package com.dancemesh.common.algorithms.domain

abstract class CompositeScore<T> {//: Comparable<CompositeScore<T>> {

    val map: MutableMap<T, MutableList<Double>> = mutableMapOf()

    constructor(marks: Collection<CompositeMark<T>>) {

        marks.forEach {
            it.map.entries.forEach {
                this.addScore(it.key, it.value)
            }
        }
    }

    fun addScore (component: T, score: Double) {

        if (!map.containsKey(component))
            map[component] = mutableListOf()

        map[component]!!.add(score)
    }

    fun getScore (component: T): Double =
        aggregate(getScores(component))

    fun getScores (component: T): List<Double> =
        map[component] ?: listOf()

    fun getComponents(): Set<T> = map.keys

    abstract fun aggregate (marks: List<Double>): Double
}