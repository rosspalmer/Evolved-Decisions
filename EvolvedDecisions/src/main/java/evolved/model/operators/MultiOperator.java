package evolved.model.operators;

import evolved.data.DataValue;

import java.util.Set;

public interface MultiOperator {

    public DataValue transform(Set<DataValue> dataValues);

}
