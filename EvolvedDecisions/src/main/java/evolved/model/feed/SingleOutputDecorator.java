package evolved.model.feed;

import evolved.data.DataSet;
import evolved.model.ComponentBuilder;
import evolved.model.ComponentBuilderDecorator;

public class SingleOutputDecorator extends ComponentBuilderDecorator {

    private String outputKey;

    public SingleOutputDecorator(ComponentBuilder componentBuilder, String outputKey) {
        super(componentBuilder);
        this.outputKey = outputKey;
    }

    @Override
    public void updateDataSet(DataSet dataSet, DataValueFeed outputFeed) {
        dataSet.setValue(outputKey, ((ValueFeed) outputFeed).getDataValue());
    }
}
