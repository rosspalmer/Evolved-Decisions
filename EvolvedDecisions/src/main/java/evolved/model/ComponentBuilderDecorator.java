package evolved.model;

import evolved.data.DataSet;
import evolved.model.feed.DataValueFeed;

public class ComponentBuilderDecorator implements ComponentBuilder {

    private final ComponentBuilder componentBuilder;

    public ComponentBuilderDecorator(ComponentBuilder componentBuilder) {
        this.componentBuilder = componentBuilder;
    }

    @Override
    public DataValueFeed generateInputFeed(DataSet dataSet) {
        return componentBuilder.generateInputFeed(dataSet);
    }

    @Override
    public DataValueFeed compute(DataValueFeed inputFeed) {
        return componentBuilder.compute(inputFeed);
    }

    @Override
    public void updateDataSet(DataSet dataSet, DataValueFeed outputFeed) {
        componentBuilder.updateDataSet(dataSet, outputFeed);
    }

}
