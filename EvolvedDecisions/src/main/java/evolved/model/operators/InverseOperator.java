package evolved.model.operators;

import evolved.data.DataValue;
import evolved.data.DataValueFactory;

public class InverseOperator implements SingleOperator {

    @Override
    public DataValue transformDataValue(DataValue dataValue) {
        return DataValueFactory.generateDoubleDataValue(1 / dataValue.getDoubleValue());
    }

}
