package ross.palmer.interstellar.ai.decisions;

import java.util.Map;

public interface DecisionModel {

    public Map<Long, Double> getOutputData(Map<Long, Double> inputValues);

}
