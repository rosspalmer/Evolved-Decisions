package evolved.model.operators;

import evolved.data.DataValue;
import evolved.data.DataValueFactory;

import java.util.Set;

public class ProductOperator implements MultiOperator {

    @Override
    public DataValue transform(Set<DataValue> dataValues) {
        double result = dataValues.stream().mapToDouble(DataValue::getDoubleValue)
                .reduce(1, (a, b) -> a * b);
        return DataValueFactory.generateDoubleDataValue(result);
    }

}
