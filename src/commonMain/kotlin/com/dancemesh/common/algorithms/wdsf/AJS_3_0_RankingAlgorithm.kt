package com.dancemesh.common.algorithms.wdsf

import com.dancemesh.common.algorithms.domain.CompositeMark
import com.dancemesh.common.algorithms.domain.CompositeScore
import com.dancemesh.common.algorithms.ranking.CompositeRankingAlgorithm
import kotlin.math.absoluteValue

enum class AJS_3_0_Components {
    TQ, // Technical Qualities
    PS, // Movement to Music
    MM, // Partnering Skill
    CP, // Choreography and Presentation
}

class AJS_3_0_Mark(map: Map<AJS_3_0_Components, Double>) : CompositeMark<AJS_3_0_Components>(map) {

    companion object {
        fun of(tq: Double, ps: Double, mm: Double, cp: Double): AJS_3_0_Mark {
            return AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.TQ to tq,
                    AJS_3_0_Components.PS to ps,
                    AJS_3_0_Components.MM to mm,
                    AJS_3_0_Components.CP to cp,
                )
            )
        }
        fun ofTQPS(mark: Double): AJS_3_0_Mark {
            return AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.TQ to mark,
                    AJS_3_0_Components.PS to mark,
                )
            )
        }
        fun ofMMCP(mark: Double): AJS_3_0_Mark {
            return AJS_3_0_Mark(
                mapOf(
                    AJS_3_0_Components.MM to mark,
                    AJS_3_0_Components.CP to mark,
                )
            )
        }
    }
}

class AJS_3_0_Score(marks: Collection<CompositeMark<AJS_3_0_Components>>) :
    CompositeScore<AJS_3_0_Components>(marks) {

    override fun aggregate(marks: List<Double>): Double =
        filterByTolerance(marks, 1.5).average()

    companion object {

        fun filterByTolerance(marks: List<Double>, tolerance: Double): List<Double> {
            val med = marks.median()
            return marks.filter { (it - med).absoluteValue <= tolerance }
        }
    }
}

object AJS_3_0_RankingAlgorithm : CompositeRankingAlgorithm<AJS_3_0_Components, AJS_3_0_Score>()

fun Iterable<Double>.median(): Double = this.sorted().let {
    if (it.size % 2 == 0)
        (it[it.size / 2] + it[(it.size - 1) / 2]) / 2
    else
        it[it.size / 2]
}
