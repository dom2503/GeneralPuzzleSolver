package generalpuzzlesolver;

import generalpuzzlesolver.puzzle.PuzzleState;

/**
 * Uses the Simulated Annealing algorithm to solve constraint based puzzles.
 *
 * The puzzle should be represented by a specific implementation of a LocalStateManager.
 */
public class SimulatedAnnealing extends ConstraintBasedLocalSearch {

  //values as proposed here:http://artint.info/html/ArtInt_89.html
  private final static double START_TEMPERATURE = 10.0;
  private final static double TEMPERATURE_MULTIPLIER = 0.97;

  public SimulatedAnnealing(int maximumSteps) {
    super(maximumSteps);
  }

  @Override
  public PuzzleState run() {
    PuzzleState currentState = this.getStateManager().getRandomState();
    double currentEvaluation = this.getStateManager().evaluate(currentState);

    PuzzleState candidate, bestState = currentState;
    double candidateEvaluation, bestEvaluation = currentEvaluation;

    double temperature = START_TEMPERATURE;

    this.resetStepCount();
    while (this.getStepCount() < this.getMaximumSteps() && bestEvaluation > 0.0) {
      temperature *= TEMPERATURE_MULTIPLIER;

      candidate = this.getStateManager().getRandomNeighbour(currentState);
      candidateEvaluation = this.getStateManager().evaluate(candidate);

      if (this.isAcceptable(candidateEvaluation, currentEvaluation, temperature)) {
        currentState = candidate;
        currentEvaluation = candidateEvaluation;
      }

      if (currentEvaluation < bestEvaluation) {
        bestState = currentState;
        bestEvaluation = currentEvaluation;
      }

      this.incrementStepCount();
    }

    return bestState;
  }

  private boolean isAcceptable(double candidateEvaluation, double currentEvaluation, double temperature) {
    return candidateEvaluation < currentEvaluation || Math.exp(-(candidateEvaluation - currentEvaluation) / temperature)
            > Math.random();
  }
}
