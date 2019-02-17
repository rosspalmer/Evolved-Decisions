package evolved.model.operators;

import evolved.data.DataSet;
import evolved.model.base.ModelBase;
import evolved.model.ModelEngine;
import evolved.model.ParameterTuner;

public class OperatorModel extends ModelBase {

    private Operator operator;

    public OperatorModel(ModelEngine modelEngine, ParameterTuner parameterTuner, Operator operator) {
        super(modelEngine, parameterTuner);
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public DataSet transformDataSet(DataSet dataSet) {
        if (getOperator() instanceof SingleOperator) {

        }
        return null;
    }

}
