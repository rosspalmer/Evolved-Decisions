package evolved.model.feed;

import evolved.data.DataSet;
import evolved.data.DataValue;
import evolved.model.ComponentBuilder;
import evolved.model.ComponentBuilderDecorator;

public class SingleInputDecorator extends ComponentBuilderDecorator {

    private String inputKey;

    public SingleInputDecorator(ComponentBuilder componentBuilder, String inputKey) {
        super(componentBuilder);
        this.inputKey = inputKey;
    }

    @Override
    public DataValueFeed generateInputFeed(DataSet dataSet) {
        DataValue dataValue = dataSet.getValue(inputKey);
        return new ValueFeed(dataValue);
    }

}
