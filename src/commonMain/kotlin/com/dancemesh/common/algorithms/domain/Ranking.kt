package com.dancemesh.common.algorithms.domain

import com.dancemesh.common.algorithms.util.IntegerInterval

/**
 * A ranking relates a set of candidates to ranks represented by integer intervals.
 */
interface Ranking<C>: Scoring<C, IntegerInterval> {
	
	fun getRank (candidate: C): IntegerInterval
//	fun getCandidates (): Set<C>
	override fun equals(other: Any?): Boolean
	
	/**
	 * @todo Specify validation rules
	 *
     * The sum of all ranks must equal the sum of the integer sequence from 1 to
     * number of ranked entities.
     *

    public static function isValid ($array) {

        // Create ranges by counting occurences of each rank.
        // Built in array_count_values does not count floats.
        $counts = Util::array_count_values($array, 'floatval');

        // Sort by keys (ranks).
        ksort($counts);

        $n = 0;

        foreach ($counts as $rank => $count) {

            // Product of size of range and rank must match
            // sum of all ranks covered by this range.

            if ($count * $rank != array_sum(range($n + 1, $n + $count))) {

                return false;
            }

            $n = $n + $count;
        }

        return true;
    }*/
}