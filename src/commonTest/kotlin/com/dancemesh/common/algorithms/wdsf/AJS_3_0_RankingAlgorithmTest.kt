package com.dancemesh.common.algorithms.wdsf

import com.dancemesh.common.algorithms.domain.MutableRanking
import com.dancemesh.common.algorithms.domain.MutableScoring
import com.dancemesh.common.algorithms.domain.RankingImpl
import com.dancemesh.common.algorithms.domain.ScoringImpl
import com.dancemesh.common.algorithms.util.IntegerInterval
import kotlin.test.Test
import kotlin.test.assertEquals

class AJS_3_0_RankingAlgorithmTest() {

    companion object {
        val scoring: MutableScoring<Int, AJS_3_0_Score> = ScoringImpl()
        val candidate1 = listOf(
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.TQ to 10.0,
                    AJS_3_0_Components.PS to 10.0,
                )
            ),
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.TQ to 10.0,
                    AJS_3_0_Components.PS to 10.0,
                )
            ),
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.TQ to 10.0,
                    AJS_3_0_Components.PS to 10.0,
                )
            ),
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.TQ to 9.0,
                    AJS_3_0_Components.PS to 8.0,
                )
            ),
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.MM to 10.0,
                    AJS_3_0_Components.CP to 10.0,
                )
            ),
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.MM to 10.0,
                    AJS_3_0_Components.CP to 10.0,
                )
            ),
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.MM to 10.0,
                    AJS_3_0_Components.CP to 10.0,
                )
            ),
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.MM to 10.0,
                    AJS_3_0_Components.CP to 10.0,
                )
            ),
        )
        val candidate2 = listOf(
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.TQ to 9.0,
                    AJS_3_0_Components.PS to 9.0,
                )
            ),
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.TQ to 9.0,
                    AJS_3_0_Components.PS to 9.0,
                )
            ),
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.TQ to 9.0,
                    AJS_3_0_Components.PS to 9.0,
                )
            ),
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.TQ to 9.0,
                    AJS_3_0_Components.PS to 9.0,
                )
            ),
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.MM to 9.0,
                    AJS_3_0_Components.CP to 9.0,
                )
            ),
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.MM to 9.0,
                    AJS_3_0_Components.CP to 9.0,
                )
            ),
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.MM to 9.0,
                    AJS_3_0_Components.CP to 9.0,
                )
            ),
            AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.MM to 9.0,
                    AJS_3_0_Components.CP to 9.0,
                )
            ),
        )
    }

    init {
        scoring.putScore(101, AJS_3_0_Score(candidate1))
        scoring.putScore(102, AJS_3_0_Score(candidate2))
    }

    @Test
    fun testGetRanking() {

        val expectedResult: MutableRanking<Int> = RankingImpl()
        expectedResult.putRank(101, IntegerInterval(1))
        expectedResult.putRank(102, IntegerInterval(2))

        val actualResult = AJS_3_0_RankingAlgorithm.getRanking(scoring)

        expectedResult.getCandidates().forEach {
            val ranks = scoring.getScore(it)
            assertEquals(expectedResult.getRank(it), actualResult.getRank(it), "Candidate $it ($ranks)")
        }
    }
}
