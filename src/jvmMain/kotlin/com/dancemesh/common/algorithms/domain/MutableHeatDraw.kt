package com.dancemesh.common.algorithms.domain

interface MutableHeatDraw<P>: com.dancemesh.common.algorithms.domain.HeatDraw<P> {
	
	fun setHeat (participant: P, heat: Int)
}