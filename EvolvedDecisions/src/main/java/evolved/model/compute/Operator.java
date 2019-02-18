package evolved.model.compute;

import evolved.data.DataValue;
import evolved.data.DataValueFactory;
import evolved.model.feed.DataValueFeed;
import evolved.model.feed.ValueFeed;
import evolved.model.feed.ValueSetFeed;

import java.util.Set;
import java.util.function.Function;

public enum Operator {

    AND("and", dataFeed -> {
        Set<DataValue> dataValues = ((ValueSetFeed) dataFeed).getDataValues();
        boolean result = dataValues.stream().allMatch(DataValue::getBooleanValue);
        return new ValueFeed(DataValueFactory.generateBooleanDataValue(result));
    }),
    OR("or", dataFeed -> {
        Set<DataValue> dataValues = ((ValueSetFeed) dataFeed).getDataValues();
        boolean result = dataValues.stream().anyMatch(DataValue::getBooleanValue);
        return new ValueFeed(DataValueFactory.generateBooleanDataValue(result));
    }),
    NOT("not", dataFeed -> {
        DataValue dataValue = ((ValueFeed) dataFeed).getDataValue();
        return new ValueFeed(DataValueFactory.generateBooleanDataValue(!dataValue.getBooleanValue()));
    }),


    SUM("sum", dataFeed -> {
        Set<DataValue> dataValues = ((ValueSetFeed) dataFeed).getDataValues();
        double result = dataValues.stream().mapToDouble(DataValue::getDoubleValue).sum();
        return new ValueFeed(DataValueFactory.generateDoubleDataValue(result));
    }),
    PROD("product", dataFeed -> {
        Set<DataValue> dataValues = ((ValueSetFeed) dataFeed).getDataValues();
        double result = dataValues.stream().mapToDouble(DataValue::getDoubleValue)
                .reduce(1, (a, b) -> a * b);
        return new ValueFeed(DataValueFactory.generateDoubleDataValue(result));
    }),
    INV("product", dataFeed -> {
        double result = ((ValueFeed) dataFeed).getDataValue().getDoubleValue();
        return new ValueFeed(DataValueFactory.generateDoubleDataValue(1 / result));
    }),


    MAX("max", dataFeed -> {
        Set<DataValue> dataValues = ((ValueSetFeed) dataFeed).getDataValues();
        double result = dataValues.stream().mapToDouble(DataValue::getDoubleValue).max().orElse(Double.MIN_VALUE);
        return new ValueFeed(DataValueFactory.generateDoubleDataValue(result));
    }),
    MIN("min", dataFeed -> {
        Set<DataValue> dataValues = ((ValueSetFeed) dataFeed).getDataValues();
        double result = dataValues.stream().mapToDouble(DataValue::getDoubleValue).min().orElse(Double.MAX_VALUE);
        return new ValueFeed(DataValueFactory.generateDoubleDataValue(result));
    }),
    ABS("absolute", dataFeed -> {
        double result = ((ValueFeed) dataFeed).getDataValue().getDoubleValue();
        return new ValueFeed(DataValueFactory.generateDoubleDataValue(Math.abs(result)));
    });


    private String stringValue;
    private Function<DataValueFeed, DataValueFeed> computeFunction;

    Operator(String stringValue, Function<DataValueFeed, DataValueFeed> computeFunction) {
        this.stringValue = stringValue;
        this.computeFunction = computeFunction;
    }

    public String getStringValue() {
        return stringValue;
    }

    public Function<DataValueFeed, DataValueFeed> getComputeFunction() {
        return computeFunction;
    }

}
