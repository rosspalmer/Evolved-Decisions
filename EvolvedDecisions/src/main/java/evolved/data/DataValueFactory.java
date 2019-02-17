package evolved.data;

public class DataValueFactory {

    public static DataValue generateBooleanDataValue(boolean value) {
        return () -> value;
    }

}
