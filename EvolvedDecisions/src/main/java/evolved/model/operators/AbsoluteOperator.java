package evolved.model.operators;

import evolved.data.DataValue;
import evolved.data.DataValueFactory;

public class AbsoluteOperator implements SingleOperator {

    @Override
    public DataValue transformDataValue(DataValue dataValue) {
        return DataValueFactory.generateDoubleDataValue(Math.abs(dataValue.getDoubleValue()));
    }

}
