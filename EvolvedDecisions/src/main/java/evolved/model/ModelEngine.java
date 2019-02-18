package evolved.model;

import evolved.data.DataSet;

import java.util.ArrayList;
import java.util.List;

public class ModelEngine {

    private List<ModelSegment> modelSegments;

    public ModelEngine() {
        modelSegments = new ArrayList<>();
    }

    public void addModelSegment(ModelSegment modelSegment) {
        modelSegments.add(modelSegment);
    }

    public void updateDataSet(DataSet dataSet) {
        for (ModelSegment modelSegment : modelSegments) {
            dataSet = modelSegment.transformDataSet(dataSet);
        }
    }

}
