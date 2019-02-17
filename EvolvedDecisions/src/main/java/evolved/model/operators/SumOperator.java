package evolved.model.operators;

import evolved.data.DataValue;
import evolved.data.DataValueFactory;

import java.util.Set;

public class SumOperator implements MultiOperator {

    @Override
    public DataValue transform(Set<DataValue> dataValues) {
        double result = dataValues.stream().mapToDouble(DataValue::getDoubleValue).sum();
        return DataValueFactory.generateDoubleDataValue(result);
    }

}
