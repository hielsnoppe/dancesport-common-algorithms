package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.domain.Scoring
import com.dancemesh.common.algorithms.domain.Ranking

interface RankingAlgorithm<S> {
//interface RankingAlgorithm<S: Score> {
	
	fun <C> getRanking (scoring: Scoring<C, S>): Ranking<C>
}