package evolved.model;

import java.util.ArrayList;
import java.util.List;

public class ModelEngine {

    private List<Model> modelSequence;

    public ModelEngine() {
        modelSequence = new ArrayList<>();
    }

    public void addToModelSequence(Model model) {
        modelSequence.add(model);
    }

    public List<Model> getModelSequence() {
        return modelSequence;
    }

    public DataSet transformDataSet(DataSet dataSet) {
        for (Model model : modelSequence) {
            dataSet = model.transformDataSet(this, dataSet);
        }
        return dataSet;
    }

}


