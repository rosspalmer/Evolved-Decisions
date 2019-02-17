package evolved.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public Set<DataValue> getValues(Set<String> inputKeys) {
        return dataValueMap.entrySet().stream()
                .filter(entry -> inputKeys.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
    }
}
