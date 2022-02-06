package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.domain.Scoring
import com.dancemesh.common.algorithms.domain.Ranking

/**
 * An algorithm creating a ranking from a list of scorings.
 */
interface MultiRankingAlgorithm<S> {
//interface RankingAlgorithm<S: Score> {
	
	fun <C> getRanking (scorings: List<Scoring<C, S>>): Ranking<C>
}