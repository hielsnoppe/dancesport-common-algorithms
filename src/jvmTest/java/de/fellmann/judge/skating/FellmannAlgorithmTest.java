package de.fellmann.judge.skating;

import com.dancemesh.common.algorithms.domain.Ranking;
import com.dancemesh.common.algorithms.domain.Scoring;
import com.dancemesh.common.algorithms.ranking.FinalScoringProvider;
import com.dancemesh.common.algorithms.util.IntegerInterval;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FellmannAlgorithmTest {
	
	@ParameterizedTest
	@ArgumentsSource(FinalScoringProvider.class)
	public void testGetRanking (List<Scoring<Integer, List<IntegerInterval>>> scorings, Ranking<Integer> expectedResult) {
		
		final Ranking<Integer> actualResult = (new FellmannAlgorithm()).getRanking(scorings);
		
		for (Integer c: expectedResult.getCandidates()) {
			assertEquals(expectedResult.getRank(c), actualResult.getRank(c), "Candidate " + c + " ($rowSum; $rowEntries)");
		}
		
		// assertEquals(expectedResult, actualResult)
	}
}