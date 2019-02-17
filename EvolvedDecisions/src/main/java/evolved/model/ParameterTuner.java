package evolved.model;

import evolved.data.DataSet;

import java.util.Set;

public interface ParameterTuner {

    public Set<Parameter> updateParameters(DataSet dataSet, Set<Parameter> parameters);

}
