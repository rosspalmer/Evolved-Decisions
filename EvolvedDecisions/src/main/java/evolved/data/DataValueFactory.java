package evolved.data;

public class DataValueFactory {

    public static DataValue generateBooleanDataValue(boolean value) {
        return new DataValue() {
            @Override
            public boolean getBooleanValue() {
                return value;
            }

            @Override
            public double getDoubleValue() {
                return value ? 1.0 : 0.0;
            }
        };
    }

    public static DataValue generateDoubleDataValue(double value) {
        DataValue dataValue = new DataValue() {
            @Override
            public boolean getBooleanValue() {
                return false;
            }
            @Override
            public double getDoubleValue() {
                return value;
            }
        };
        return new NotBooleanDecorator(dataValue);
    }

}
