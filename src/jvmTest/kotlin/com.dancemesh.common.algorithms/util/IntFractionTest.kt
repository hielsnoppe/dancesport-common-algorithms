package com.dancemesh.common.algorithms.util

import com.dancemesh.common.algorithms.ranking.MajorityRankingAlgorithmUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class IntFractionTest () {
	
	@ParameterizedTest
    @MethodSource("testDataGetMajority")
	fun testGetMajority(total: Int, expectedResult: Int) {
		
		assertEquals(expectedResult, MajorityRankingAlgorithmUtils.getMajority(total))
	}
	
	companion object {
		
		@JvmStatic
		fun testDataGetMajority(): Stream<Arguments> = Stream.of(
				Arguments.of(3, 2),
				Arguments.of(5, 3),
				Arguments.of(7, 4),
				Arguments.of(9, 5),
				Arguments.of(11, 6),
				Arguments.of(13, 7),	
				Arguments.of(15, 8)
		)
	}
}