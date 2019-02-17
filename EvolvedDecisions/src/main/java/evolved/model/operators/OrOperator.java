package evolved.model.operators;

import evolved.data.DataValue;
import evolved.data.DataValueFactory;

import java.util.Set;

public class OrOperator implements MultiOperator {

    @Override
    public DataValue transform(Set<DataValue> dataValues) {
        boolean result = dataValues.stream().anyMatch(DataValue::getBooleanValue);
        return DataValueFactory.generateBooleanDataValue(result);
    }

}
