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

    public void setParameters(Set<Parameter> parameters) {
        this.parameters = parameters;
    }

    public void setParameterTuner(ParameterTuner parameterTuner) {
        this.parameterTuner = parameterTuner;
    }

    public void setModelEngine(ModelEngine modelEngine) {
        this.modelEngine = modelEngine;
    }

    @Override
    public DataSet transformDataSet(ModelEngine modelEngine, DataSet dataSet) {

        setModelEngine(modelEngine);
        setParameters(getParameterTuner().updateParameters(dataSet, getParameters()));

        DataValueFeed inputFeed = generateInputFeed(dataSet);
        DataValueFeed outputFeed = compute(inputFeed);
        dataSet = updateDataSet(dataSet, outputFeed);

        return dataSet;
    }

    public abstract DataValueFeed generateInputFeed(DataSet dataSet);

    public abstract DataValueFeed compute(DataValueFeed inputFeed);

    public abstract DataSet updateDataSet(DataSet dataSet, DataValueFeed outputFeed);

}
