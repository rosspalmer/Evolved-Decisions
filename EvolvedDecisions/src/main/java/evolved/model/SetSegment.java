package evolved.model;

import evolved.data.DataSet;

import java.util.HashSet;
import java.util.Set;

public class SetSegment implements ModelSegment {

    private Set<ModelComponent> modelComponents;

    public SetSegment() {
        modelComponents = new HashSet<>();
    }

    public void addToModelSequence(ModelComponent modelComponent) {
        modelComponents.add(modelComponent);
    }

    public Set<ModelComponent> getModelComponents() {
        return modelComponents;
    }

    @Override
    public DataSet transformDataSet(DataSet dataSet) {
        modelComponents.forEach(modelComponent -> modelComponent.updateDataSet(dataSet));
        return dataSet;
    }

}


