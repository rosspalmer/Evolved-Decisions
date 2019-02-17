package evolved.model.operators;

import evolved.data.DataValue;
import evolved.data.DataValueFactory;

import java.util.Set;

public class MaxOperator implements MultiOperator {

    @Override
    public DataValue transform(Set<DataValue> dataValues) {
        double result = dataValues.stream().mapToDouble(DataValue::getDoubleValue).max().orElse(Double.MIN_VALUE);
        return DataValueFactory.generateDoubleDataValue(result);
    }

}
