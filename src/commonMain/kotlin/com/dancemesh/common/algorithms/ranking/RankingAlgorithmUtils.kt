package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.domain.MutableRanking
import com.dancemesh.common.algorithms.util.IntegerInterval
import com.dancemesh.common.algorithms.domain.Ranking
import com.dancemesh.common.algorithms.domain.RankingImpl

object RankingAlgorithmUtils {
	
	fun <C, V: Comparable<V>> getRanking (table: Set<Pair<C, V>>): Ranking<C> {
		
		val ranking: MutableRanking<C> = RankingImpl<C>();
		
		table
			.groupBy { it.second } // group by number of marks
			.entries.asSequence()
			.sortedBy { it.key }
			.map { it.value.toSet() } // convert sorted set to list of groups
			.fold(ranking) { acc, it -> splitRanks(acc, it) }

		return ranking;
	}

	fun <C, V: Comparable<V>> getRankingDescending (table: Set<Pair<C, V>>): Ranking<C> {

		val ranking: MutableRanking<C> = RankingImpl<C>();

		table
			.groupBy { it.second } // group by number of marks
			.entries.asSequence()
			.sortedByDescending { it.key }
			.map { it.value.toSet() } // convert sorted set to list of groups
			.fold(ranking) { acc, it -> splitRanks(acc, it) }

		return ranking;
	}
	
	private fun <C> splitRanks (acc: MutableRanking<C>, rows: Set<Pair<C, Any>>): MutableRanking<C> {
		
		val min = acc.count() + 1
		val max = acc.count() + rows.count()
		
		rows.forEach {
			acc.putRank(it.first, IntegerInterval(min, max))
		}

		return acc
	}
}