package com.dancemesh.common.algorithms.wdsf

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class AJS_3_0_ScoreTest() {

    companion object {
        val waltz_1: AJS_3_0_Score = AJS_3_0_Score(AJS_3_0_MarkTest.waltz_1.values)
        val waltz_2: AJS_3_0_Score = AJS_3_0_Score(AJS_3_0_MarkTest.waltz_1.values)
    }

    @Test
    fun testFilterByTolerance() {
        assertFilterByTolerance(1.5, listOf(1.0, 1.0, 1.0), listOf(1.0, 1.0, 1.0))
        assertFilterByTolerance(1.5, listOf(1.0, 1.0, 2.0), listOf(1.0, 1.0, 2.0))
        assertFilterByTolerance(1.5, listOf(1.0, 2.0, 3.5), listOf(1.0, 2.0, 3.5))
        assertFilterByTolerance(1.5, listOf(1.0, 2.0, 4.0), listOf(1.0, 2.0))
    }

    private fun assertFilterByTolerance(tolerance: Double, marks: List<Double>, expectedResult: List<Double>) {
        assertContentEquals(expectedResult, AJS_3_0_Score.filterByTolerance(marks, tolerance))
    }

    @Test
    fun testGetScore() {
        assertGetScore(waltz_1, AJS_3_0_Components.TQ, 8.5)
        assertGetScore(waltz_1, AJS_3_0_Components.PS, 8.5)
        assertGetScore(waltz_1, AJS_3_0_Components.MM, 9.25)
        assertGetScore(waltz_1, AJS_3_0_Components.CP, 9.25)
    }

    private fun assertGetScore(score: AJS_3_0_Score, component: AJS_3_0_Components, expectedValue: Double) {
        assertEquals(expectedValue, score.getScore(component), 0.01)
    }

	/*
	@Test
	fun testGetScores () {

		assertContentEquals(listOf(10.0, 10.0, 10.0, 10.0), score.getScores(AJS_3_0_Components.TQ), "")
		assertContentEquals(listOf(10.0, 10.0, 10.0, 10.0), score.getScores(AJS_3_0_Components.PS), "")
		assertContentEquals(listOf(10.0, 10.0, 10.0, 10.0), score.getScores(AJS_3_0_Components.MM), "")
		assertContentEquals(listOf(10.0, 10.0, 10.0, 10.0), score.getScores(AJS_3_0_Components.CP), "")
	}

	@Test
	fun testGetComponents () {

		assertEquals(setOf(
			AJS_3_0_Components.TQ, AJS_3_0_Components.PS,
			AJS_3_0_Components.CP, AJS_3_0_Components.MM
		), score.getComponents(), "")
	}
	*/
}
