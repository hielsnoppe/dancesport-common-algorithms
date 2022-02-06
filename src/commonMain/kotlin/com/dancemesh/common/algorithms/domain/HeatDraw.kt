package com.dancemesh.common.algorithms.domain

/**
 * A heat draw relates a set of participants to heats.
 */
interface HeatDraw<P> {

	/**
	 * Get the set of participants.
	 */
	fun getParticipants (): Set<P>

	/**
	 * Get the number of heats.
	 */
	fun getNumberOfHeats (): Int

	/**
	 * Get the number of participants.
	 */
	fun getNumberOfParticipants (): Int

	/**
	 * Get the heat assigned to a participant.
	 */
	fun getParticipant (participant: P): Int

	/**
	 * Get the set of participants assigned to a heat.
	 */
	fun getHeat (heat: Int): Set<P>

	/**
	 * Get the set of participants assigned to the first heat.
	 */
	fun getFirstHeat (): Set<P>

	/**
	 * Get the set of participants assigned to the last heat.
	 */
	fun getLastHeat (): Set<P>
}