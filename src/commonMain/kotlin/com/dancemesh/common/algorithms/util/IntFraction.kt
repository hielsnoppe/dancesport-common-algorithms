package com.dancemesh.common.algorithms.util

import kotlin.math.ceil
import kotlin.math.floor

data class IntFraction (val numerator: Int, val denominator: Int): Comparable<IntFraction> {
	
	constructor (x: Int): this(x, 1)
	
	val decimal by lazy { numerator.toDouble() / denominator }
	val floor by lazy { floor(decimal).toInt() }
	val ceil by lazy { ceil(decimal).toInt() }
	
	operator fun plus (add: IntFraction): IntFraction {
		
		if (this.denominator == add.denominator) {
            return IntFraction(this.numerator + add.numerator, denominator).shorten()
        }
		else {
            val a = this * add.denominator
            val b = add * this.denominator
            return IntFraction(a.numerator + b.numerator, a.denominator).shorten()
        }
	}

	operator fun times(num: Int) = IntFraction(
		numerator * num,
		denominator * num
	).shorten()

	operator fun times(other: IntFraction) = IntFraction(
		numerator * other.numerator,
		denominator * other.denominator
	).shorten()

	operator fun div(num: Int) = IntFraction(
		numerator / num,
		denominator / num
	).shorten()

	operator fun div(other: IntFraction) = IntFraction(
		numerator * other.denominator,
		denominator * other.numerator
	).shorten()
	
	override fun compareTo(other: IntFraction) = decimal.compareTo(other.decimal)
	
	fun shorten (): IntFraction {
		if (numerator % denominator == 0) return IntFraction(numerator / denominator, 1)
		return this
	}
	
	override fun toString (): String {
		if (denominator == 1) return "$numerator"
		else return "$numerator/$denominator"
	}
}