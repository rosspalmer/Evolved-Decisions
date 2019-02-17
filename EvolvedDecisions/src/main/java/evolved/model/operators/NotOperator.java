package evolved.model.operators;

import evolved.data.DataValue;
import evolved.data.DataValueFactory;

public class NotOperator implements SingleOperator {

    @Override
    public DataValue transformDataValue(DataValue dataValue) {
        return DataValueFactory.generateBooleanDataValue(!dataValue.getBooleanValue());
    }

}
