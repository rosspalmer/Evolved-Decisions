package evolved.model.operators;

import evolved.data.DataSet;
import evolved.data.DataValue;
import evolved.model.ModelBuilder;
import evolved.model.feed.DataValueFeed;
import evolved.model.feed.ValueFeed;
import evolved.model.feed.ValueSetFeed;

import java.util.Set;

public class OperatorModelBuilder implements ModelBuilder {

    private Operator operator;

    public OperatorModelBuilder(Operator operator) {
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public DataValueFeed generateInputFeed(DataSet dataSet) {
        return null;
    }

    @Override
    public DataValueFeed compute(DataValueFeed inputFeed) {
        DataValue outputDataValue;
        if (operator instanceof SingleOperator) {
            DataValue inputDataValue = ((ValueFeed) inputFeed).getDataValue();
            outputDataValue = ((SingleOperator) operator).transformDataValue(inputDataValue);
        } else if (operator instanceof MultiOperator) {
            Set<DataValue> inputDataValues = ((ValueSetFeed) inputFeed).getDataValues();
            outputDataValue = ((MultiOperator) operator).transform(inputDataValues);
        } else {
            throw new RuntimeException("Unsupported Operator Type");
        }
        return new ValueFeed(outputDataValue);
    }

    @Override
    public DataSet updateDataSet(DataSet dataSet, DataValueFeed outputFeed) {
        return null;
    }

}
