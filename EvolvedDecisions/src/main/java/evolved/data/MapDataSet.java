package evolved.data;

import java.util.HashMap;
import java.util.Map;

public class MapDataSet implements DataSet {

    private Map<String, DataValue> dataValueMap;

    public MapDataSet() {
        dataValueMap = new HashMap<>();
    }

    @Override
    public DataValue getValue(String key) {
        return dataValueMap.get(key);
    }

    @Override
    public void setValue(String key, DataValue dataValue) {
        dataValueMap.put(key, dataValue);
    }
}
