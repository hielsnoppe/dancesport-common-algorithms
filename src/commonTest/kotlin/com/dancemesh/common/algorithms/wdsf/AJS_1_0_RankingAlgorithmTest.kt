package com.dancemesh.common.algorithms.wdsf

import com.dancemesh.common.algorithms.domain.*
import com.dancemesh.common.algorithms.util.IntegerInterval
import kotlin.test.assertEquals
import kotlin.test.Test

class AJS_1_0_RankingAlgorithmTest() {

	val scoring: MutableScoring<Int, AJS_1_0_Score> = ScoringImpl()

	init {
		scoring.putScore(1, AJS_1_0_ScoreTest.score)
	}

	@Test
	fun testGetRanking () {

		val expectedResult: MutableRanking<Int> = RankingImpl()
		expectedResult.putRank(1, IntegerInterval(1))
		expectedResult.putRank(2, IntegerInterval(2))

		val actualResult = AJS_1_0_RankingAlgorithm.getRanking(scoring)

		expectedResult.getCandidates().forEach {
			val ranks = scoring.getScore(it)
			assertEquals(expectedResult.getRank(it), actualResult.getRank(it), "Candidate $it ($ranks)")
		}
	}

	fun <C> assertRanking (expectedRanking: Ranking<C>, actualRanking: Ranking<C>) {
		expectedRanking.getCandidates().forEach {
			assertEquals(expectedRanking.getRank(it), actualRanking.getRank(it), "Candidate $it")
		}
	}
}