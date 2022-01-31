package com.dancemesh.common.algorithms.ranking

import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.api.extension.ExtensionContext
import java.util.stream.Stream
import org.junit.jupiter.params.provider.Arguments

object FinalScoringProvider: ArgumentsProvider {
 
    override fun provideArguments (context: ExtensionContext): Stream<Arguments> =
    		rawData.map({ argumentsOf(it) }).stream()
	
	fun argumentsOf(data: Array<IntArray>): Arguments {
			
    	val ranking = FinalDanceScoringProvider.getRanking(data)
		var scorings = generateDanceScorings(data).map({ FinalDanceScoringProvider.getScoring(it) })
		
		return Arguments.of(scorings, ranking)
	}
	
	fun generateDanceScorings (data: Array<IntArray>) =
		Array(data[0].count() - 3, fun (d): Array<IntArray> {
			return Array(data.count(), fun (c): IntArray {
				return IntArray(7, fun (i): Int {
					if (i == 0) return data[c][0]
					return data[c][d + 3]
				})
			})
		})
	
	// Mozart
	val rawData1 = arrayOf(
			arrayOf<IntArray>(
				intArrayOf(1, 2,2, 13),
				intArrayOf(2, 1,1, 7),
				intArrayOf(3, 4,4, 20),
				intArrayOf(4, 3,3, 20),
				intArrayOf(5, 5,5, 20),
				intArrayOf(6, 6,6, 25)
			),
			arrayOf<IntArray>( // LW
				intArrayOf(1, 3,3, 1, 3, 2, 6, 6),
				intArrayOf(2, 1,1, 2, 1, 3, 4, 1),
				intArrayOf(3, 2,2, 3, 2, 1, 2, 3),
				intArrayOf(4, 6,6, 4, 6, 6, 3, 5),
				intArrayOf(5, 4,4, 5, 5, 4, 1, 4),
				intArrayOf(6, 5,5, 6, 4, 5, 5, 2)
			),
			arrayOf<IntArray>( // TG
				intArrayOf(1, 1,1, 1, 1, 1, 1, 1),
				intArrayOf(2, 2,2, 2, 2, 2, 4, 4),
				intArrayOf(3, 4,4, 3, 3, 4, 3, 5),
				intArrayOf(4, 3,3, 4, 4, 3, 2, 2),
				intArrayOf(5, 5,5, 5, 5, 5, 5, 3),
				intArrayOf(6, 6,6, 6, 6, 6, 6, 6)
			),
			arrayOf<IntArray>( // VW
				intArrayOf(1, 5,5, 1, 1, 4, 5, 6),
				intArrayOf(2, 1,1, 5, 5, 2, 1, 1),
				intArrayOf(3, 3,3, 3, 3, 1, 4, 4),
				intArrayOf(4, 4,4, 4, 4, 5, 2, 2),
				intArrayOf(5, 2,2, 2, 2, 3, 3, 3),
				intArrayOf(6, 6,6, 6, 6, 6, 6, 6)
			),
			arrayOf<IntArray>( // SF
				intArrayOf(1, 3,3, 1, 3, 1, 4, 6),
				intArrayOf(2, 1,1, 2, 5, 5, 1, 1),
				intArrayOf(3, 5,5, 6, 6, 4, 3, 4),
				intArrayOf(4, 2,2, 3, 1, 3, 6, 3),
				intArrayOf(5, 6,6, 5, 2, 2, 5, 5),
				intArrayOf(6, 4,4, 4, 4, 6, 2, 2)
			),
			arrayOf<IntArray>( // QS
				intArrayOf(1, 1,1, 1, 1, 1, 1, 1),
				intArrayOf(2, 2,2, 2, 2, 2, 2, 2),
				intArrayOf(3, 6,6, 6, 6, 6, 6, 6),
				intArrayOf(4, 5,5, 5, 5, 5, 3, 3),
				intArrayOf(5, 3,3, 3, 3, 3, 4, 4),
				intArrayOf(6, 4,4, 4, 4, 4, 5, 5)
			)
		)
	
