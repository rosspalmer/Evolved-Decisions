package evolved.model;

import evolved.data.DataSet;
import evolved.data.DataValueFactory;
import evolved.data.MapDataSet;
import evolved.model.compute.Operator;
import evolved.model.compute.OperatorBuilderDecorator;
import evolved.model.feed.MultiInputSetDecorator;
import evolved.model.feed.SingleInputDecorator;
import evolved.model.feed.SingleOutputDecorator;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ModelEngineTest {

    @Test
    public void updateDataSet_Operators() {

        DataSet dataSet = new MapDataSet();
        dataSet.setValue("boolA", DataValueFactory.generateBooleanDataValue(true));
        dataSet.setValue("boolB", DataValueFactory.generateBooleanDataValue(false));
        dataSet.setValue("boolC", DataValueFactory.generateBooleanDataValue(true));

        dataSet.setValue("doubleA", DataValueFactory.generateDoubleDataValue(2.3));
        dataSet.setValue("doubleB", DataValueFactory.generateDoubleDataValue(-2.3));
        dataSet.setValue("doubleC", DataValueFactory.generateDoubleDataValue(0.1));

        ModelEngine modelEngine = new ModelEngine();
        ModelSegment modelSegmentA = new SetSegment();
        ModelSegment modelSegmentB = new SetSegment();

        Set<String> andBuilderAKeys = new HashSet<>();
        andBuilderAKeys.add("boolA");
        andBuilderAKeys.add("boolC");
        ComponentBuilder andBuilder = ComponentBuilderFactory.concreteBase();
        andBuilder = new MultiInputSetDecorator(andBuilder, andBuilderAKeys);
        andBuilder = new SingleOutputDecorator(andBuilder, "boolD");
        andBuilder = new OperatorBuilderDecorator(andBuilder, Operator.AND);

        modelSegmentA.addModelComponent(new ModelComponent(andBuilder));

        Set<String> orBuilderKeys = new HashSet<>();
        orBuilderKeys.add("boolB");
        orBuilderKeys.add("boolD");
        ComponentBuilder orBuilder = ComponentBuilderFactory.concreteBase();
        orBuilder = new MultiInputSetDecorator(orBuilder, orBuilderKeys);
        orBuilder = new SingleOutputDecorator(orBuilder, "boolE");
        orBuilder = new OperatorBuilderDecorator(orBuilder, Operator.OR);

        modelSegmentB.addModelComponent(new ModelComponent(orBuilder));

        Set<String> sumBuilderKeys = new HashSet<>();
        sumBuilderKeys.add("doubleB");
        sumBuilderKeys.add("doubleC");
        ComponentBuilder sumBuilder = ComponentBuilderFactory.concreteBase();
        sumBuilder = new MultiInputSetDecorator(sumBuilder, sumBuilderKeys);
        sumBuilder = new SingleOutputDecorator(sumBuilder, "doubleD");
        sumBuilder = new OperatorBuilderDecorator(sumBuilder, Operator.SUM);

        modelSegmentB.addModelComponent(new ModelComponent(sumBuilder));

        modelEngine.addModelSegment(modelSegmentA);
        modelEngine.addModelSegment(modelSegmentB);

        modelEngine.updateDataSet(dataSet);

        assertTrue(dataSet.getValue("boolA").getBooleanValue());
        assertFalse(dataSet.getValue("boolB").getBooleanValue());
        assertTrue(dataSet.getValue("boolC").getBooleanValue());
        assertTrue(dataSet.getValue("boolD").getBooleanValue());
        assertTrue(dataSet.getValue("boolD").getBooleanValue());

        assertEquals(2.3, dataSet.getValue("doubleA").getDoubleValue(), 0.0001);
        assertEquals(-2.3, dataSet.getValue("doubleB").getDoubleValue(), 0.0001);
        assertEquals(0.1, dataSet.getValue("doubleC").getDoubleValue(), 0.0001);
        assertEquals(-2.2, dataSet.getValue("doubleD").getDoubleValue(), 0.0001);

    }

}