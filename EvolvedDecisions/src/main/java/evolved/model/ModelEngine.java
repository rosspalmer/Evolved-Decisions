package evolved.model;

import evolved.data.DataSet;

import java.util.ArrayList;
import java.util.List;

public class ModelEngine {

    private List<ModelBase> modelBaseSequence;

    public ModelEngine() {
        modelBaseSequence = new ArrayList<>();
    }

    public void addToModelSequence(ModelBase modelBase) {
        modelBaseSequence.add(modelBase);
    }

    public List<ModelBase> getModelBaseSequence() {
        return modelBaseSequence;
    }

    public DataSet transformDataSet(DataSet dataSet) {
        for (ModelBase modelBase : modelBaseSequence) {
            dataSet = modelBase.transformDataSet(dataSet, this);
        }
        return dataSet;
    }

}


