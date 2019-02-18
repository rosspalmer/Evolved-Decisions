package evolved.model.compute;

import evolved.model.ModelBuilder;
import evolved.model.ModelBuilderDecorator;
import evolved.model.feed.DataValueFeed;


public class OperatorBuilderDecorator extends ModelBuilderDecorator {

    private Operator operator;

    public OperatorBuilderDecorator(ModelBuilder modelBuilder, Operator operator) {
        super(modelBuilder);
        this.operator = operator;
    }

    @Override
    public DataValueFeed compute(DataValueFeed inputFeed) {
        return operator.getComputeFunction().apply(inputFeed);
    }
}
