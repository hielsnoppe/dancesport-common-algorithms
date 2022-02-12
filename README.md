# Common algorithms for DanceSport

[![CodeFactor](https://www.codefactor.io/repository/github/hielsnoppe/dancesport-common-algorithms/badge)](https://www.codefactor.io/repository/github/hielsnoppe/dancesport-common-algorithms)

This library supports developers of open-source software for DanceSport
with carefully crafted and extensively reviewed and tested implementations
of the common algorithms that underlie the rules governing DanceSport competitions.

**Note:** This library is neither reviewed nor certified (yet) by any sports governing body such as,
e.g., the [World DanceSport Federation (WDSF)](https://www.worlddancesport.org/)
or the [World Dance Council (WDC)](https://www.wdcdance.com/).

## Scoring and Ranking

Abstract base algorithms

* **SimpleRanking**:
* **CompositeRanking**:

Concrete algorithms:

* **MarksCountRanking**:
* **MajorityRanking**:
* **MultiMajorityRanking**:

### WDSF Absolute Judging System

The following versions of the WDSF Absolute Judging System are implemented:

* Version 1.0 (deprecated): implemented
* Version 2.1 (deprecated): work in progress
* Version 3.0: work in progress

## Drawing

DanceSport competitions are usually executed in rounds,
rounds consist of dances and each dance is danced in groups, aka. heats.
The process of assigning competitors to heats is called drawing and the resulting assignment is called a heat draw.
In this library the heat draw for an individual dance is represented by the `HeatDraw` type
and the sum of heat draws for all dances in a round is represented by the `MultiHeatDraw` type.

There are different methods of drawing heats under various conditions.
This library supports the following drawing methods:

* **Randomized:** Creates a random `HeatDraw` for a single dance.
* **SortedWith:** Creates a sorted `HeatDraw` for a single dance,
  based on a provided criterion, e.g., number.
* **MultiFixed:** Creates a `MultiHeatDraw` in which the assignment is the same in every dance,
  based on a provided drawing algorithm for an individual dance.
* **MultiIndependent:** Creates a `MultiHeatDraw` in which the assignment is calculated independently for every dance, 
  based on a provided drawing algorithm for the individual dances.
* **MultiRandomizedNice:** Creates a `MultiHeatDraw` in which the assignment is randomized in each individual dance,
  but, when possible, competitors are not assigned to the first heat of a dance if they are also assigned to the last heat of the previous dance.