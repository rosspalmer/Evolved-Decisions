package evolved.model.feed;

import evolved.data.DataSet;
import evolved.data.DataValue;
import evolved.data.DataValueFactory;
import evolved.data.MapDataSet;
import evolved.model.ModelBuilder;
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

        ModelBuilder modelBuilder = ModelBuilderFactory.generate(null);
        modelBuilder = new SingleInputDecorator(modelBuilder, "inputA");

        DataValueFeed inputFeed = modelBuilder.generateInputFeed(dataSet);

        assertTrue(inputFeed instanceof ValueFeed);
        assertEquals(1.03, ((ValueFeed) inputFeed).getDataValue().getDoubleValue(), 0.0001);

        modelBuilder = ModelBuilderFactory.generate(null);
        modelBuilder = new SingleInputDecorator(modelBuilder, "inputB");

        inputFeed = modelBuilder.generateInputFeed(dataSet);

        assertTrue(inputFeed instanceof ValueFeed);
        assertTrue(((ValueFeed) inputFeed).getDataValue().getBooleanValue());

    }

    @Test
    public void generateInputFeed_Multi() {

        DataSet dataSet = getTestDataSet();

        ModelBuilder modelBuilder = ModelBuilderFactory.generate(null);
        Set<String> inputKeys = new HashSet<>();
        inputKeys.add("inputA");
        modelBuilder = new MultiInputSetDecorator(modelBuilder, inputKeys);

        DataValueFeed inputFeed = modelBuilder.generateInputFeed(dataSet);
        ValueSetFeed valueSetFeed = (ValueSetFeed) inputFeed;

        assertEquals(1, valueSetFeed.getDataValues().size());
        assertEquals(1.03, valueSetFeed.getDataValues().stream()
                .mapToDouble(DataValue::getDoubleValue).sum(), 0.0001);

        modelBuilder = ModelBuilderFactory.generate(null);
        inputKeys = new HashSet<>();
        inputKeys.add("inputA");
        inputKeys.add("outputA");
        modelBuilder = new MultiInputSetDecorator(modelBuilder, inputKeys);

        inputFeed = modelBuilder.generateInputFeed(dataSet);
        valueSetFeed = (ValueSetFeed) inputFeed;

        assertEquals(2, valueSetFeed.getDataValues().size());
        assertEquals(5.23, valueSetFeed.getDataValues().stream()
                .mapToDouble(DataValue::getDoubleValue).sum(), 0.0001);

    }

    @Test
    public void updateDataSet_Single() {

        DataSet dataSet = getTestDataSet();

        ModelBuilder modelBuilder = ModelBuilderFactory.generate(null);
        modelBuilder = new SingleOutputDecorator(modelBuilder, "outputA");

        assertEquals(4.2, dataSet.getValue("outputA").getDoubleValue(), 0.0001);
        assertFalse(dataSet.getValue("outputB").getBooleanValue());

        DataValueFeed outputValue = new ValueFeed(DataValueFactory.generateDoubleDataValue(2.27));
        modelBuilder.updateDataSet(dataSet, outputValue);

        assertEquals(2.27, dataSet.getValue("outputA").getDoubleValue(), 0.0001);
        assertFalse(dataSet.getValue("outputB").getBooleanValue());

        modelBuilder = ModelBuilderFactory.generate(null);
        modelBuilder = new SingleOutputDecorator(modelBuilder, "outputB");

        outputValue = new ValueFeed(DataValueFactory.generateBooleanDataValue(true));
        modelBuilder.updateDataSet(dataSet, outputValue);

        assertEquals(2.27, dataSet.getValue("outputA").getDoubleValue(), 0.0001);
        assertTrue(dataSet.getValue("outputB").getBooleanValue());

    }

}