package evolved.model;

import evolved.model.compute.Operator;

import java.util.Set;

public class ComponentBuilderConfiguration {

    private final Set<String> inputKeys;
    private final String outputKey;

    private final Operator operator;

    public ComponentBuilderConfiguration(Set<String> inputKeys, String outputKey, Operator operator) {
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
