package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.domain.MutableRanking
import com.dancemesh.common.algorithms.domain.MutableScoring
import com.dancemesh.common.algorithms.domain.RankingImpl
import com.dancemesh.common.algorithms.domain.ScoringImpl
import com.dancemesh.common.algorithms.util.IntegerInterval
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.util.stream.Stream

object DanceScoringProvider : ArgumentsProvider {
	
    override fun provideArguments(context: ExtensionContext): Stream<Arguments> =
        rawData.map { Arguments.of(getScoring(it), getRanking(it)) }.stream()

    fun getRanking(data: Array<IntArray>) =
        data.fold(RankingImpl<Int>(), fun (acc, it): MutableRanking<Int> {
            acc.putRank(it[0], IntegerInterval(it[1], it[2]))
            return acc
        })
	
    fun getScoring(data: Array<IntArray>) =
        data.fold(ScoringImpl<Int, List<Boolean>>(), fun (acc, it): MutableScoring<Int, List<Boolean>> {
            acc.putScore(it[0], it.drop(3).map { it.equals(1) }.toList())
            return acc
        })
	
    val rawData = arrayOf(
        arrayOf<IntArray>(
            intArrayOf(101, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(102, 2, 2, 1, 1, 1, 1, 0),
            intArrayOf(103, 3, 3, 1, 1, 1, 0, 0),
            intArrayOf(104, 4, 4, 1, 1, 0, 0, 0),
            intArrayOf(105, 5, 5, 1, 0, 0, 0, 0),
            intArrayOf(106, 6, 6, 0, 0, 0, 0, 0)
        ),
        arrayOf<IntArray>(
            intArrayOf(101, 1, 2, 1, 1, 1, 1, 1),
            intArrayOf(102, 1, 2, 1, 1, 1, 1, 1),
            intArrayOf(103, 3, 3, 1, 1, 1, 0, 0),
            intArrayOf(104, 4, 5, 1, 1, 0, 0, 0),
            intArrayOf(105, 4, 5, 1, 1, 0, 0, 0),
            intArrayOf(106, 6, 6, 0, 0, 0, 0, 0)
        )
    )
}
