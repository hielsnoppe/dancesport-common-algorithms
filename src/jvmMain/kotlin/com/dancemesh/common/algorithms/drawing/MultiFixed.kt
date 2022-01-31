package com.dancemesh.common.algorithms.drawing

import com.dancemesh.common.algorithms.domain.HeatDraw

class MultiFixed<P>(algo: HeatDrawingAlgorithm<P>): MultiHeatDrawingAlgorithm<P> {
	
	val algo: HeatDrawingAlgorithm<P> = algo
	
	override fun draw (participants: Set<P>, numberOfHeats: Int, numberOfDances: Int): List<com.dancemesh.common.algorithms.domain.HeatDraw<P>> {
		
		val draw = algo.draw(participants, numberOfHeats)
		return (1..numberOfDances).map({ draw })
	}
}