package com.dancemesh.common.algorithms.domain

interface HeatDraw<P> {
	
	fun getParticipants (): Set<P>
	fun getNumberOfHeats (): Int
	fun getNumberOfParticipants (): Int
	fun getParticipant (participant: P): Int
	fun getHeat (heat: Int): Set<P>
	fun getFirstHeat (): Set<P>
	fun getLastHeat (): Set<P>
}