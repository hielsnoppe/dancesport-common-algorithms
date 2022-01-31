package com.dancemesh.common.algorithms.domain

interface Scoring<C, S> {
//interface Scoring<C, S: Score> {
	
	fun getScore (candidate: C): S
	fun getScores (): Set<Pair<C, S>>
	fun isCompatible (other: Scoring<C, S>): Boolean
	fun getCandidates (): Set<C>
	fun count (): Int
}