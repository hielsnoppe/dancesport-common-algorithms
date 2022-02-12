package com.dancemesh.common.algorithms.domain

import com.dancemesh.common.algorithms.util.IntegerInterval

class RankingImpl<C> : MutableRanking<C> {
	
    private var map: HashMap<C, IntegerInterval> = HashMap<C, IntegerInterval>()
	
    override fun getRank(candidate: C): IntegerInterval = map.getValue(candidate)
	
    override fun putRank(candidate: C, rank: IntegerInterval) {
        map[candidate] = rank
    }
	
    override fun putScore(candidate: C, score: IntegerInterval) = putRank(candidate, score)
	
    override fun getCandidates() = this.map.keys
	
    override fun getScore(candidate: C): IntegerInterval = getRank(candidate)
	
    override fun getScores() = map.entries.map { e -> Pair(e.key, e.value) }.toSet()
	
    override fun isCompatible(other: Scoring<C, IntegerInterval>): Boolean =
        this.getCandidates() == other.getCandidates()
	
    override fun count(): Int = map.count()
	
    override fun equals(other: Any?): Boolean {
		
        if (other !is Ranking<*>) return false
        if (getCandidates() != other.getCandidates()) return false
        other as Ranking<C>
        return this.map.keys.map { c -> getRank(c) == other.getRank(c) }.all { x -> x }
    }
}
