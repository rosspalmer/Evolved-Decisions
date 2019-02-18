package evolved.model;

import evolved.data.DataSet;

import java.util.HashSet;
import java.util.Set;

public class ModelEngine {

    private Set<Model> models;

    public ModelEngine() {
        models = new HashSet<>();
    }

    public void addToModelSequence(Model model) {
        models.add(model);
    }

    public Set<Model> getModels() {
        return models;
    }

    public DataSet transformDataSet(DataSet dataSet) {
        models.forEach(model -> model.transformDataSet(this, dataSet));
        return dataSet;
    }

}


