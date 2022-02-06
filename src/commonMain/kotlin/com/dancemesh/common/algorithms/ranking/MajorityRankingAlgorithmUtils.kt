package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.util.IntegerInterval
import com.dancemesh.common.algorithms.util.IntFraction
import com.dancemesh.common.algorithms.domain.Scoring
import kotlin.math.ceil

data class Entry (val count: Int, val sum: IntFraction): Comparable<Entry> {
			
	constructor (pair: Pair<Int, IntFraction>):
			this(pair.component1(), pair.component2())
	
	override fun compareTo (other: Entry): Int {
		if (count > other.count) return 1;
		if (count < other.count) return -1;
		if (sum < other.sum) return 1;
		if (sum > other.sum) return -1;
		
		return 0;
	}
	
	override fun toString (): String = "$count ($sum)"
}

object MajorityRankingAlgorithmUtils {
	
	fun getMajority (total: Int): Int = ceil((total + 1) / 2.0).toInt()
	
	fun <C> getMajority (scoring: Scoring<C, List<IntegerInterval>>) =
			getMajority(scoring.getScore(scoring.getCandidates().first()).count())
	
	fun countAndSum (ranks: List<IntegerInterval>, maxRank: Int): Array<Entry> = Array<Entry>(maxRank) { column ->

		val x = ranks.filter { r -> r >= IntegerInterval(column + 1) }
		val sum: IntFraction =
			x.fold(IntFraction(0)) { acc: IntFraction, item: IntegerInterval -> acc + item.average() }

		Entry(x.count(), sum)
	}

	fun sumByIntFraction (items: List<IntegerInterval>): IntFraction {
		
		return items.fold(IntFraction(0)) { acc: IntFraction, it: IntegerInterval -> acc + it.average() }
	}
	
	fun <T> chunkedWith (rows: Set<T>, comp: Comparator<T>): List<Set<T>> {
		
		if (rows.isEmpty()) return listOf()
		
		val top = rows.maxWithOrNull(comp)!!
		val parts = rows.partition { row -> comp.compare(row, top) == 0 }

		return listOf(parts.first.toSet()) + chunkedWith(parts.second.toSet(), comp)
	}
}