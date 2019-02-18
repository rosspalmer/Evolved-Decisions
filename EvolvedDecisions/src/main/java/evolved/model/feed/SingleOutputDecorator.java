package evolved.model.feed;

import evolved.data.DataSet;
import evolved.model.ModelBuilder;
import evolved.model.ModelBuilderDecorator;

public class SingleOutputDecorator extends ModelBuilderDecorator {

    private String outputKey;

    public SingleOutputDecorator(ModelBuilder modelBuilder, String outputKey) {
        super(modelBuilder);
        this.outputKey = outputKey;
    }

    @Override
    public void updateDataSet(DataSet dataSet, DataValueFeed outputFeed) {
        dataSet.setValue(outputKey, ((ValueFeed) outputFeed).getDataValue());
    }
}
