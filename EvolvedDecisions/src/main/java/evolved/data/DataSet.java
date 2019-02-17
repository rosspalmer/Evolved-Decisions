package evolved.data;

import java.util.Map;
import java.util.Set;

public interface DataSet {

    DataValue getValue(String key);

    void setValue(String key, DataValue dataValue);

    Set<DataValue> getValues(Set<String> inputKeys);

    Map<String, DataValue> getValueMap(Set<String> inputKeys);

}
