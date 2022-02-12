package com.dancemesh.common.algorithms.wdsf

import kotlin.test.Test
import kotlin.test.assertEquals

class AJS_1_0_ScoreTest() {

    companion object {
        val waltz_1 = AJS_1_0_Score(AJS_1_0_MarkTest.waltz_1.values)
        val waltz_7 = AJS_1_0_Score(AJS_1_0_MarkTest.waltz_7.values)
    }

    @Test
    fun testGetScore() {
        assertGetScore(waltz_1, AJS_1_0_Components.PB, 8.78)
        assertGetScore(waltz_1, AJS_1_0_Components.QM, 8.89)
        assertGetScore(waltz_1, AJS_1_0_Components.MM, 8.83)
        assertGetScore(waltz_1, AJS_1_0_Components.PS, 8.83)
        assertGetScore(waltz_1, AJS_1_0_Components.CP, 8.56)
    }

    private fun assertGetScore(score: AJS_1_0_Score, component: AJS_1_0_Components, expectedValue: Double) {
        assertEquals(expectedValue, score.getScore(component), 0.01)
    }
}
