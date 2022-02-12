package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.domain.Ranking
import com.dancemesh.common.algorithms.domain.Scoring
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import java.math.BigDecimal

class MarksSumRankingAlgorithmTest() {
	
    @ParameterizedTest
    @ArgumentsSource(DanceTeamScoringProvider::class)
    fun testGetRanking(scoring: Scoring<Int, List<BigDecimal>>, expectedResult: Ranking<Int>) {
		
        val actualResult = MarksSumRankingAlgorithm.getRanking(scoring)
        // val table = MajorityRankingAlgorithm.getMajorityTable(scoring)
		
        for (candidate in expectedResult.getCandidates()) {
            val ranks = scoring.getScore(candidate)
            assertEquals(expectedResult.getRank(candidate), actualResult.getRank(candidate), "Candidate $candidate ($ranks)")
        }
		
        // assertEquals(expectedResult, actualResult)
    }
}
