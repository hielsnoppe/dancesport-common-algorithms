package com.dancemesh.common.algorithms.wdsf

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AJS_1_0_MarkTest() {

	companion object {

		/**
		 * @see [WDSF website](https://cdnb.worlddancesport.org/legacy-results/40850/W.pdf)
		 */
		val waltz_1 = mapOf(
			'A' to AJS_1_0_Mark.of(8.5, 8.5, 9.0, 8.5, 8.5),
			'B' to AJS_1_0_Mark.of(8.0, 8.0, 8.5, 8.5, 7.5),
			'C' to AJS_1_0_Mark.of(9.0, 9.5, 9.0, 9.5, 8.5),
			'D' to AJS_1_0_Mark.of(8.5, 8.5, 8.5, 8.5, 8.0),
			'E' to AJS_1_0_Mark.of(9.0, 8.5, 8.5, 9.0, 8.5),
			'F' to AJS_1_0_Mark.of(8.5, 9.0, 8.5, 8.0, 8.5),
			'G' to AJS_1_0_Mark.of(8.5, 8.5, 9.0, 9.0, 8.5),
			'H' to AJS_1_0_Mark.of(9.0, 9.0, 9.0, 8.5, 8.5),
			'I' to AJS_1_0_Mark.of(9.5, 9.0, 9.0, 8.5, 9.0),
			'J' to AJS_1_0_Mark.of(9.0, 9.5, 9.0, 9.5, 9.5),
			'K' to AJS_1_0_Mark.of(9.0, 9.5, 9.5, 9.5, 9.0),
		)
	}

	@Test
	fun testCompareTo () {
		assertCompareTo(waltz_1['A']!!, waltz_1['B']!!)
		assertCompareTo(waltz_1['C']!!, waltz_1['B']!!)
		assertCompareTo(waltz_1['D']!!, waltz_1['B']!!)
		assertCompareTo(waltz_1['C']!!, waltz_1['A']!!)
		assertCompareTo(waltz_1['A']!!, waltz_1['D']!!)
	}

	private fun assertCompareTo (better: AJS_1_0_Mark, worse: AJS_1_0_Mark) {
		assertTrue(better > worse, "")
	}

	@Test
	fun testTotal () {
		assertTotal(waltz_1['A']!!, 43.0)
		assertTotal(waltz_1['B']!!, 40.5)
		assertTotal(waltz_1['C']!!, 45.5)
		assertTotal(waltz_1['D']!!, 42.0)
		assertTotal(waltz_1['E']!!, 43.5)
		assertTotal(waltz_1['F']!!, 42.5)
		assertTotal(waltz_1['G']!!, 43.5)
		assertTotal(waltz_1['H']!!, 44.0)
		assertTotal(waltz_1['I']!!, 45.0)
		assertTotal(waltz_1['J']!!, 46.5)
		assertTotal(waltz_1['K']!!, 46.5)
	}

	private fun assertTotal (mark: AJS_1_0_Mark, expectedTotal: Double) {
		assertEquals(expectedTotal, mark.total)
	}
}