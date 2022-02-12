package com.dancemesh.common.algorithms.wdsf

import com.dancemesh.common.algorithms.domain.*
import com.dancemesh.common.algorithms.util.IntegerInterval
import kotlin.test.Test
import kotlin.test.assertEquals

class AJS_1_0_RankingAlgorithmTest() {

    companion object {
        val scoring: MutableScoring<Int, AJS_1_0_Score> = ScoringImpl()
    }

    init {
        scoring.putScore(1, AJS_1_0_ScoreTest.waltz_1)
        scoring.putScore(7, AJS_1_0_ScoreTest.waltz_7)
    }

    @Test
    fun testGetRanking() {

        val expectedResult: MutableRanking<Int> = RankingImpl()
        expectedResult.putRank(7, IntegerInterval(1))
        expectedResult.putRank(1, IntegerInterval(2))

        val actualResult = AJS_1_0_RankingAlgorithm.getRanking(scoring)

        expectedResult.getCandidates().forEach {
            val ranks = scoring.getScore(it)
            assertEquals(expectedResult.getRank(it), actualResult.getRank(it), "Candidate $it ($ranks)")
        }
    }

    fun <C> assertRanking(expectedRanking: Ranking<C>, actualRanking: Ranking<C>) {
        expectedRanking.getCandidates().forEach {
            assertEquals(expectedRanking.getRank(it), actualRanking.getRank(it), "Candidate $it")
        }
    }
}
