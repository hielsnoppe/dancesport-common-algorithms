package com.dancemesh.common.algorithms.ranking

import kotlin.test.Test
import kotlin.test.assertEquals

class MajorityRankingAlgorithmUtilsTest() {

    @Test
    fun testGetMajority() {
		
        listOf(
            Pair(3, 2),
            Pair(5, 3),
            Pair(7, 4),
            Pair(9, 5),
            Pair(11, 6),
            Pair(13, 7),
            Pair(15, 8)
        ).forEach {
            assertEquals(it.second, MajorityRankingAlgorithmUtils.getMajority(it.first))
        }
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
