package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.util.IntegerInterval
import com.dancemesh.common.algorithms.domain.Ranking
import com.dancemesh.common.algorithms.domain.Scoring
import com.dancemesh.common.algorithms.domain.MutableRanking
import com.dancemesh.common.algorithms.domain.RankingImpl

object MajorityRankingAlgorithm: RankingAlgorithm<List<IntegerInterval>> {
	
	data class Row (val candidate: Any, val entries: Array<Entry>, val majority: Int) {
		
		val majorityOn by lazy { entries.indexOfFirst { e -> e.count >= majority } + 1 }
		
		constructor (candidate: Any, ranks: List<IntegerInterval>, length: Int):
				this(candidate,
					MajorityRankingAlgorithmUtils.countAndSum(ranks, length),
					MajorityRankingAlgorithmUtils.getMajority(ranks.count())
				)
		
		class RowComparator (private val start: Int): Comparator<Row> {
		
			override fun compare (a: Row, b: Row): Int =
					a.entries
						.drop(start - 1)
						.zip(b.entries.drop(start - 1)) { aa, bb -> aa.compareTo(bb) }
						.firstOrNull { x -> x != 0 } ?: 0
		}
	}
	
	/**
	 * Use this to calculate results from a dance
	 */
	override fun <C> getRanking (scoring: Scoring<C, List<IntegerInterval>>): Ranking<C> {
		
		val table: Set<Row> = getMajorityTable(scoring)
		val ranking: MutableRanking<C> = RankingImpl<C>();
		
		table.groupBy { it.majorityOn }
			.toSortedMap()
				.flatMap { e -> chunked(e.value.toSet(), e.key) }
			.fold(ranking) { acc, it -> splitRanks(acc, it) }

		return ranking;
	}
	
	fun <C> getMajorityTable (scoring: Scoring<C, List<IntegerInterval>>): Set<Row> =
			scoring.getCandidates().map { c ->
				Row(
					c as Any,
					scoring.getScore(c),
					scoring.getCandidates().count()
				)
			}.toSet();
	
	private fun chunked (rows: Set<Row>, majority: Int): List<Set<Row>> {
		
		if (rows.isEmpty()) return listOf()
		
		val comp = Row.RowComparator(majority);
		val top = rows.maxWithOrNull(comp)!!
		val parts = rows.partition { row: Row -> comp.compare(row, top) == 0 }

		return listOf(parts.first.toSet()) + chunked(parts.second.toSet(), majority)
	}
	
	private fun <C> splitRanks (acc: MutableRanking<C>, rows: Set<Row>): MutableRanking<C> {
		
		val min = acc.count() + 1
		val max = acc.count() + rows.count()
		
		rows.forEach {
			acc.putRank(it.candidate as C, IntegerInterval(min, max))
		}
		return acc
	}
}