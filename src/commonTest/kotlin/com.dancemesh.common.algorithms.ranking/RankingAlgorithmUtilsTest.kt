package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.domain.Ranking
import com.dancemesh.common.algorithms.domain.RankingImpl
import com.dancemesh.common.algorithms.util.IntegerInterval
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RankingAlgorithmUtilsTest() {

    @Test
    fun testSliceRanking() {

        val ranking = RankingImpl<Int>()
        listOf(
            Pair(1, 1),
            Pair(2, 2),
            Pair(3, 3),
            Pair(4, 4),
            Pair(5, 5),
            Pair(6, 6),
            Pair(7, 7),
            Pair(8, 8),
            Pair(9, 9),
            Pair(10, 10),
            Pair(11, 11),
            Pair(12, 12),
            Pair(13, 13),
            Pair(14, 14),
            Pair(15, 15),
            Pair(16, 16),
            Pair(17, 17),
            Pair(18, 18),
            Pair(19, 19),
            Pair(20, 20),
        ).forEach {
            ranking.putRank(it.first, IntegerInterval(it.second))
        }

        val rankingFizz = RankingAlgorithmUtils.sliceRanking(ranking) { (it % 3 == 0).and((it % 5 == 0).not()) }
        val rankingBuzz = RankingAlgorithmUtils.sliceRanking(ranking) { (it % 5 == 0).and((it % 3 == 0).not()) }
        val rankingFizzBuzz = RankingAlgorithmUtils.sliceRanking(ranking) { (it % 5 == 0).and(it % 3 == 0) }
        val rankingRest = RankingAlgorithmUtils.sliceRanking(ranking) { ! (it % 3 == 0).or(it % 5 == 0) }

        assertEquals(1, rankingFizzBuzz.count())
        assertEquals(3, rankingBuzz.count())
        assertEquals(5, rankingFizz.count())
        assertEquals(11, rankingRest.count())

        assertCoherentRanking(ranking)
        assertCoherentRanking(rankingFizz)
        assertCoherentRanking(rankingBuzz)
        assertCoherentRanking(rankingFizzBuzz)
        assertCoherentRanking(rankingRest)
    }

    fun <C> assertCoherentRanking(ranking: Ranking<C>) {

        val scores = ranking.getScores().groupBy { it.second }

        scores.entries.asSequence().forEach {
            assertEquals(it.key.max - it.key.min + 1, it.value.size)
        }

        scores.keys.sorted().reduce() {
            acc, it ->
            assertTrue(it.isNext(acc), "$acc $it"); it
        }
    }
}
