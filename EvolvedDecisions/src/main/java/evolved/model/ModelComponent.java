package evolved.model;

import evolved.data.DataSet;
import evolved.model.feed.DataValueFeed;

import java.util.HashSet;
import java.util.Set;

public abstract class ModelComponent {

    private ParameterTuner parameterTuner;
    private ComponentBuilder componentBuilder;
    private Set<Parameter> parameters;

    public ModelComponent(ParameterTuner parameterTuner, ComponentBuilder componentBuilder) {
        this.parameterTuner = parameterTuner;
        this.componentBuilder = componentBuilder;
        parameters = new HashSet<>();
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }

    public Parameter getParameter(String parameterName) {
        return parameters.stream().filter(parameter -> parameter.getName().equals(parameterName))
                .findAny().orElse(null);
    }

    public Set<Parameter> getParameters() {
        return parameters;
    }

    public ParameterTuner getParameterTuner() {
        return parameterTuner;
    }

    public void setParameters(Set<Parameter> parameters) {
        this.parameters = parameters;
    }

    public void setParameterTuner(ParameterTuner parameterTuner) {
        this.parameterTuner = parameterTuner;
    }

    void updateDataSet(DataSet dataSet) {
        setParameters(getParameterTuner().updateParameters(dataSet, getParameters()));
        DataValueFeed inputFeed = componentBuilder.generateInputFeed(dataSet);
        DataValueFeed outputFeed = componentBuilder.compute(inputFeed);
        componentBuilder.updateDataSet(dataSet, outputFeed);
    }
}
