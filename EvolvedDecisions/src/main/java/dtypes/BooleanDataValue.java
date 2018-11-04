package dtypes;

public class BooleanDataValue implements DataValue {

    private final boolean data;

    public BooleanDataValue(boolean data) {
        this.data = data;
    }

    public boolean getData() {
        return data;
    }

}
