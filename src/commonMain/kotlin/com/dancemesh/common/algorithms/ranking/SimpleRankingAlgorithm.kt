package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.domain.CompositeScore
import com.dancemesh.common.algorithms.domain.MutableScoring
import com.dancemesh.common.algorithms.domain.Ranking
import com.dancemesh.common.algorithms.domain.Scoring
import com.dancemesh.common.algorithms.wdsf.AJS_1_0_Score
import com.dancemesh.common.algorithms.wdsf.AJS_3_0_Score

abstract class SimpleRankingAlgorithm<S, V: Comparable<V>>(val descending: Boolean = false):
	RankingAlgorithm<S> {

	override fun <C> getRanking (scoring: Scoring<C, S>): Ranking<C> {
		
		val table = scoring.getCandidates()
			.map { Pair(it, aggregate(scoring.getScore(it))) }
			.toSet()
		
		return (
			if (descending) RankingAlgorithmUtils.getRankingDescending(table)
			else RankingAlgorithmUtils.getRanking(table)
		)
	}
	
	abstract fun aggregate (score: S): V
}

open class CompositeRankingAlgorithm<C, S: CompositeScore<C>> : SimpleRankingAlgorithm<S, Double>(true) {

	override fun aggregate(score: S): Double {
		return score.getComponents().sumOf { score.getScore(it) }
	}
}

/**
 * Use this for binary scores (+/-)
 */
object MarksCountRankingAlgorithm:
		SimpleRankingAlgorithm<List<Boolean>, Int>() {
	
	override fun aggregate (score: List<Boolean>): Int
		= score.count { !it }
}