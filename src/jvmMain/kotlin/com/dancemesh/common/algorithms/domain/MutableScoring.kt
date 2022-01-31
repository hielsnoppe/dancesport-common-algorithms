package com.dancemesh.common.algorithms.domain

interface MutableScoring<C, S>: Scoring<C, S> {
//interface MutableScoring<C, S: Score>: Scoring<C, S> {
	
	fun putScore (candidate: C, score: S)
}