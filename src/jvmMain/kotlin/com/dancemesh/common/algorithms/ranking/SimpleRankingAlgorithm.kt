package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.domain.Ranking
import com.dancemesh.common.algorithms.domain.Scoring
import java.math.BigDecimal

abstract class SimpleRankingAlgorithm<S, V: Comparable<V>>:
	RankingAlgorithm<S> {
	
	override fun <C> getRanking (scoring: Scoring<C, S>): Ranking<C> {
		
		val table = scoring.getCandidates()
				.map { Pair(it, aggregate(scoring.getScore(it))) }
			.toSet()
		
		return RankingAlgorithmUtils.getRanking(table)
	}
	
	abstract fun aggregate (score: S): V
}

/**
 * Use this for scores such as 1, 1.5, 2, 2.5, 3
 */
object MarksSumRankingAlgorithm:
		SimpleRankingAlgorithm<List<BigDecimal>, BigDecimal>() {

	override fun aggregate (score: List<BigDecimal>): BigDecimal
		= score.reduce { a, b -> a + b }
}

/**
 * Use this for binary scores (+/-)
 */
object MarksCountRankingAlgorithm:
		SimpleRankingAlgorithm<List<Boolean>, Int>() {
	
	override fun aggregate (score: List<Boolean>): Int
		= score.count { !it }
}