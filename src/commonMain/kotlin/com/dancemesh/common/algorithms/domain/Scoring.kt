package com.dancemesh.common.algorithms.domain

/**
 * A scoring relates a set of candidates to scores.
 */
interface Scoring<C, S> {
// interface Scoring<C, S: Score> {

    /**
     * Get the score assigned to a candidate.
     */
    fun getScore(candidate: C): S

    /**
     * Get a set of pairs of candidates and assigned scores.
     */
    fun getScores(): Set<Pair<C, S>>

    /**
     * Two scorings are compatible iff they have the same set of candidates.
     */
    fun isCompatible(other: Scoring<C, S>): Boolean

    /**
     * Get the set of candidates.
     */
    fun getCandidates(): Set<C>

    /**
     * Get the number of candidates.
     */
    fun count(): Int
}
