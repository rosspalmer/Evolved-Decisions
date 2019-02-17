package evolved.model.operators;

import evolved.data.DataSet;
import evolved.model.Model;
import evolved.model.ModelEngine;
import evolved.model.ParameterTuner;

public class OperatorModel extends Model {

    public OperatorModel(ModelEngine modelEngine, ParameterTuner parameterTuner) {
        super(modelEngine, parameterTuner);
    }

    @Override
    public DataSet transformDataSet(DataSet dataSet) {
        return null;
    }

}
