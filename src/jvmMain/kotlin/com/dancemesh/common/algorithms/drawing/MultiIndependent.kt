package com.dancemesh.common.algorithms.drawing

import com.dancemesh.common.algorithms.domain.HeatDraw

class MultiIndependent<P>(algo: HeatDrawingAlgorithm<P>): MultiHeatDrawingAlgorithm<P> {
	
	val algo: HeatDrawingAlgorithm<P> = algo
	
	override fun draw (participants: Set<P>, numberOfHeats: Int, numberOfDances: Int): List<com.dancemesh.common.algorithms.domain.HeatDraw<P>>
			= (1..numberOfDances).map({ algo.draw(participants, numberOfHeats) })
}