package de.fellmann.judge.skating;

import com.dancemesh.common.algorithms.domain.MutableRanking;
import com.dancemesh.common.algorithms.domain.Ranking;
import com.dancemesh.common.algorithms.domain.RankingImpl;
import com.dancemesh.common.algorithms.domain.Scoring;
import com.dancemesh.common.algorithms.ranking.MultiRankingAlgorithm;
import com.dancemesh.common.algorithms.util.IntegerInterval;
import de.fellmann.judge.Place;

import java.util.List;

/**
 * Adapter for Hanno Fellmann's implementation of Skating calculation.
 *
 * @author Niels Hoppe
 *
 */
public class FellmannAlgorithm implements MultiRankingAlgorithm<List<IntegerInterval>>
{

	@Override
	public <C> Ranking<C> getRanking(List<? extends Scoring<C, List<IntegerInterval>>> scorings) {
		
		final C[] candidates = (C[]) scorings.get(0).getCandidates().toArray();
		
		final JudgementForFinal jff = preprocess(scorings, candidates);
		final Calculator test = new Calculator(jff);
		final Ranking<C> result = postprocess(test, candidates);
		
		return result;
	}

	private static <C> JudgementForFinal preprocess (
			List<? extends Scoring<C, List<IntegerInterval>>> scorings,
			C[] candidates
			) {
		
		final int dances = scorings.size();
		final int judges = scorings.get(0).getScore(candidates[0]).size();
		
		final JudgementForFinal jff = new JudgementForFinal(dances, candidates.length, judges);

		for (int dance = 0; dance < scorings.size(); dance++) {
			
			Scoring<C, List<IntegerInterval>> danceScoring = scorings.get(dance);
			
			for (int c = 0; c < danceScoring.count(); c++) {
				
				int judge = 0;
				
				for (IntegerInterval score: danceScoring.getScore(candidates[c])) {
					
					jff.setMark(dance, c, judge, (byte) score.median());
					judge++;
				}
			}
		}
		
		return jff;
	}
	
	private static <C> Ranking<C> postprocess (Calculator calc, C[] candidates) {
		
		MutableRanking<C> result = new RankingImpl<C>();

		for (int c = 0; c < candidates.length; c++) {
			result.putRank(candidates[c], placeToIntegerInterval(calc.getResult(c)));
		}
		
		return result;
	}
	
	private static IntegerInterval placeToIntegerInterval (Place place) {
		
		return new IntegerInterval(place.getPlaceFrom(), place.getPlaceTo());
	}
}
