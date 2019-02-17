package evolved.data;

public class NotBooleanDecorator extends DataValueDecorator {

    public NotBooleanDecorator(DataValue dataValue) {
        super(dataValue);
    }

    @Override
    public boolean getBooleanValue() {
        throw new RuntimeException("DataValue does not have Boolean value");
    }
}
