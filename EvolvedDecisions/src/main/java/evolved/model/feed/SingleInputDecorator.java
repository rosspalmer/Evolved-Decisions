package evolved.model.feed;

import evolved.data.DataSet;
import evolved.data.DataValue;
import evolved.model.ModelBuilder;
import evolved.model.ModelBuilderDecorator;

public class SingleInputDecorator extends ModelBuilderDecorator {

    private String inputKey;

    public SingleInputDecorator(ModelBuilder modelBuilder, String inputKey) {
        super(modelBuilder);
        this.inputKey = inputKey;
    }

    @Override
    public DataValueFeed generateInputFeed(DataSet dataSet) {
        DataValue dataValue = dataSet.getValue(inputKey);
        return new ValueFeed(dataValue);
    }

}
