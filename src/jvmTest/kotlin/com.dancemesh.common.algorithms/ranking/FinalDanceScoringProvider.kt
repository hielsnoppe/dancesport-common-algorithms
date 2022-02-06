package com.dancemesh.common.algorithms.ranking

import com.dancemesh.common.algorithms.domain.MutableRanking
import com.dancemesh.common.algorithms.domain.MutableScoring
import com.dancemesh.common.algorithms.domain.RankingImpl
import com.dancemesh.common.algorithms.domain.ScoringImpl
import com.dancemesh.common.algorithms.util.IntegerInterval
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.api.extension.ExtensionContext
import java.util.stream.Stream
import org.junit.jupiter.params.provider.Arguments

object FinalDanceScoringProvider: ArgumentsProvider {
	
	override fun provideArguments (context: ExtensionContext): Stream<Arguments> =
			rawData.map { Arguments.of(getScoring(it), getRanking(it)) }.stream()

	fun getRanking (data: Array<IntArray>) =
		data.fold(RankingImpl<Int>(), fun (acc, it): MutableRanking<Int> {
			acc.putRank(it[0], IntegerInterval(it[1], it[2]))
			return acc
		})
	
	fun getScoring (data: Array<IntArray>) =
		data.fold(ScoringImpl<Int, List<IntegerInterval>>(), fun (acc, it): MutableScoring<Int, List<IntegerInterval>> {
			acc.putScore(it[0], it.drop(3).map({x -> IntegerInterval(x)}).toList())
			return acc
		})
	
