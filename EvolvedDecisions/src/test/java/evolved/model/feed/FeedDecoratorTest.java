package evolved.model.feed;

import evolved.data.DataSet;
import evolved.data.DataValue;
import evolved.data.DataValueFactory;
import evolved.data.MapDataSet;
import evolved.model.ComponentBuilder;
import evolved.model.ModelBuilderFactory;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class FeedDecoratorTest {

    private DataSet getTestDataSet() {

        DataSet dataSet = new MapDataSet();
        dataSet.setValue("inputA", DataValueFactory.generateDoubleDataValue(1.03));
        dataSet.setValue("inputB", DataValueFactory.generateBooleanDataValue(true));
        dataSet.setValue("outputA", DataValueFactory.generateDoubleDataValue(4.2));
        dataSet.setValue("outputB", DataValueFactory.generateBooleanDataValue(false));

        return dataSet;
    }

    @Test
    public void generateInputFeed_Single() {

        DataSet dataSet = getTestDataSet();

        ComponentBuilder componentBuilder = ModelBuilderFactory.concreteBase();
        componentBuilder = new SingleInputDecorator(componentBuilder, "inputA");

        DataValueFeed inputFeed = componentBuilder.generateInputFeed(dataSet);

        assertTrue(inputFeed instanceof ValueFeed);
        assertEquals(1.03, ((ValueFeed) inputFeed).getDataValue().getDoubleValue(), 0.0001);

        componentBuilder = ModelBuilderFactory.concreteBase();
        componentBuilder = new SingleInputDecorator(componentBuilder, "inputB");

        inputFeed = componentBuilder.generateInputFeed(dataSet);

        assertTrue(inputFeed instanceof ValueFeed);
        assertTrue(((ValueFeed) inputFeed).getDataValue().getBooleanValue());

    }

    @Test
    public void generateInputFeed_Multi() {

        DataSet dataSet = getTestDataSet();

        ComponentBuilder componentBuilder = ModelBuilderFactory.concreteBase();
        Set<String> inputKeys = new HashSet<>();
        inputKeys.add("inputA");
        componentBuilder = new MultiInputSetDecorator(componentBuilder, inputKeys);

        DataValueFeed inputFeed = componentBuilder.generateInputFeed(dataSet);
        ValueSetFeed valueSetFeed = (ValueSetFeed) inputFeed;

        assertEquals(1, valueSetFeed.getDataValues().size());
        assertEquals(1.03, valueSetFeed.getDataValues().stream()
                .mapToDouble(DataValue::getDoubleValue).sum(), 0.0001);

        componentBuilder = ModelBuilderFactory.concreteBase();
        inputKeys = new HashSet<>();
        inputKeys.add("inputA");
        inputKeys.add("outputA");
        componentBuilder = new MultiInputSetDecorator(componentBuilder, inputKeys);

        inputFeed = componentBuilder.generateInputFeed(dataSet);
        valueSetFeed = (ValueSetFeed) inputFeed;

        assertEquals(2, valueSetFeed.getDataValues().size());
        assertEquals(5.23, valueSetFeed.getDataValues().stream()
                .mapToDouble(DataValue::getDoubleValue).sum(), 0.0001);

    }

    @Test
    public void updateDataSet_Single() {

        DataSet dataSet = getTestDataSet();

        ComponentBuilder componentBuilder = ModelBuilderFactory.concreteBase();
        componentBuilder = new SingleOutputDecorator(componentBuilder, "outputA");

        assertEquals(4.2, dataSet.getValue("outputA").getDoubleValue(), 0.0001);
        assertFalse(dataSet.getValue("outputB").getBooleanValue());

        DataValueFeed outputValue = new ValueFeed(DataValueFactory.generateDoubleDataValue(2.27));
        componentBuilder.updateDataSet(dataSet, outputValue);

        assertEquals(2.27, dataSet.getValue("outputA").getDoubleValue(), 0.0001);
        assertFalse(dataSet.getValue("outputB").getBooleanValue());

        componentBuilder = ModelBuilderFactory.concreteBase();
        componentBuilder = new SingleOutputDecorator(componentBuilder, "outputB");

        outputValue = new ValueFeed(DataValueFactory.generateBooleanDataValue(true));
        componentBuilder.updateDataSet(dataSet, outputValue);

        assertEquals(2.27, dataSet.getValue("outputA").getDoubleValue(), 0.0001);
        assertTrue(dataSet.getValue("outputB").getBooleanValue());

    }

}