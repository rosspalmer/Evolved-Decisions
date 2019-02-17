package evolved.model.feed;

import evolved.data.DataValue;

import java.util.Set;

public class ValueSetFeed implements DataValueFeed {

    private final Set<DataValue> values;

    public ValueSetFeed(Set<DataValue> values) {
        this.values = values;
    }

    public Set<DataValue> getDataValues() {
        return values;
    }
}
