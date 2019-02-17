package evolved.model;

import java.util.HashSet;
import java.util.Set;

public abstract class Model {

    private ModelEngine modelEngine;
    private Set<Parameter> parameters;

    public Model(ModelEngine modelEngine) {
        this.modelEngine = modelEngine;
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

    public void setParameters(Set<Parameter> parameters) {
        this.parameters = parameters;
    }

    public void setModelEngine(ModelEngine modelEngine) {
        this.modelEngine = modelEngine;
    }

    public DataSet transformDataSet(DataSet dataSet, ModelEngine modelEngine) {
        setModelEngine(modelEngine);
        return transformDataSet(dataSet);
    }

    public abstract DataSet transformDataSet(DataSet dataSet);

}
