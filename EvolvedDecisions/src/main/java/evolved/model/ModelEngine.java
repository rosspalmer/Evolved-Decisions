package evolved.model;

import evolved.data.DataSet;

import java.util.ArrayList;
import java.util.List;

public class ModelEngine {

    private List<Model> modelBuildeSequence;

    public ModelEngine() {
        modelBuildeSequence = new ArrayList<>();
    }

    public void addToModelSequence(Model model) {
        modelBuildeSequence.add(model);
    }

    public List<Model> getModelBuildeSequence() {
        return modelBuildeSequence;
    }

    public DataSet transformDataSet(DataSet dataSet) {
        for (Model modelBuilde : modelBuildeSequence) {
            dataSet = modelBuilde.transformDataSet(this, dataSet);
        }
        return dataSet;
    }

}


