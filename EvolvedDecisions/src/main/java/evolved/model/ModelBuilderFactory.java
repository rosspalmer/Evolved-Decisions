package evolved.model;

import evolved.data.DataSet;
import evolved.model.feed.DataValueFeed;
import evolved.model.feed.MultiInputSetDecorator;
import evolved.model.feed.SingleInputDecorator;
import evolved.model.feed.SingleOutputDecorator;
import evolved.model.compute.OperatorBuilderDecorator;

import java.util.Set;

public class ModelBuilderFactory {

    public static ModelBuilder generate(ModelBuilderConfiguration configuration) {

        ModelBuilder newBuilder = concreteBase(configuration);

        newBuilder = configureInput(newBuilder, configuration);
        newBuilder = configureOutput(newBuilder, configuration);
        newBuilder = configureCompute(newBuilder, configuration);

        return newBuilder;
    }

    private static ModelBuilder configureInput(ModelBuilder modelBuilder, ModelBuilderConfiguration configuration) {

        Set<String> inputKeys = configuration.getInputKeys();
        if (inputKeys.size() == 1) {
            modelBuilder = new SingleInputDecorator(modelBuilder, inputKeys.stream().findAny().get());
        } else if (inputKeys.size() > 1) {
            modelBuilder = new MultiInputSetDecorator(modelBuilder, inputKeys);
        }
        return modelBuilder;

    }

    private static ModelBuilder configureOutput(ModelBuilder modelBuilder, ModelBuilderConfiguration configuration) {
        return new SingleOutputDecorator(modelBuilder, configuration.getOutputKey());
    }

    private static ModelBuilder configureCompute(ModelBuilder modelBuilder, ModelBuilderConfiguration configuration) {
        return new OperatorBuilderDecorator(modelBuilder, configuration.getOperator());
    }

    private static ModelBuilder concreteBase(ModelBuilderConfiguration configuration) {
        return new ModelBuilder() {
            @Override
            public DataValueFeed generateInputFeed(DataSet dataSet) {
                return null;
            }

            @Override
            public DataValueFeed compute(DataValueFeed inputFeed) {
                return null;
            }

            @Override
            public void updateDataSet(DataSet dataSet, DataValueFeed outputFeed) {
            }
        };
    }

}
