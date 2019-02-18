package evolved.model;

import evolved.data.DataSet;
import evolved.model.feed.DataValueFeed;

public class ModelBuilderDecorator implements ModelBuilder {

    private final ModelBuilder modelBuilder;

    public ModelBuilderDecorator(ModelBuilder modelBuilder) {
        this.modelBuilder = modelBuilder;
    }

    @Override
    public DataValueFeed generateInputFeed(DataSet dataSet) {
        return modelBuilder.generateInputFeed(dataSet);
    }

    @Override
    public DataValueFeed compute(DataValueFeed inputFeed) {
        return modelBuilder.compute(inputFeed);
    }

    @Override
    public void updateDataSet(DataSet dataSet, DataValueFeed outputFeed) {
        modelBuilder.updateDataSet(dataSet, outputFeed);
    }

}
