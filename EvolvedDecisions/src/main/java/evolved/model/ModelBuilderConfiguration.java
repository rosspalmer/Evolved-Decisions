package evolved.model;

import evolved.model.operators.Operator;

import java.util.Set;

public class ModelBuilderConfiguration {

    private final Set<String> inputKeys;
    private final String outputKey;

    private final Operator operator;

    public ModelBuilderConfiguration(Set<String> inputKeys, String outputKey, Operator operator) {
        this.inputKeys = inputKeys;
        this.outputKey = outputKey;
        this.operator = operator;
    }

    Set<String> getInputKeys() {
        return inputKeys;
    }

    Operator getOperator() {
        return operator;
    }

    String getOutputKey() {
        return outputKey;
    }
}
