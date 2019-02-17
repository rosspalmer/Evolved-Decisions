package evolved.model.base;

import evolved.data.DataSet;
import evolved.model.Model;
import evolved.model.ModelEngine;
import evolved.model.Parameter;
import evolved.model.ParameterTuner;

import java.util.HashSet;
import java.util.Set;

public abstract class ModelBase implements Model {

    private ModelEngine modelEngine;
    private Set<Parameter> parameters;
    private ParameterTuner parameterTuner;

    private DataValueFeed inputFeed;
    private DataValueFeed outputFeed;

    public ModelBase(ModelEngine modelEngine, ParameterTuner parameterTuner) {
        this.modelEngine = modelEngine;
        parameters = new HashSet<>();
        this.parameterTuner = parameterTuner;
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

    public void setInputFeed(DataValueFeed inputFeed) {
        this.inputFeed = inputFeed;
    }

    public void setOutputFeed(DataValueFeed outputFeed) {
        this.outputFeed = outputFeed;
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

    public DataSet transformDataSet(DataSet dataSet, ModelEngine modelEngine) {

        setModelEngine(modelEngine);
        setInputFeed(updateInputFeed(dataSet));
        setOutputFeed(updateOutputFeed(dataSet));

        setParameters(getParameterTuner().updateParameters(dataSet, getParameters()));
        dataSet = transformDataSet(dataSet);

        return dataSet;
    }

    public abstract DataValueFeed updateInputFeed(DataSet dataSet);

    public abstract DataValueFeed updateOutputFeed(DataSet dataSet);

}
