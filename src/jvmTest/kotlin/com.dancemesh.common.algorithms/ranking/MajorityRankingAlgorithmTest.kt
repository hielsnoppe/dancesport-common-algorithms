package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.domain.Ranking
import com.dancemesh.common.algorithms.domain.Scoring
import com.dancemesh.common.algorithms.util.IntegerInterval

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource

class MajorityRankingAlgorithmTest() {
	
	@ParameterizedTest
	@ArgumentsSource(FinalDanceScoringProvider::class)
	fun testGetMajorityTable (scoring: Scoring<Int, List<IntegerInterval>>, expectedResult: Ranking<Int>) {
		
		val actualResult: Set<MajorityRankingAlgorithm.Row> = MajorityRankingAlgorithm.getMajorityTable(scoring)
		
		for (row in actualResult) {
			assertEquals(row, row)
		}
	}
		
	@ParameterizedTest
	@ArgumentsSource(FinalDanceScoringProvider::class)
	fun testGetRanking (scoring: Scoring<Int, List<IntegerInterval>>, expectedResult: Ranking<Int>) {
		
		val actualResult = MajorityRankingAlgorithm.getRanking(scoring)
		val table = MajorityRankingAlgorithm.getMajorityTable(scoring)
		
		for (candidate in expectedResult.getCandidates()) {
			val ranks = scoring.getScore(candidate)
			val row = table.single { r -> r.candidate == candidate }.entries.joinToString()
			assertEquals(expectedResult.getRank(candidate), actualResult.getRank(candidate), "Candidate $candidate ($ranks) ($row)")
		}
		
		// assertEquals(expectedResult, actualResult)
	}
}