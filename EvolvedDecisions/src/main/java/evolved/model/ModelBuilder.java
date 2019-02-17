package evolved.model;

import evolved.data.DataSet;
import evolved.model.feed.DataValueFeed;

public interface ModelBuilder {

    DataValueFeed generateInputFeed(DataSet dataSet);

    DataValueFeed compute(DataValueFeed inputFeed);

    DataSet updateDataSet(DataSet dataSet, DataValueFeed outputFeed);

}
