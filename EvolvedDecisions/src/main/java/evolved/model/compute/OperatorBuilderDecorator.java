package evolved.model.compute;

import evolved.model.ComponentBuilder;
import evolved.model.ComponentBuilderDecorator;
import evolved.model.feed.DataValueFeed;


public class OperatorBuilderDecorator extends ComponentBuilderDecorator {

    private Operator operator;

    public OperatorBuilderDecorator(ComponentBuilder componentBuilder, Operator operator) {
        super(componentBuilder);
        this.operator = operator;
    }

    @Override
    public DataValueFeed compute(DataValueFeed inputFeed) {
        return operator.getComputeFunction().apply(inputFeed);
    }
}
