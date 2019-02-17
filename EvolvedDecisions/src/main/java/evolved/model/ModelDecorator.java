package evolved.model;

import evolved.data.DataSet;

public class ModelDecorator implements Model {

    private Model model;

    public ModelDecorator(Model model) {
        this.model = model;
    }

    @Override
    public DataSet transformDataSet(DataSet dataSet) {
        return model.transformDataSet(dataSet);
    }
}
