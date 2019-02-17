package evolved.data;

public class DataValueDecorator implements DataValue {

    private final DataValue dataValue;

    public DataValueDecorator(DataValue dataValue) {
        this.dataValue = dataValue;
    }

    public DataValue getDataValue() {
        return dataValue;
    }
}
