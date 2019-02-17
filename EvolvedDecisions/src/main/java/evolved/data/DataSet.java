package evolved.data;

public interface DataSet {

    public DataValue getValue(String key);

    public void setValue(String key, DataValue dataValue);

}
