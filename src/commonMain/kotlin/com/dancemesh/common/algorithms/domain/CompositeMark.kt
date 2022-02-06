package com.dancemesh.common.algorithms.domain

open class CompositeMark<T>(val map: Map<T, Double>): Comparable<CompositeMark<T>> {

    val total: Double by lazy {
        map.values.sum()
    }

    fun getScore (component: T): Double =
        map[component] ?: 0.0

    override fun compareTo(other: CompositeMark<T>): Int =
        this.total.compareTo(other.total)
}