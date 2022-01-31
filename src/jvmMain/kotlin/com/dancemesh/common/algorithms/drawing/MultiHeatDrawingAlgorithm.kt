package com.dancemesh.common.algorithms.drawing

import com.dancemesh.common.algorithms.domain.HeatDraw

interface MultiHeatDrawingAlgorithm<P> {
	
	fun draw (participants: Set<P>, numberOfHeats: Int, numberOfDances: Int): List<com.dancemesh.common.algorithms.domain.HeatDraw<P>>
}