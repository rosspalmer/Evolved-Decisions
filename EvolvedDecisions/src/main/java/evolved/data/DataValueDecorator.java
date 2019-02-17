package evolved.data;

public class DataValueDecorator implements DataValue {

    private final DataValue dataValue;

    public DataValueDecorator(DataValue dataValue) {
        this.dataValue = dataValue;
    }

    public DataValue getDataValue() {
        return dataValue;
    }

    @Override
    public boolean getBooleanValue() {
        return getDataValue().getBooleanValue();
    }

    @Override
    public double getDoubleValue() {
        return getDataValue().getDoubleValue();
    }
}
