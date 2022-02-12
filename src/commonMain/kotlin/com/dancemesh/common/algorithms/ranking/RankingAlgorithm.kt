package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.domain.Ranking
import com.dancemesh.common.algorithms.domain.Scoring

/**
 * An algorithm creating a ranking from a scoring.
 */
interface RankingAlgorithm<S> {
// interface RankingAlgorithm<S: Score> {
	
    fun <C> getRanking(scoring: Scoring<C, S>): Ranking<C>
}