	val rawData2 = arrayOf(
			arrayOf<IntArray>(
				intArrayOf(21, 6,6, 14),
				intArrayOf(22, 5,5, 14),
				intArrayOf(23, 3,3, 14),
				intArrayOf(24, 4,4, 14),
				intArrayOf(25, 2,2, 14),
				intArrayOf(26, 1,1, 14)
			),
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
			)
		)
	
	// Majorität und Skating
	val rawData3 = arrayOf(
			arrayOf<IntArray>(
				intArrayOf(11, 1,1, 7),
				intArrayOf(21, 2,2, 20),
				intArrayOf(31, 5,6, 20),
				intArrayOf(41, 4,4, 20),
				intArrayOf(51, 5,6, 20),
				intArrayOf(61, 3,3, 20),
				intArrayOf(71, 7,7, 335)
			),
			arrayOf<IntArray>(
				intArrayOf(11, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(21, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(31, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(41, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(51, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(61, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(71, 1, 0, 0, 0, 0, 0, 0, 0)
			),
			arrayOf<IntArray>(
				intArrayOf(11, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(21, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(31, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(41, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(51, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(61, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(71, 1, 0, 0, 0, 0, 0, 0, 0)
			),
			arrayOf<IntArray>(
				intArrayOf(11, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(21, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(31, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(41, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(51, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(61, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(71, 1, 0, 0, 0, 0, 0, 0, 0)
			),
			arrayOf<IntArray>(
				intArrayOf(11, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(21, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(31, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(41, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(51, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(61, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(71, 1, 0, 0, 0, 0, 0, 0, 0)
			),
			arrayOf<IntArray>(
				intArrayOf(11, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(21, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(31, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(41, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(51, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(61, 1, 0, 0, 0, 0, 0, 0, 0),
				intArrayOf(71, 1, 0, 0, 0, 0, 0, 0, 0)
			)
		)
	
	// jUdge BEISPIEL_P
	val rawData4 = arrayOf(
			arrayOf<IntArray>(
				intArrayOf(101, 1,1, 0),
				intArrayOf(102, 2,2, 0),
				intArrayOf(103, 3,3, 0),
				intArrayOf(104, 4,4, 0),
				intArrayOf(105, 5,5, 0),
				intArrayOf(106, 6,6, 0)
			),
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
				intArrayOf(102, 3,5, 2, 3, 4, 5, 6),
				intArrayOf(103, 3,5, 3, 4, 5, 6, 2),
				intArrayOf(104, 3,5, 4, 5, 6, 2, 3),
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
			)
	)
	
	val rawData = arrayOf(
		// Beispiel F - Regel 9a, TSO, S. X
		arrayOf<IntArray>(
				intArrayOf(16, 1,1, 1, 2, 1, 2, 2),
				intArrayOf(26, 2,2, 3, 1, 2, 1, 4),
				intArrayOf(36, 3,3, 2, 3, 3, 3, 3),
				intArrayOf(46, 4,4, 4, 4, 4, 4, 1),
				intArrayOf(56, 5,5, 5, 5, 5, 5, 6),
				intArrayOf(66, 6,6, 6, 6, 6, 6, 5)
				),
		// Beispiel G - Regel 10a und 10d, TSO, S. X
		arrayOf<IntArray>(
				intArrayOf(17, 1,1, 1, 1, 2, 5),
				intArrayOf(27, 2,2, 3, 3, 1, 2),
				intArrayOf(37, 4,4, 2, 5, 4, 4),
				intArrayOf(47, 3,3, 4, 2, 3, 6),
				intArrayOf(57, 6,6, 6, 6, 5, 1),
				intArrayOf(67, 5,5, 5, 4, 6, 3)
				),
		// Beispiel H - Regel 10b (erster Satz) und 10d, TSO, S. X
		arrayOf<IntArray>(
				intArrayOf(18, 1,1, 3, 1, 1, 1, 1),
				intArrayOf(28, 2,2, 2, 2, 3, 2, 6),
				intArrayOf(38, 3,3, 1, 4, 2, 5, 3),
				intArrayOf(48, 4,4, 4, 5, 6, 4, 2),
				intArrayOf(58, 5,5, 5, 3, 5, 3, 5),
				intArrayOf(68, 6,6, 6, 6, 4, 6, 4)
				),
		// Beispiel J - Regel 10b (zweiter Satz) und 10d, TSO, S. X
		arrayOf<IntArray>(
				intArrayOf(19, 1,1, 1, 1, 2, 3, 2),
				intArrayOf(29, 2,2, 3, 4, 1, 2, 1),
				intArrayOf(39, 3,3, 2, 2, 3, 1, 3),
				intArrayOf(49, 4,4, 6, 3, 4, 5, 5),
				intArrayOf(59, 5,5, 5, 5, 5, 4, 4),
				intArrayOf(69, 6,6, 4, 6, 6, 6, 6)
				),
		// Beispiel K - Regel 10c, TSO, S. X
		arrayOf<IntArray>(
				intArrayOf(20, 1,1, 2, 1, 5, 3, 2),
				intArrayOf(30, 2,2, 1, 2, 2, 6, 6),
				intArrayOf(40, 3,3, 6, 6, 1, 1, 3),
				intArrayOf(50, 4,4, 4, 4, 4, 4, 1),
				intArrayOf(60, 5,5, 5, 3, 3, 2, 4),
				intArrayOf(70, 6,6, 3, 5, 6, 5, 5)
				)/*,
		// Beispiel L1 - Regel 9a, TSO, S. X
		arrayOf<IntArray>(
				intArrayOf(16, 1, 1, 2, 1, 2, 2),
				intArrayOf(26, 2, 3, 1, 2, 1, 4),
				intArrayOf(36, 3, 2, 3, 3, 3, 3),
				intArrayOf(46, 4, 4, 4, 4, 4, 1),
				intArrayOf(56, 5, 5, 5, 5, 5, 6),
				intArrayOf(66, 6, 6, 6, 6, 6, 5)
				),
		// Beispiel L2 - Regel 9a, TSO, S. X
		arrayOf<IntArray>(
				intArrayOf(16, 1, 1, 2, 1, 2, 2),
				intArrayOf(26, 2, 3, 1, 2, 1, 4),
				intArrayOf(36, 3, 2, 3, 3, 3, 3),
				intArrayOf(46, 4, 4, 4, 4, 4, 1),
				intArrayOf(56, 5, 5, 5, 5, 5, 6),
				intArrayOf(66, 6, 6, 6, 6, 6, 5)
				),
		// Beispiel L3 - Regel 9a, TSO, S. X
		arrayOf<IntArray>(
				intArrayOf(16, 1, 1, 2, 1, 2, 2),
				intArrayOf(26, 2, 3, 1, 2, 1, 4),
				intArrayOf(36, 3, 2, 3, 3, 3, 3),
				intArrayOf(46, 4, 4, 4, 4, 4, 1),
				intArrayOf(56, 5, 5, 5, 5, 5, 6),
				intArrayOf(66, 6, 6, 6, 6, 6, 5)
				),
		// Beispiel L4 - Regel 9a, TSO, S. X
		arrayOf<IntArray>(
				intArrayOf(16, 1, 1, 2, 1, 2, 2),
				intArrayOf(26, 2, 3, 1, 2, 1, 4),
				intArrayOf(36, 3, 2, 3, 3, 3, 3),
				intArrayOf(46, 4, 4, 4, 4, 4, 1),
				intArrayOf(56, 5, 5, 5, 5, 5, 6),
				intArrayOf(66, 6, 6, 6, 6, 6, 5)
				),
		// Beispiel M - Regel 9a, TSO, S. X
		arrayOf<IntArray>(
				intArrayOf(16, 1, 1, 2, 1, 2, 2),
				intArrayOf(26, 2, 3, 1, 2, 1, 4),
				intArrayOf(36, 3, 2, 3, 3, 3, 3),
				intArrayOf(46, 4, 4, 4, 4, 4, 1),
				intArrayOf(56, 5, 5, 5, 5, 5, 6),
				intArrayOf(66, 6, 6, 6, 6, 6, 5)
				),
		// Beispiel N - Regel 9a, TSO, S. X
		arrayOf<IntArray>(
				intArrayOf(16, 1, 1, 2, 1, 2, 2),
				intArrayOf(26, 2, 3, 1, 2, 1, 4),
				intArrayOf(36, 3, 2, 3, 3, 3, 3),
				intArrayOf(46, 4, 4, 4, 4, 4, 1),
				intArrayOf(56, 5, 5, 5, 5, 5, 6),
				intArrayOf(66, 6, 6, 6, 6, 6, 5)
				),
		// Beispiel P - Regel 9a, TSO, S. X
		arrayOf<IntArray>(
				intArrayOf(16, 1, 1, 2, 1, 2, 2),
				intArrayOf(26, 2, 3, 1, 2, 1, 4),
				intArrayOf(36, 3, 2, 3, 3, 3, 3),
				intArrayOf(46, 4, 4, 4, 4, 4, 1),
				intArrayOf(56, 5, 5, 5, 5, 5, 6),
				intArrayOf(66, 6, 6, 6, 6, 6, 5)
				)
		*/
		)
}