	val rawData = arrayOf(
		// jUdge BEISPIEL_B
		arrayOf<IntArray>(
				intArrayOf(101, 1,1, 1, 1, 1, 4, 4),
				intArrayOf(102, 2,2, 3, 2, 2, 1, 1),
				intArrayOf(103, 3,3, 2, 5, 5, 2, 2),
				intArrayOf(104, 4,4, 4, 3, 4, 5, 3),
				intArrayOf(105, 5,5, 5, 4, 3, 3, 5),
				intArrayOf(106, 6,6, 6, 6, 6, 6, 6)
		),
		// jUdge BEISPIEL_P
		arrayOf<IntArray>( // SW
				intArrayOf(101, 2,2, 2, 2, 5, 2, 5),
				intArrayOf(102, 4,4, 3, 3, 6, 3, 6),
				intArrayOf(103, 3,3, 4, 5, 3, 1, 1),
				intArrayOf(104, 1,1, 1, 1, 2, 5, 3),
				intArrayOf(105, 5,5, 6, 6, 1, 4, 4),
				intArrayOf(106, 6,6, 5, 4, 4, 6, 2)
		),
		arrayOf<IntArray>( // TG
				intArrayOf(101, 1,1, 1, 2, 2, 3, 4),
				intArrayOf(102, 3,3, 3, 1, 3, 1, 3),
				intArrayOf(103, 5,5, 2, 5, 5, 5, 5),
				intArrayOf(104, 4,4, 4, 4, 4, 4, 1),
				intArrayOf(105, 6,6, 6, 6, 6, 6, 6),
				intArrayOf(106, 2,2, 5, 3, 1, 2, 2)
		),
		/*
		arrayOf<IntArray>( // VW
				intArrayOf(101, 1,1, 1, 1, 1, 1, 1),
				intArrayOf(102, 4,4, 2, 3, 4, 5, 6),
				intArrayOf(103, 4,4, 3, 4, 5, 6, 2),
				intArrayOf(104, 4,4, 4, 5, 6, 2, 3),
				intArrayOf(105, 2,2, 5, 2, 2, 3, 4),
				intArrayOf(106, 6,6, 6, 6, 3, 4, 5)
		),
		*/
		arrayOf<IntArray>( // SF
				intArrayOf(101, 3,3, 3, 3, 1, 3, 6),
				intArrayOf(102, 1,1, 1, 1, 2, 5, 2),
				intArrayOf(103, 5,5, 4, 5, 4, 4, 1),
				intArrayOf(104, 4,4, 5, 6, 3, 2, 3),
				intArrayOf(105, 2,2, 2, 2, 5, 1, 5),
				intArrayOf(106, 6,6, 6, 4, 6, 6, 4)
		),
		arrayOf<IntArray>( // QS
			 	intArrayOf(101, 2,2, 2, 6, 2, 6, 1),
				intArrayOf(102, 6,6, 4, 4, 6, 3, 6),
				intArrayOf(103, 1,1, 1, 5, 1, 5, 2),
				intArrayOf(104, 5,5, 6, 3, 3, 4, 5),
				intArrayOf(105, 4,4, 5, 1, 5, 1, 4),
				intArrayOf(106, 3,3, 3, 2, 4, 2, 3)
		),
		// Beispiel A - Regel 5, TSO, S. 102 (PDF)
		arrayOf<IntArray>(
				intArrayOf(101, 1,1, 1, 5, 1, 1, 2),
				intArrayOf(102, 2,2, 2, 2, 5, 4, 1),
				intArrayOf(103, 3,3, 3, 3, 3, 2, 3),
				intArrayOf(104, 4,4, 4, 4, 2, 3, 4),
				intArrayOf(105, 5,5, 5, 1, 4, 5, 5),
				intArrayOf(106, 6,6, 6, 6, 6, 6, 6)
				),
		// Beispiel B - Regel 6, TSO, S. 103 (PDF)
		arrayOf<IntArray>(
				intArrayOf(101, 1,1, 1, 1, 1, 4, 4),
				intArrayOf(102, 2,2, 3, 2, 2, 1, 1),
				intArrayOf(103, 3,3, 2, 5, 5, 2, 2),
				intArrayOf(104, 4,4, 4, 3, 4, 5, 3),
				intArrayOf(105, 5,5, 5, 4, 3, 3, 5),
				intArrayOf(106, 6,6, 6, 6, 6, 6, 6)
				),
		// Beispiel C - Regel 7a, TSO, S. 104 (PDF)"
		arrayOf<IntArray>(
				intArrayOf(101, 1,1, 1, 1, 1, 5, 5),
				intArrayOf(102, 2,2, 2, 2, 5, 1, 4),
				intArrayOf(103, 3,3, 5, 5, 2, 2, 2),
				intArrayOf(104, 4,4, 3, 3, 4, 6, 1),
				intArrayOf(105, 5,5, 4, 4, 3, 3, 3),
				intArrayOf(106, 6,6, 6, 6, 6, 4, 6)
				),	
		// Beispiel D - Regel 7b, TSO, S. 105 (PDF)"
		arrayOf<IntArray>(
				intArrayOf(101, 1,1, 2, 1, 5, 1, 1),
				intArrayOf(102, 2,2, 1, 2, 2, 5, 5),
				intArrayOf(103, 3,3, 5, 6, 1, 2, 2),
				intArrayOf(104, 4,4, 3, 3, 3, 3, 6),
				intArrayOf(105, 5,5, 4, 4, 4, 6, 4),
				intArrayOf(106, 6,6, 6, 5, 6, 4, 3)
				),
		// Beispiel E - Regel 8, TSO, S. 106 (PDF)"
		arrayOf<IntArray>(
				intArrayOf(101, 1,1, 1, 1, 3, 2, 3),
				intArrayOf(102, 2,2, 6, 5, 4, 1, 1),
				intArrayOf(103, 3,3, 2, 4, 1, 5, 5),
				intArrayOf(104, 4,4, 4, 2, 5, 6, 2),
				intArrayOf(105, 5,5, 5, 6, 2, 3, 4),
				intArrayOf(106, 6,6, 3, 3, 6, 4, 6)
				),
		// Beispiele aus der Schulung vom 5. April 2019
		arrayOf<IntArray>(
				intArrayOf(21, 6,6, 6, 5, 6, 4, 3),
				intArrayOf(22, 5,5, 4, 4, 4, 6, 4),
				intArrayOf(23, 3,3, 5, 6, 1, 2, 2),
				intArrayOf(24, 4,4, 3, 3, 3, 3, 6),
				intArrayOf(25, 2,2, 1, 2, 2, 5, 5),
				intArrayOf(26, 1,1, 2, 1, 5, 1, 1)
				),
		arrayOf<IntArray>(
				intArrayOf(21, 4,4, 4, 2, 5, 6, 2),
				intArrayOf(22, 2,2, 6, 5, 4, 1, 1),
				intArrayOf(23, 3,3, 2, 4, 1, 5, 5),
				intArrayOf(24, 1,1, 1, 1, 3, 2, 3),
				intArrayOf(25, 5,5, 5, 6, 2, 3, 4),
				intArrayOf(26, 6,6, 3, 3, 6, 4, 6)
				),
		arrayOf<IntArray>(
				intArrayOf(21, 1,1, 1, 1, 2, 5, 2),
				intArrayOf(22, 5,5, 4, 5, 4, 4, 1),
				intArrayOf(23, 2,2, 2, 2, 5, 1, 5),
				intArrayOf(24, 4,4, 5, 6, 3, 2, 3),
				intArrayOf(25, 6,6, 6, 4, 6, 6, 4),
				intArrayOf(26, 3,3, 3, 3, 1, 3, 6)
				),
		arrayOf<IntArray>(
				intArrayOf(21, 4,4, 5, 1, 5, 1, 3),
				intArrayOf(22, 2,2, 2, 6, 2, 6, 1),
				intArrayOf(23, 6,6, 4, 4, 6, 3, 6),
				intArrayOf(24, 5,5, 6, 3, 3, 4, 5),
				intArrayOf(25, 1,1, 1, 5, 1, 5, 2),
				intArrayOf(26, 3,3, 3, 2, 3, 2, 4)
				),
		// Pr�fung TL 2005, �bungsaufgabe 4, Regel 1 bis 9 
		arrayOf<IntArray>(
				intArrayOf(54, 1,1, 4, 1, 3, 2, 1, 1, 2),
				intArrayOf(58, 5,5, 5, 4, 4, 5, 6, 5, 3),
				intArrayOf(60, 6,6, 6, 2, 5, 6, 4, 6, 6),
				intArrayOf(61, 2,2, 1, 5, 2, 1, 2, 4, 4),
				intArrayOf(68, 3,3, 2, 6, 1, 4, 3, 3, 1),
				intArrayOf(77, 4,4, 3, 3, 6, 3, 5, 2, 5)
				),
		arrayOf<IntArray>(
				intArrayOf(54, 1,1, 3, 1, 3, 2, 1, 1, 2),
				intArrayOf(58, 5,5, 5, 6, 4, 5, 5, 4, 4),
				intArrayOf(60, 6,6, 6, 4, 5, 6, 4, 6, 6),
				intArrayOf(61, 2,3, 1, 3, 2, 1, 2, 5, 3),
				intArrayOf(68, 2,3, 2, 5, 1, 3, 3, 2, 1),
				intArrayOf(77, 4,4, 4, 2, 6, 4, 6, 3, 5)
				),
		arrayOf<IntArray>(
				intArrayOf(54, 1,1, 3, 1, 3, 1, 1, 1, 2),
				intArrayOf(58, 4,4, 5, 6, 4, 5, 5, 5, 4),
				intArrayOf(60, 6,6, 6, 4, 5, 6, 4, 6, 6),
				intArrayOf(61, 2,2, 2, 2, 1, 2, 2, 4, 3),
				intArrayOf(68, 3,3, 1, 3, 2, 4, 3, 2, 1),
				intArrayOf(77, 5,5, 4, 5, 6, 3, 6, 3, 5)
				),
		arrayOf<IntArray>(
				intArrayOf(54, 1,1, 3, 1, 3, 1, 1, 2, 2),
				intArrayOf(58, 4,4, 5, 6, 4, 5, 5, 4, 5),
				intArrayOf(60, 6,6, 6, 3, 5, 6, 4, 6, 6),
				intArrayOf(61, 3,3, 2, 4, 1, 2, 2, 3, 3),
				intArrayOf(68, 2,2, 1, 2, 2, 4, 3, 1, 1),
				intArrayOf(77, 5,5, 4, 5, 6, 3, 6, 5, 4)
				),
		arrayOf<IntArray>(
				intArrayOf(54, 2,2, 3, 1, 3, 1, 2, 1, 2),
				intArrayOf(58, 6,6, 5, 6, 5, 6, 6, 5, 5),
				intArrayOf(60, 5,5, 6, 3, 4, 5, 4, 6, 6),
				intArrayOf(61, 3,3, 2, 4, 2, 2, 3, 3, 3),
				intArrayOf(68, 1,1, 1, 2, 1, 3, 1, 2, 1),
				intArrayOf(77, 4,4, 4, 5, 6, 4, 5, 4, 4)
				),
		// Pr�fung TL 2005, �bungsaufgabe 5, Regel 1 bis 9
		arrayOf<IntArray>(
				intArrayOf(42, 2,2, 3, 6, 2, 5, 2),
				intArrayOf(45, 3,3, 5, 3, 5, 3, 3),
				intArrayOf(46, 4,4, 1, 4, 6, 2, 4),
				intArrayOf(51, 1,1, 2, 2, 3, 6, 1),
				intArrayOf(53, 6,6, 4, 5, 1, 4, 6),
				intArrayOf(54, 5,5, 6, 1, 4, 1, 5)
				),
		arrayOf<IntArray>(
				intArrayOf(42, 3,3, 4, 4, 2, 5, 2),
				intArrayOf(45, 2,2, 5, 3, 5, 3, 1),
				intArrayOf(46, 6,6, 1, 6, 6, 2, 6),
				intArrayOf(51, 4,4, 3, 2, 4, 6, 4),
				intArrayOf(53, 5,5, 6, 5, 3, 4, 5),
				intArrayOf(54, 1,1, 2, 1, 1, 1, 3)
				),
		arrayOf<IntArray>(
				intArrayOf(42, 3,3, 4, 5, 2, 4, 3),
				intArrayOf(45, 5,5, 5, 3, 5, 5, 4),
				intArrayOf(46, 4,4, 1, 4, 6, 2, 6),
				intArrayOf(51, 2,2, 3, 2, 4, 6, 2),
				intArrayOf(53, 6,6, 6, 6, 3, 3, 5),
				intArrayOf(54, 1,1, 2, 1, 1, 1, 1)
				),
		arrayOf<IntArray>(
				intArrayOf(42, 2,2, 2, 4, 2, 4, 3),
				intArrayOf(45, 3,3, 5, 3, 4, 3, 4),
				intArrayOf(46, 5,5, 1, 5, 6, 1, 5),
				intArrayOf(51, 4,4, 4, 2, 5, 6, 2),
				intArrayOf(53, 6,6, 6, 6, 3, 5, 6),
				intArrayOf(54, 1,1, 3, 1, 1, 2, 1)
				),
		arrayOf<IntArray>(
				intArrayOf(42, 2,2, 3, 3, 2, 4, 4),
				intArrayOf(45, 3,3, 5, 4, 3, 3, 3),
				intArrayOf(46, 5,5, 2, 5, 6, 2, 5),
				intArrayOf(51, 4,4, 4, 2, 4, 5, 2),
				intArrayOf(53, 6,6, 6, 6, 5, 6, 6),
				intArrayOf(54, 1,1, 1, 1, 1, 1, 1)
				),
		// Pr�fung TL 2005, �bungsaufgabe 6, Regel 1 bis 9
		arrayOf<IntArray>(
				intArrayOf(41, 3,3, 1, 5, 2, 4, 3),
				intArrayOf(43, 6,6, 5, 3, 5, 6, 6),
				intArrayOf(49, 5,5, 6, 4, 4, 5, 4),
				intArrayOf(50, 2,2, 3, 1, 1, 2, 5),
				intArrayOf(51, 1,1, 4, 2, 3, 1, 1),
				intArrayOf(53, 4,4, 2, 6, 6, 3, 2)
				),/*
		arrayOf<IntArray>(
				intArrayOf(41, 3,3, 1, 5, 2, 4, 3),
				intArrayOf(43, 6,6, 5, 3, 5, 6, 6),
				intArrayOf(49, 5,5, 6, 4, 4, 5, 4),
				intArrayOf(50, 2,2, 3, 1, 1, 2, 5),
				intArrayOf(51, 1,1, 4, 2, 3, 1, 1),
				intArrayOf(53, 4,4, 2, 6, 6, 3, 2)
				),
		arrayOf<IntArray>(
				intArrayOf(41, 3, 1, 5, 2, 4, 3),
				intArrayOf(43, 6, 5, 3, 5, 6, 6),
				intArrayOf(49, 5, 6, 4, 4, 5, 4),
				intArrayOf(50, 2, 3, 1, 1, 2, 5),
				intArrayOf(51, 1, 4, 2, 3, 1, 1),
				intArrayOf(53, 4, 2, 6, 6, 3, 2)
				),
		arrayOf<IntArray>(
				intArrayOf(41, 3, 1, 5, 2, 4, 3),
				intArrayOf(43, 6, 5, 3, 5, 6, 6),
				intArrayOf(49, 5, 6, 4, 4, 5, 4),
				intArrayOf(50, 2, 3, 1, 1, 2, 5),
				intArrayOf(51, 1, 4, 2, 3, 1, 1),
				intArrayOf(53, 4, 2, 6, 6, 3, 2)
				),
		arrayOf<IntArray>(
				intArrayOf(41, 3, 1, 5, 2, 4, 3),
				intArrayOf(43, 6, 5, 3, 5, 6, 6),
				intArrayOf(49, 5, 6, 4, 4, 5, 4),
				intArrayOf(50, 2, 3, 1, 1, 2, 5),
				intArrayOf(51, 1, 4, 2, 3, 1, 1),
				intArrayOf(53, 4, 2, 6, 6, 3, 2)
				),*/
		// Prüfung TL 2005, Übungsaufgabe 7, Regel 1 bis 9
		// TODO
		// Prüfung TL 2005, Übungsaufgabe 8, Regel 1 bis 9
		// TODO
		// Prüfung TL 2005, Übungsaufgabe 9, Regel 1 bis 9
		// TODO
		// Perfect chaos
 		arrayOf<IntArray>(
    			intArrayOf(101, 1,5, 1, 2, 3, 4, 5),
    			intArrayOf(102, 1,5, 5, 1, 2, 3, 4),
    			intArrayOf(103, 1,5, 4, 5, 1, 2, 3),
    			intArrayOf(104, 1,5, 3, 4, 5, 1, 2),
    			intArrayOf(105, 1,5, 2, 3, 4, 5, 1)
				),
		// Perfect ordering
		arrayOf<IntArray>(
				intArrayOf(101, 1,1, 1, 1, 1, 1, 1),
				intArrayOf(102, 2,2, 2, 2, 2, 2, 2),
				intArrayOf(103, 3,3, 3, 3, 3, 3, 3),
				intArrayOf(104, 4,4, 4, 4, 4, 4, 4),
				intArrayOf(105, 5,5, 5, 5, 5, 5, 5)
				)
	)
}