package dtypes;

public class NumericDataValue implements DataValue {

    private final double data;

    public NumericDataValue(double data) {
        this.data = data;
    }

    public double getDataValue() {
        return data;
    }
}
