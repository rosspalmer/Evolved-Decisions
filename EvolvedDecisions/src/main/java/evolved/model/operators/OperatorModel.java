package evolved.model.operators;

import evolved.data.DataSet;
import evolved.model.ModelBase;
import evolved.model.ModelEngine;
import evolved.model.ParameterTuner;

public class OperatorModel extends ModelBase {

    public OperatorModel(ModelEngine modelEngine, ParameterTuner parameterTuner) {
        super(modelEngine, parameterTuner);
    }

    @Override
    public DataSet transformDataSet(DataSet dataSet) {
        return null;
    }

}
