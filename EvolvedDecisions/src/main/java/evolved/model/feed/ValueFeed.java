package evolved.model.feed;

import evolved.data.DataValue;

public class ValueFeed implements DataValueFeed {

    private final DataValue dataValue;

    public ValueFeed(DataValue dataValue) {
        this.dataValue = dataValue;
    }

    public DataValue getDataValue() {
        return dataValue;
    }

}
