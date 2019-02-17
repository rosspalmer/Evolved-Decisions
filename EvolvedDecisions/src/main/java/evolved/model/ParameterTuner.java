package evolved.model;

import java.util.Set;

public interface ParameterTuner {

    public Set<Parameter> updateParameters(DataSet dataSet, Set<Parameter> parameters);

}
