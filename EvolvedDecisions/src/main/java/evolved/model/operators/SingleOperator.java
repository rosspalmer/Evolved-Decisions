package evolved.model.operators;

import evolved.data.DataValue;

public interface SingleOperator extends Operator {

    DataValue transformDataValue(DataValue dataValue);

}
