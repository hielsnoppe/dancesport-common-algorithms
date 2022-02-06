package com.dancemesh.common.algorithms.domain

class ScoringImpl<C, S>: MutableScoring<C, S> {
//class ScoringImpl<C, S: Score>: MutableScoring<C, S> {
	
	private var map: HashMap<C, S> = HashMap<C, S>()
	
	override fun count (): Int = map.count()
	
	override fun getScore(candidate: C): S = map.getValue(candidate)
	
	override fun getScores () = map.entries.map { e -> Pair(e.key, e.value) }.toSet()
	
	override fun putScore(candidate: C, score: S) {
		map[candidate] = score
	}
	
	override fun isCompatible (other: Scoring<C, S>): Boolean =
		this.getCandidates() == other.getCandidates();
	
	override fun getCandidates () = this.map.keys
}