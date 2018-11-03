package ross.palmer.interstellar.ai.decisions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SingleChoiceDecisionEngine extends DecisionEngine {

    private BestChoice bestChoice;
    private Set<Long> excludedChoices;

    public SingleChoiceDecisionEngine(long engineId) {
        super(engineId);
        excludedChoices = new HashSet<>();
    }

    public void addExcludedChoice(long outputId) {
        excludedChoices.add(outputId);
    }

    public long getTriggeredOutputId() {
        return bestChoice.getOutputId();
    }

    public void removeExcludedChoice(long outputId) {
        excludedChoices.remove(outputId);
    }

    @Override
    protected Map<Long, Boolean> triggerFunction(Map<Long, Double> outputValues) {

        Map<Long, Boolean> outputTriggers = new HashMap<>();
        bestChoice = new BestChoice();
        outputValues.entrySet().stream()
                .filter(entry -> !excludedChoices.contains(entry.getKey()))
                .forEach(entry -> {
                    outputTriggers.put(entry.getKey(), false);
                    bestChoice.check(entry.getKey(), entry.getValue());
        });

        outputTriggers.put(bestChoice.getOutputId(), true);

        return outputTriggers;
    }

    private class BestChoice {

        private long outputId;
        private double highestValue;

        private BestChoice() {
            highestValue = Double.MIN_VALUE;
        }

        private void check(long outputId, double outputValue) {
            if (outputValue > highestValue) {
                this.outputId = outputId;
                this.highestValue = outputValue;
            }
        }

        public long getOutputId() {
            return outputId;
        }

        public double getHighestValue() {
            return highestValue;
        }
    }

}
