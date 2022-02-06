# Common algorithms for DanceSport

## Ranking

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