package com.dancemesh.common.algorithms.domain

/**
 * A mutable heat draw relates a set of participants to heats and can be modified after instantiation.
 */
interface MutableHeatDraw<P>: com.dancemesh.common.algorithms.domain.HeatDraw<P> {
	
	fun setHeat (participant: P, heat: Int)
}