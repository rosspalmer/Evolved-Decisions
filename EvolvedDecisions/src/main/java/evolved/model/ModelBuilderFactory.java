package evolved.model;

import evolved.data.DataSet;
import evolved.model.feed.DataValueFeed;
import evolved.model.feed.MultiInputSetDecorator;
import evolved.model.feed.SingleInputDecorator;
import evolved.model.feed.SingleOutputDecorator;
import evolved.model.compute.OperatorBuilderDecorator;

import java.util.Set;

public class ModelBuilderFactory {

    public static ComponentBuilder concreteBase() {
        return new ComponentBuilder() {
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

    public static ComponentBuilder generate(ModelBuilderConfiguration configuration) {

        ComponentBuilder newBuilder = concreteBase();

        newBuilder = configureInput(newBuilder, configuration);
        newBuilder = configureOutput(newBuilder, configuration);
        newBuilder = configureCompute(newBuilder, configuration);

        return newBuilder;
    }

    private static ComponentBuilder configureInput(ComponentBuilder componentBuilder, ModelBuilderConfiguration configuration) {

        Set<String> inputKeys = configuration.getInputKeys();
        if (inputKeys.size() == 1) {
            componentBuilder = new SingleInputDecorator(componentBuilder, inputKeys.stream().findAny().get());
        } else if (inputKeys.size() > 1) {
            componentBuilder = new MultiInputSetDecorator(componentBuilder, inputKeys);
        }
        return componentBuilder;

    }

    private static ComponentBuilder configureOutput(ComponentBuilder componentBuilder, ModelBuilderConfiguration configuration) {
        if (configuration.getOutputKey() != null) {
            componentBuilder = new SingleOutputDecorator(componentBuilder, configuration.getOutputKey());
        }
        return componentBuilder;
    }

    private static ComponentBuilder configureCompute(ComponentBuilder componentBuilder, ModelBuilderConfiguration configuration) {
        if (configuration.getOperator() != null) {
            componentBuilder = new OperatorBuilderDecorator(componentBuilder, configuration.getOperator());
        }
        return componentBuilder;
    }

}
