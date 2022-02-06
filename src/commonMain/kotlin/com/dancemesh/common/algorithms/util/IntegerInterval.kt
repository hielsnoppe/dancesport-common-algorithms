package com.dancemesh.common.algorithms.util

/**
 * Ranks are represented by integer intervals.
 */
data class IntegerInterval (val min: Int, val max: Int): Comparable<IntegerInterval> {

	constructor (x: Int): this(x, x)
	
	fun equals(other: IntegerInterval): Boolean = this.min == other.min && this.max == other.max
	fun isNext(other: IntegerInterval): Boolean = this.max == other.min - 1
	fun isOverlapping(other: IntegerInterval): Boolean = !isDisjunct(other)
	fun isDisjunct(other: IntegerInterval): Boolean =
			this.min > other.max || this.max < other.min
	fun size(): Int = max - min
	fun median(): Int = (min + max) / 2
	fun average(): IntFraction = IntFraction((min + max), 2)
	
	override fun compareTo(other: IntegerInterval): Int {
		
		// Lower minimum value makes better rank
		if (this.min < other.min) return 1;
		if (other.min < this.min) return -1;
		
		// Same minimum value: Lower maximum value makes better rank
		if (this.max < other.max) return 1;
		if (other.max < this.max) return -1;
		
		// Same minimum and maximum value: Ranks are equal
		return 0;
	}
	
	override fun toString (): String {
		return if (min == max) "$min"
		else "$min-$max"
	}
}