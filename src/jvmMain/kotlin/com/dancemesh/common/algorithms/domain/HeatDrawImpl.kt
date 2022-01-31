package com.dancemesh.common.algorithms.domain

class HeatDrawImpl<P>: com.dancemesh.common.algorithms.domain.MutableHeatDraw<P> {

	var map: MutableMap<P, Int> = mutableMapOf()
	
	override fun getNumberOfHeats (): Int = map.values.maxOrNull() ?: 0
	
	override fun getNumberOfParticipants (): Int = map.count()
	
	override fun getParticipant (participant: P): Int = map.getValue(participant)
	
	
	override fun getParticipants () = map.keys
	
	override fun getHeat (heat: Int): Set<P> =
		map.filter({ e -> e.value == heat }).keys
	
	override fun setHeat (participant: P, heat: Int) {
		map.put(participant, heat)
	}
	
	override fun getFirstHeat () = getHeat(1)
	override fun getLastHeat () = getHeat(getNumberOfHeats())
}