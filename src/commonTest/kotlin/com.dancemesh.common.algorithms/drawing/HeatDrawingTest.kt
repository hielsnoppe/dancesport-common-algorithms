package com.dancemesh.common.algorithms.drawing

import kotlin.test.Test
import kotlin.test.assertEquals

class HeatDrawingTest {

    @Test
    fun testRandomizedHeatDraw() {
        assertRandomizedHeatDraw(9, 3)
        assertRandomizedHeatDraw(93, 10)
    }

    private fun assertRandomizedHeatDraw(numberOfParticipants: Int, numberOfHeats: Int) {

        val participants = (1..1 + numberOfParticipants).toSet()
        val algorithm = Randomized<Int>()
        val draw = algorithm.draw(participants, numberOfHeats)

        assertEquals(participants.count(), draw.getNumberOfParticipants())
        assertEquals(numberOfHeats, draw.getNumberOfHeats())
    }

    @Test
    fun testIndependentRandomizedHeatDraws() {
        assertIndependentRandomizedHeatDraw(9, 3, 5)
        assertIndependentRandomizedHeatDraw(93, 10, 5)
    }

    private fun assertIndependentRandomizedHeatDraw(numberOfParticipants: Int, numberOfHeats: Int, numberOfDances: Int) {

        val participants = (1..1 + numberOfParticipants).toSet()

        val algorithm = MultiIndependent(Randomized<Int>())
        val draws = algorithm.draw(participants, numberOfHeats, numberOfDances)

        for (draw in draws) {
            assertEquals(participants.count(), draw.getNumberOfParticipants())
            assertEquals(numberOfHeats, draw.getNumberOfHeats())
        }
    }

    @Test
    fun testDependentRandomizedHeatDrawsAvoidingDoubleDancing() {

        assertDependentRandomizedHeatDrawsAvoidingDoubleDancing(9, 2, 5)
        assertDependentRandomizedHeatDrawsAvoidingDoubleDancing(9, 2, 5)
        assertDependentRandomizedHeatDrawsAvoidingDoubleDancing(9, 2, 5)

        assertDependentRandomizedHeatDrawsAvoidingDoubleDancing(18, 3, 5)
        assertDependentRandomizedHeatDrawsAvoidingDoubleDancing(18, 3, 5)
        assertDependentRandomizedHeatDrawsAvoidingDoubleDancing(18, 3, 5)

        assertDependentRandomizedHeatDrawsAvoidingDoubleDancing(93, 10, 5)
        assertDependentRandomizedHeatDrawsAvoidingDoubleDancing(93, 10, 5)
        assertDependentRandomizedHeatDrawsAvoidingDoubleDancing(93, 10, 5)
    }

    private fun assertDependentRandomizedHeatDrawsAvoidingDoubleDancing(numberOfParticipants: Int, numberOfHeats: Int, numberOfDances: Int) {

        val participants = (1..1 + numberOfParticipants).toSet()

        val algorithm = MultiRandomizedNice<Int>()
        val draws = algorithm.draw(participants, numberOfHeats, numberOfDances)

        for (draw in draws) {
            assertEquals(participants.count(), draw.getNumberOfParticipants())
            assertEquals(numberOfHeats, draw.getNumberOfHeats())
        }

        // assertAll(
        //    "No double dancing",
        draws
            .zipWithNext { fst, snd -> getDoubles(fst, snd) }
            .map { { assertEquals(setOf<Int>(), it) } }
        // )
    }

    fun <P2> getDoubles(fst: com.dancemesh.common.algorithms.domain.HeatDraw<P2>, snd: com.dancemesh.common.algorithms.domain.HeatDraw<P2>): Set<P2> {

        val lastHeatOfPreviousDance = fst.getLastHeat()
        val firstHeatOfCurrentDance = snd.getFirstHeat()

        return firstHeatOfCurrentDance.intersect(lastHeatOfPreviousDance)
    }
}
