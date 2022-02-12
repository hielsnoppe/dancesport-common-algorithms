package com.dancemesh.common.algorithms.wdsf

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AJS_3_0_MarkTest() {

    companion object {
        val waltz_1 = mapOf(
            'A' to AJS_3_0_Mark.ofTQPS(10.0),
            'B' to AJS_3_0_Mark.ofTQPS(9.0),
            'C' to AJS_3_0_Mark.ofTQPS(8.0),
            'D' to AJS_3_0_Mark.ofTQPS(7.0),
            'E' to AJS_3_0_Mark.ofMMCP(10.0),
            'F' to AJS_3_0_Mark.ofMMCP(9.0),
            'G' to AJS_3_0_Mark.ofMMCP(10.0),
            'H' to AJS_3_0_Mark.ofMMCP(8.0),
        )
    }
	
    @Test
    fun testCompareTo() {
        assertCompareTo(waltz_1['A']!!, waltz_1['B']!!)
        assertCompareTo(waltz_1['B']!!, waltz_1['C']!!)
        assertCompareTo(waltz_1['C']!!, waltz_1['D']!!)
        assertCompareTo(waltz_1['E']!!, waltz_1['F']!!)
        assertCompareTo(waltz_1['E']!!, waltz_1['H']!!)
        assertCompareTo(waltz_1['F']!!, waltz_1['H']!!)
    }

    private fun assertCompareTo(better: AJS_3_0_Mark, worse: AJS_3_0_Mark) {
        assertTrue(better > worse, "")
    }

    @Test
    fun testTotal() {
        assertTotal(waltz_1['A']!!, 20.0)
        assertTotal(waltz_1['B']!!, 18.0)
        assertTotal(waltz_1['C']!!, 16.0)
        assertTotal(waltz_1['D']!!, 14.0)
        assertTotal(waltz_1['E']!!, 20.0)
        assertTotal(waltz_1['F']!!, 18.0)
        assertTotal(waltz_1['G']!!, 20.0)
        assertTotal(waltz_1['H']!!, 16.0)
    }

    private fun assertTotal(mark: AJS_3_0_Mark, expectedTotal: Double) {
        assertEquals(expectedTotal, mark.total)
    }
}
