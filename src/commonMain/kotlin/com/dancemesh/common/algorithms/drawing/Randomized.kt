package com.dancemesh.common.algorithms.drawing

import com.dancemesh.common.algorithms.domain.HeatDraw
import com.dancemesh.common.algorithms.domain.HeatDrawImpl
import com.dancemesh.common.algorithms.domain.MutableHeatDraw

/**
 * An algorithm creating random heat draws for individual dances.
 */
class Randomized<P> : HeatDrawingAlgorithm<P> {
	
    override fun draw(participants: Set<P>, numberOfHeats: Int): HeatDraw<P> =
        participants.shuffled().foldIndexed(HeatDrawImpl(), fun (index, acc, it): MutableHeatDraw<P> {
            acc.setHeat(it, index % numberOfHeats + 1)
            return acc
        })
}
