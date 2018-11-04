package dtypes;

public class LongDataValue implements DataValue {

    private final long data;

    public LongDataValue(long data) {
        this.data = data;
    }

    public long getData() {
        return data;
    }

}
