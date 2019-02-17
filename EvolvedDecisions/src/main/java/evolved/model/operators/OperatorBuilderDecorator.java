package evolved.model.operators;

import evolved.data.DataValue;
import evolved.genetics.characters.OperatorType;
import evolved.model.ModelBuilder;
import evolved.model.ModelBuilderDecorator;
import evolved.model.feed.DataValueFeed;
import evolved.model.feed.ValueFeed;
import evolved.model.feed.ValueSetFeed;

import java.util.Set;

public class OperatorBuilderDecorator extends ModelBuilderDecorator {

    private Operator operator;

    public OperatorBuilderDecorator(ModelBuilder modelBuilder, OperatorType operatorType) {
        super(modelBuilder);
        this.operator = OperatorFactory.generate(operatorType);
    }

    public OperatorBuilderDecorator(ModelBuilder modelBuilder, Operator operator) {
        super(modelBuilder);
        this.operator = operator;
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

}
