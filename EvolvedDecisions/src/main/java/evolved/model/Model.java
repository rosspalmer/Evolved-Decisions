package evolved.model;

import evolved.data.DataSet;

public interface Model {

    public DataSet transformDataSet(ModelEngine modelEngine, DataSet dataSet);

}
