package com.dancemesh.common.algorithms.drawing

import com.dancemesh.common.algorithms.domain.HeatDraw
import com.dancemesh.common.algorithms.domain.HeatDrawImpl
import com.dancemesh.common.algorithms.domain.MutableHeatDraw

/**
 * An algorithm creating heat draws for individual dances based on a sorting criterion, e.g., the number.
 */
class SortedWith<P>(comp: Comparator<P>): HeatDrawingAlgorithm<P> {
	
	val comp: Comparator<P> = comp
	
	override fun draw (participants: Set<P>, numberOfHeats: Int): com.dancemesh.common.algorithms.domain.HeatDraw<P> {
		
		val participantsPerHeat = participants.count() / numberOfHeats
		return participants.sortedWith(comp).chunked(participantsPerHeat)
			.foldIndexed(com.dancemesh.common.algorithms.domain.HeatDrawImpl(), fun (heat, acc, it): MutableHeatDraw<P> {
				it.forEach({ acc.setHeat(it, heat + 1) })
				return acc
			})
	}
}