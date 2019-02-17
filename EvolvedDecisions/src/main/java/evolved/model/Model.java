package evolved.model;

import evolved.data.DataSet;
import evolved.model.feed.DataValueFeed;

import java.util.HashSet;
import java.util.Set;

public abstract class Model {

    private ModelEngine modelEngine;
    private ParameterTuner parameterTuner;
    private ModelBuilder modelBuilder;
    private Set<Parameter> parameters;

    public Model(ModelEngine modelEngine, ParameterTuner parameterTuner, ModelBuilder modelBuilder) {
        this.modelEngine = modelEngine;
        this.parameterTuner = parameterTuner;
        this.modelBuilder = modelBuilder;
        parameters = new HashSet<>();
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }

    public ModelEngine getModelEngine() {
        return modelEngine;
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

    public void setModelEngine(ModelEngine modelEngine) {
        this.modelEngine = modelEngine;
    }

    public DataSet transformDataSet(ModelEngine modelEngine, DataSet dataSet) {

        setModelEngine(modelEngine);
        setParameters(getParameterTuner().updateParameters(dataSet, getParameters()));

        DataValueFeed inputFeed = modelBuilder.generateInputFeed(dataSet);
        DataValueFeed outputFeed = modelBuilder.compute(inputFeed);
        dataSet = modelBuilder.updateDataSet(dataSet, outputFeed);

        return dataSet;
    }
}
