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
}
