package dtypes;


import java.util.HashMap;
import java.util.Map;

public class DataValueSet {

    private Map<Long, DataValue> dataValues;

    public DataValueSet() {
        dataValues = new HashMap<>();
    }

    public void addDataValue(long dataValueId, DataValue dataValue) {
        dataValues.put(dataValueId, dataValue);
    }

    public DataValue getSingleValue() {
        if (dataValues.size() != 1) {
            throw new RuntimeException("Number of dataValues != 1 (Single)");
        } else {
            return dataValues.values().stream().findAny().get();
        }
    }

    public Map<Long, DataValue> getDataValues() {
        return dataValues;
    }

    public void mergeInOtherDataValues(DataValueSet otherDataValueSet) {
        this.dataValues.putAll(otherDataValueSet.dataValues);
    }

}
