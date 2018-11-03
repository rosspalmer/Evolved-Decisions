package ross.palmer.interstellar.ai.decisions;

import java.util.HashMap;
import java.util.Map;

public abstract class DecisionEngine {

    private final long engineId;
    private DecisionModel decisionModel;
    private Map<Long, Double> inputValues;
    private Map<Long, Boolean> outputTriggers;

    public DecisionEngine(long engineId) {
        this.engineId = engineId;
        inputValues = new HashMap<>();
    }

    public void updateOutputTriggers() {
        Map<Long, Double> outputValues = decisionModel.getOutputData(inputValues);
        outputTriggers = triggerFunction(outputValues);
    }

    public DecisionModel getDecisionModel() {
        return decisionModel;
    }

    public void setDecisionModel(DecisionModel decisionModel) {
        this.decisionModel = decisionModel;
    }

    public Map<Long, Double> getInputValues() {
        return inputValues;
    }

    public void setInputValues(Map<Long, Double> inputValues) {
        this.inputValues = inputValues;
    }

    public double getInputValue(long inputId) {
        return inputValues.get(inputId);
    }

    public void setInputValue(long inputId, double inputValue) {
        inputValues.put(inputId, inputValue);
    }

    public Map<Long, Boolean> getOutputTriggers() {
        return outputTriggers;
    }

    public boolean getOutputTriggerValue(long outputId) {
        return outputTriggers.get(outputId);
    }

    protected abstract Map<Long, Boolean> triggerFunction(Map<Long, Double> outputValues);

}
