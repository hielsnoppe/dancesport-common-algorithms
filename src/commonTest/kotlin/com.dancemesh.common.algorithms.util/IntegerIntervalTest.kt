package com.dancemesh.common.algorithms.util

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IntegerIntervalTest() {

    @Test
    fun testEquals() {
        assertTrue(IntegerInterval(0).equals(IntegerInterval(0)))
        assertTrue(IntegerInterval(1).equals(IntegerInterval(1)))
        assertFalse(IntegerInterval(0).equals(IntegerInterval(1)))
    }

    @Test
    fun testIsNext() {
        assertTrue(IntegerInterval(0).isNext(IntegerInterval(1)))
        assertTrue(IntegerInterval(1).isNext(IntegerInterval(2)))
        assertFalse(IntegerInterval(0).isNext(IntegerInterval(2)))
    }

    @Test
    fun testIsOverlapping() {
        assertTrue(IntegerInterval(0, 1).isOverlapping(IntegerInterval(1, 2)))
        assertTrue(IntegerInterval(0, 2).isOverlapping(IntegerInterval(2, 3)))
        assertFalse(IntegerInterval(0, 1).isOverlapping(IntegerInterval(2, 3)))
        assertFalse(IntegerInterval(0, 1).isOverlapping(IntegerInterval(3, 4)))
    }

    @Test
    fun testIsDisjunct() {
        assertFalse(IntegerInterval(0, 1).isDisjunct(IntegerInterval(1, 2)))
        assertFalse(IntegerInterval(0, 2).isDisjunct(IntegerInterval(2, 3)))
        assertTrue(IntegerInterval(0, 1).isDisjunct(IntegerInterval(2, 3)))
        assertTrue(IntegerInterval(0, 1).isDisjunct(IntegerInterval(3, 4)))
    }

    @Test
    fun testSize() {
        assertEquals(0, IntegerInterval(0).size())
        assertEquals(0, IntegerInterval(0, 0).size())
        assertEquals(0, IntegerInterval(1).size())
        assertEquals(1, IntegerInterval(0, 1).size())
        assertEquals(1, IntegerInterval(2, 3).size())
        assertEquals(2, IntegerInterval(1, 3).size())
    }
}
