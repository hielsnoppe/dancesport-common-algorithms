package com.dancemesh.common.algorithms.domain

typealias SimpleHeatDraw = Map<Any, Int>

fun SimpleHeatDraw.getParticipantsByHeat (heat: Int): List<Any> =
		this.entries.groupBy({it.value}, {it.key}).getValue(heat)