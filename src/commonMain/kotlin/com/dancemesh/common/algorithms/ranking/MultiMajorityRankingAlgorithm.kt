package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.domain.*
import com.dancemesh.common.algorithms.util.IntFraction
import com.dancemesh.common.algorithms.util.IntegerInterval

object MultiMajorityRankingAlgorithm : MultiRankingAlgorithm<List<IntegerInterval>> {

    data class Row(
        val candidate: Any,
        val entries: Array<Entry>,
        val horrorTable: Array<Entry>,
        val majority: Int,
        val majorityHorror: Int,
        val sum: IntFraction
    ) {

        val majorityOn by lazy { entries.indexOfFirst { e -> e.count >= majority } + 1 }

        constructor (candidate: Any, danceResults: List<IntegerInterval>, ranks: List<IntegerInterval>, maxRank: Int) :
            this(
                candidate,
                MajorityRankingAlgorithmUtils.countAndSum(danceResults, maxRank),
                MajorityRankingAlgorithmUtils.countAndSum(ranks, maxRank),
                MajorityRankingAlgorithmUtils.getMajority(danceResults.count()),
                MajorityRankingAlgorithmUtils.getMajority(ranks.count()),
                MajorityRankingAlgorithmUtils.sumByIntFraction(danceResults)
            )
		
        class SumComparator() : Comparator<Row> {

            override fun compare(first: Row, second: Row): Int {
				
                if (first.sum < second.sum) return 1
                if (first.sum > second.sum) return -1
                return 0
            }
        }
		
        class RowComparator(val start: Int) : Comparator<Row> {
		
            override fun compare(first: Row, second: Row): Int {
				
                val foo = first.entries.zip(second.entries) { a, b -> a.compareTo(b) }.get(start - 1)
                if (foo != 0) return foo
				
                return first.horrorTable.zip(second.horrorTable) { a, b -> a.compareTo(b) }
                    .drop(start - 1).firstOrNull { x -> x != 0 } ?: 0
            }
        }
    }
	
    /**
     * Use this to calculate final results
     */
    override fun <C> getRanking(scorings: List<Scoring<C, List<IntegerInterval>>>): Ranking<C> {
		
        val table = getMajorityTable(scorings)
        val ranking: MutableRanking<C> = RankingImpl<C>()
		
        MajorityRankingAlgorithmUtils.chunkedWith(table, Row.SumComparator())
            .fold(ranking) { acc, it -> assignRanks(acc, it) }

        return ranking
    }
	
    fun <C> getMajorityTable(scorings: List<Scoring<C, List<IntegerInterval>>>): Set<Row> {
		
        val rankings = scorings.map { MajorityRankingAlgorithm.getRanking(it) }
        val candidates = rankings.first().getCandidates()
		
        val combinedScoring = candidates.fold(ScoringImpl<C, List<IntegerInterval>>()) { acc, c ->
            acc.putScore(c, rankings.map { it.getRank(c) })
            acc
        }

        val individualScorings = candidates.fold(ScoringImpl<C, List<IntegerInterval>>()) { acc, c ->
            acc.putScore(c, scorings.map { it.getScore(c) }.flatten())
            acc
        }

        val maxRank = combinedScoring.count()
		
        return combinedScoring.getCandidates().map {
            Row(it as Any, combinedScoring.getScore(it), individualScorings.getScore(it), maxRank)
        }.toSet()
    }
	
    fun chunked2(rows: Set<Row>, rank: Int): List<Set<Row>> {
		
        if (rows.isEmpty()) return listOf()
		
        val comp = Row.RowComparator(rank)
        val top = rows.maxWithOrNull(comp)!!
        val parts = rows.partition { row: Row -> comp.compare(row, top) == 0 }

        return listOf(parts.first.toSet()) + chunked2(parts.second.toSet(), rank + 1)
    }
	
    fun <C> assignRanks(acc: MutableRanking<C>, rows: Set<Row>): MutableRanking<C> {
		
        if (rows.count() == 1)
            acc.putRank(rows.first().candidate as C, IntegerInterval(acc.count() + 1))
        else
            chunked2(rows, acc.count() + 1).fold(acc) { acc2, it -> splitRanks(acc2, it) }

        return acc
    }
	
    fun <C> splitRanks(acc: MutableRanking<C>, rows: Set<Row>): MutableRanking<C> {
		
        val min = acc.count() + 1
        val max = acc.count() + rows.count()
		
        rows.forEach {
            acc.putRank(it.candidate as C, IntegerInterval(min, max))
        }
        return acc
    }
}
