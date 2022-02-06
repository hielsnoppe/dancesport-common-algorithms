package com.dancemesh.common.algorithms.domain

/**
 * A mutable scoring relates a set of candidates to scores and can be modified after instantiation.
 */
interface MutableScoring<C, S>: Scoring<C, S> {
//interface MutableScoring<C, S: Score>: Scoring<C, S> {

	/**
	 * Assign a score to a candidate.
	 */
	fun putScore (candidate: C, score: S)
}