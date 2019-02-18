package evolved.model.feed;

import evolved.data.DataSet;
import evolved.data.DataValue;
import evolved.model.ComponentBuilder;
import evolved.model.ComponentBuilderDecorator;

import java.util.Set;

public class MultiInputSetDecorator extends ComponentBuilderDecorator {

    private Set<String> inputKeys;

    public MultiInputSetDecorator(ComponentBuilder componentBuilder, Set<String> inputKeys) {
        super(componentBuilder);
        this.inputKeys = inputKeys;
    }

    @Override
    public DataValueFeed generateInputFeed(DataSet dataSet) {
        Set<DataValue> dataValues = dataSet.getValues(inputKeys);
        return new ValueSetFeed(dataValues);
    }

}
