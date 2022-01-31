package com.dancemesh.common.algorithms.ranking

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MajorityRankingAlgorithmUtilsTest() {
	
	@ParameterizedTest
    @CsvSource(
		"3, 2",
		"5, 3",
		"7, 4",
		"9, 5",
		"11, 6",
		"13, 7",
		"15, 8"
    )
	fun testGetMajority(total: Int, expectedResult: Int) {
		
		assertEquals(expectedResult, MajorityRankingAlgorithmUtils.getMajority(total))
	}
	
	/*
	@Test
	fun testSumByIntFraction () {
		val actualResult = MajorityRankingAlgorithmUtils.sumByIntFraction(listOf(
				IntegerInterval(1),
				IntegerInterval(2)
		))
		
		assertTrue(actualResult.equals(IntegerInterval(3)))
	}
	*/
	
	/*
	class ScoringAggregator: ArgumentsAggregator {
		
	    override fun aggregateArguments(arguments: ArgumentsAccessor, context: ParameterContext): Scoring<Int, List<IntegerInterval>> {
			
	        var scoring: MutableScoring<Int, List<IntegerInterval>> = ScoringImpl()
			
 			//stackoverflow.com/questions/45086786/cast-any-to-array-in-kotlin
			val rows = (arguments.toArray() as? Array<*>)?.filterIsInstance<Array<Int>>()
			
			for (row in rows!!) {
			
				val id = row[0]
				scoring.putScore(id, row.drop(2).map({x -> IntegerInterval(x)}).toList())
			}
			
			return scoring
	    }
	}
	
	class RankingAggregator: ArgumentsAggregator {
		
	    override fun aggregateArguments(arguments: ArgumentsAccessor, context: ParameterContext): Ranking<Int> {
			
			var ranking: MutableRanking<Int> = RankingImpl()
			
			for (row in rawData) {
			
				val id = row[0]
				ranking.putRank(id, IntegerInterval(row[1]))
			}
			
			return ranking
	    }
	}
	*/
}