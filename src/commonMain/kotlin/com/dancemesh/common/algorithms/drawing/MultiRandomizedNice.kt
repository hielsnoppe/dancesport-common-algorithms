package com.dancemesh.common.algorithms.drawing

import com.dancemesh.common.algorithms.domain.HeatDraw
import com.dancemesh.common.algorithms.domain.HeatDrawImpl
import com.dancemesh.common.algorithms.domain.MutableHeatDraw

/**
 * An algorithm creating random heat draws for multiple dances in which, when possible,
 * a competitor is not assigned to the first heat of a dance if they are also assigned to the last heat of the previous dance.
 */
class MultiRandomizedNice<P>() : MultiHeatDrawingAlgorithm<P> {
	
    val algo = Randomized<P>()
	
    override fun draw(participants: Set<P>, numberOfHeats: Int, numberOfDances: Int): List<HeatDraw<P>> {
		
        return (1..numberOfDances).fold(mutableListOf<HeatDraw<P>>(), fun (acc: MutableList<HeatDraw<P>>, it: Int): MutableList<HeatDraw<P>> {
			
            if (it == 1) return mutableListOf(algo.draw(participants, numberOfHeats))
			
            val participantsPerHeat: Int = participants.count() / numberOfHeats
			
            val affected = acc.last().getLastHeat()
            val uninvolved = participants - affected
            val firstHeat = uninvolved.shuffled().take(participantsPerHeat)
            val otherHeat = (uninvolved - firstHeat + affected).shuffled()
			
            val draw: MutableHeatDraw<P> = HeatDrawImpl<P>()
			
            firstHeat.foldIndexed(draw, fun (index, acc, it): MutableHeatDraw<P> {
                acc.setHeat(it, 1)
                return acc
            })
			
            otherHeat.foldIndexed(draw, fun (index, acc, it): MutableHeatDraw<P> {
                acc.setHeat(it, index % (numberOfHeats - 1) + 2)
                return acc
            })
			
            acc.add(draw)
            return acc
        })
    }
}
