package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.domain.Ranking
import com.dancemesh.common.algorithms.domain.Scoring
import com.dancemesh.common.algorithms.util.IntegerInterval

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest

import org.junit.jupiter.params.provider.ArgumentsSource

class MultiMajorityRankingAlgorithmTest() {
	
	@ParameterizedTest
    @ArgumentsSource(FinalScoringProvider::class)
	fun testGetRanking (scorings: List<Scoring<Int, List<IntegerInterval>>>, expectedResult: Ranking<Int>) {
		
		val actualResult = MultiMajorityRankingAlgorithm.getRanking(scorings)
		val table = MultiMajorityRankingAlgorithm.getMajorityTable(scorings)
		
		for (c in expectedResult.getCandidates()) {
			val row = table.single({ r -> r.candidate == c})
			val rowEntries = row.entries.joinToString()
			val rowSum = row.sum
			assertEquals(expectedResult.getRank(c), actualResult.getRank(c), "Candidate $c ($rowSum; $rowEntries)")
		}
	}
	
	@Test
	fun testGetRankingManual () {
		
		val rawData = FinalScoringProvider.rawData1
		//val rawData = FinalScoringProvider.rawData2
		
		val expectedResult = FinalDanceScoringProvider.getRanking(rawData[0])
		val scorings = rawData.drop(1).map { FinalDanceScoringProvider.getScoring(it) }
		val rankings = rawData.drop(1).map { FinalDanceScoringProvider.getRanking(it) }


		for (dance in 0..rawData.lastIndex - 1) {

			val danceScoring = scorings.get(dance)
			val expectedDanceResult = rankings.get(dance)
			
			val actualDanceResult = MajorityRankingAlgorithm.getRanking(danceScoring)
			
			for (c in expectedDanceResult.getCandidates()) {
				//var ranks = danceScoring.getScore(c)
				//var row = table.single({ r -> r.candidate == c}).entries.joinToString()
				assertEquals(expectedDanceResult.getRank(c), actualDanceResult.getRank(c), "Candidate $c in dance $dance")
			}
			//assertEquals(expectedDanceResult, actualDanceResult, "Results in individual dances")
		}

				
		val actualResult = MultiMajorityRankingAlgorithm.getRanking(scorings)
		val table = MultiMajorityRankingAlgorithm.getMajorityTable(scorings)
		
		
		for (c in expectedResult.getCandidates()) {
//			val ranks = scoring.getScore(candidate)
			val row = table.single { r -> r.candidate == c }
			val entries = row.entries.joinToString()
			val sum = row.sum
			assertEquals(expectedResult.getRank(c), actualResult.getRank(c), "Candidate $c ($sum; $entries)")
		}
		//assertEquals(expectedResult, actualResult, "Overall result")
	}
}