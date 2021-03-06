package com.dancemesh.common.algorithms.drawing

import com.dancemesh.common.algorithms.domain.HeatDraw

/**
 * An algorithm creating heat draws for multiple dances in which the heat draws for each individual dance are the same.
 */
class MultiFixed<P>(algo: HeatDrawingAlgorithm<P>) : MultiHeatDrawingAlgorithm<P> {
	
    val algo: HeatDrawingAlgorithm<P> = algo
	
    override fun draw(participants: Set<P>, numberOfHeats: Int, numberOfDances: Int): List<HeatDraw<P>> {
		
        val draw = algo.draw(participants, numberOfHeats)
        return (1..numberOfDances).map { draw }
    }
}
