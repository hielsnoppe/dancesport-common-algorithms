package com.dancemesh.common.algorithms.drawing

import com.dancemesh.common.algorithms.domain.HeatDraw

/**
 * An algorithm creating heat draws for individual dances.
 */
interface HeatDrawingAlgorithm<P> {
	
	fun draw (participants: Set<P>, numberOfHeats: Int): com.dancemesh.common.algorithms.domain.HeatDraw<P>
}