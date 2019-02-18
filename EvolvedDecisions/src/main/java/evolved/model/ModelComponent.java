package evolved.model;

import evolved.data.DataSet;
import evolved.model.feed.DataValueFeed;

import java.util.HashSet;
import java.util.Set;

public class ModelComponent {

    private ComponentBuilder componentBuilder;
    private Set<Parameter> parameters;

    public ModelComponent(ComponentBuilder componentBuilder) {
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

    public void setParameters(Set<Parameter> parameters) {
        this.parameters = parameters;
    }

    void updateDataSet(DataSet dataSet) {
        DataValueFeed inputFeed = componentBuilder.generateInputFeed(dataSet);
        DataValueFeed outputFeed = componentBuilder.compute(inputFeed);
        componentBuilder.updateDataSet(dataSet, outputFeed);
    }
}
