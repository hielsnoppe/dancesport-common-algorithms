package com.dancemesh.common.algorithms.domain

import com.dancemesh.common.algorithms.util.IntegerInterval

interface MutableRanking<C>: Ranking<C>, MutableScoring<C, IntegerInterval> {
	
	fun putRank (candidate: C, rank: IntegerInterval)
}