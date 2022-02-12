package com.dancemesh.common.algorithms.drawing

import com.dancemesh.common.algorithms.domain.HeatDraw

/**
 * An algorithm creating heat draws for multiple dances.
 */
interface MultiHeatDrawingAlgorithm<P> {
	
    fun draw(participants: Set<P>, numberOfHeats: Int, numberOfDances: Int): List<HeatDraw<P>>
}
