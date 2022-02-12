package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.domain.MutableRanking
import com.dancemesh.common.algorithms.domain.MutableScoring
import com.dancemesh.common.algorithms.domain.RankingImpl
import com.dancemesh.common.algorithms.domain.ScoringImpl
import com.dancemesh.common.algorithms.util.IntegerInterval
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.stream.Stream

object DanceTeamScoringProvider : ArgumentsProvider {
	
    override fun provideArguments(context: ExtensionContext): Stream<Arguments> =
        rawData.map { Arguments.of(getScoring(it), getRanking(it)) }.stream()

    fun getRanking(data: Array<IntArray>) =
        data.fold(RankingImpl<Int>(), fun (acc, it): MutableRanking<Int> {
            acc.putRank(it[0], IntegerInterval(it[1], it[2]))
            return acc
        })
	
    fun getScoring(data: Array<IntArray>) =
        data.fold(ScoringImpl<Int, List<BigDecimal>>(), fun (acc, it): MutableScoring<Int, List<BigDecimal>> {
            acc.putScore(
                it[0],
                it.drop(3).map {
                    it.toBigDecimal().divide(10.toBigDecimal(), 1, RoundingMode.HALF_UP)
                }.toList()
            )
            return acc
        })
	
    val rawData = arrayOf(
        arrayOf<IntArray>(
            intArrayOf(101, 1, 1, 10, 10, 10, 10, 10),
            intArrayOf(102, 2, 2, 15, 15, 15, 15, 15),
            intArrayOf(103, 3, 3, 15, 20, 20, 20, 20),
            intArrayOf(104, 4, 4, 20, 20, 20, 20, 25),
            intArrayOf(105, 5, 5, 25, 25, 25, 25, 25),
            intArrayOf(106, 6, 6, 30, 30, 30, 30, 30)
        ),
        arrayOf<IntArray>(
            intArrayOf(201, 1, 1, 10, 10, 10, 10, 10),
            intArrayOf(202, 2, 2, 15, 15, 15, 15, 15),
            intArrayOf(203, 3, 4, 20, 20, 20, 20, 20),
            intArrayOf(204, 3, 4, 20, 20, 20, 20, 20),
            intArrayOf(205, 5, 5, 25, 25, 25, 25, 25),
            intArrayOf(206, 6, 6, 30, 30, 30, 30, 30)
        )
    )
}
