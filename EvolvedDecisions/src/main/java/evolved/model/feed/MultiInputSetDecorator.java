package evolved.model.feed;

import evolved.data.DataSet;
import evolved.data.DataValue;
import evolved.model.ModelBuilder;
import evolved.model.ModelBuilderDecorator;

import java.util.Set;

public class MultiInputSetDecorator extends ModelBuilderDecorator {

    private Set<String> inputKeys;

    public MultiInputSetDecorator(ModelBuilder modelBuilder, Set<String> inputKeys) {
        super(modelBuilder);
        this.inputKeys = inputKeys;
    }

    @Override
    public DataValueFeed generateInputFeed(DataSet dataSet) {
        Set<DataValue> dataValues = dataSet.getValues(inputKeys);
        return new ValueSetFeed(dataValues);
    }

}
