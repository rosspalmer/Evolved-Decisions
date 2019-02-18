package evolved.model;

import evolved.data.DataSet;

public interface ModelSegment {

    void addModelComponent(ModelComponent modelComponent);

    DataSet transformDataSet(DataSet dataSet);

}
