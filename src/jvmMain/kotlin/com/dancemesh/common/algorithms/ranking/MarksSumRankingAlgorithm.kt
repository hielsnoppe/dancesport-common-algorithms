package com.dancemesh.common.algorithms.ranking

import java.math.BigDecimal

/**
 * Use this for scores such as 1, 1.5, 2, 2.5, 3
 */
object MarksSumRankingAlgorithm:
    SimpleRankingAlgorithm<List<BigDecimal>, BigDecimal>() {

    override fun aggregate (score: List<BigDecimal>): BigDecimal
            = score.reduce { a, b -> a + b }
}