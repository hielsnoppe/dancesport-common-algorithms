package com.dancemesh.common.algorithms.drawing

import com.dancemesh.common.algorithms.domain.HeatDraw
import org.junit.jupiter.api.assertAll;
import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HeatDrawingTest {
	
	@ParameterizedTest
	@CsvSource(
		"9, 3",
		"93, 10"
	)
	fun `Randomized heat draw` (numberOfParticipants: Int, numberOfHeats: Int) {
		
		val participants = (1..1 + numberOfParticipants).toSet()
		
		val algorithm = Randomized<Int>()
		val draw = algorithm.draw(participants, numberOfHeats)
		
		assertEquals(participants.count(), draw.getNumberOfParticipants())
		assertEquals(numberOfHeats, draw.getNumberOfHeats())
	}
	
	@ParameterizedTest
	@CsvSource(
		"9, 3, 5",
		"93, 10, 5"
	)
	fun `Independent randomized heat draws` (numberOfParticipants: Int, numberOfHeats: Int, numberOfDances: Int) {
		
		val participants = (1..1 + numberOfParticipants).toSet()
		
		val algorithm = MultiIndependent(Randomized<Int>())
		val draws = algorithm.draw(participants, numberOfHeats, numberOfDances)
		
		for (draw in draws) {
			assertEquals(participants.count(), draw.getNumberOfParticipants())
			assertEquals(numberOfHeats, draw.getNumberOfHeats())
		}
	}
	
		
	@ParameterizedTest
	@CsvSource(
		"9, 2, 5",
		"9, 2, 5",
		"9, 2, 5",
		"18, 3, 5",
		"18, 3, 5",
		"18, 3, 5",
		"93, 10, 5",
		"93, 10, 5",
		"93, 10, 5"
	)
	fun `Dependent randomized heat draws avoiding double dancing` (numberOfParticipants: Int, numberOfHeats: Int, numberOfDances: Int) {
		
		val participants = (1..1 + numberOfParticipants).toSet()
		
		val algorithm = MultiRandomizedNice<Int>()
		val draws = algorithm.draw(participants, numberOfHeats, numberOfDances)
		
		for (draw in draws) {
			assertEquals(participants.count(), draw.getNumberOfParticipants())
			assertEquals(numberOfHeats, draw.getNumberOfHeats())
		}
		
		assertAll("No double dancing", draws
			.zipWithNext({ fst, snd -> getDoubles(fst, snd)})
			.map({{ assertEquals(setOf<Int>(), it) }})
		)
	}
	
	fun <P2> getDoubles (fst: HeatDraw<P2>, snd: HeatDraw<P2>): Set<P2> {
		
		val lastHeatOfPreviousDance = fst.getLastHeat()
		val firstHeatOfCurrentDance = snd.getFirstHeat()
		
		return firstHeatOfCurrentDance.intersect(lastHeatOfPreviousDance)
	}
}