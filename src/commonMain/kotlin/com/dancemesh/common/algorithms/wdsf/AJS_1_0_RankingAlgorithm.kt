package com.dancemesh.common.algorithms.wdsf

import com.dancemesh.common.algorithms.domain.CompositeMark
import com.dancemesh.common.algorithms.domain.CompositeScore
import com.dancemesh.common.algorithms.ranking.CompositeRankingAlgorithm

enum class AJS_1_0_Components {
    PB, // Posture, Balance, Coordination
    QM, // Quality of Movement
    MM, // Movement to Music
    PS, // Partnering Skill
    CP, // Choreography and Presentation
}

class AJS_1_0_Mark(map: Map<AJS_1_0_Components, Double>) : CompositeMark<AJS_1_0_Components>(map) {

    companion object {
        fun of(pb: Double, qm: Double, mm: Double, ps: Double, cp: Double): AJS_1_0_Mark {
            return AJS_1_0_Mark(
                mapOf(
                    AJS_1_0_Components.PB to pb,
                    AJS_1_0_Components.QM to qm,
                    AJS_1_0_Components.MM to mm,
                    AJS_1_0_Components.PS to ps,
                    AJS_1_0_Components.CP to cp
                )
            )
        }
    }
}

class AJS_1_0_Score(marks: Collection<CompositeMark<AJS_1_0_Components>>) :
    CompositeScore<AJS_1_0_Components>(marks) {

    override fun aggregate(marks: List<Double>): Double =
        marks.sorted().drop(1).dropLast(1).average()
}

object AJS_1_0_RankingAlgorithm : CompositeRankingAlgorithm<AJS_1_0_Components, AJS_1_0_Score>()
