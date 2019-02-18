package evolved.model.compute;

import evolved.data.DataValue;
import evolved.data.DataValueFactory;
import evolved.model.feed.DataValueFeed;
import evolved.model.feed.ValueFeed;
import evolved.model.feed.ValueSetFeed;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import static org.junit.Assert.*;

public class OperatorTest {

    @Test
    public void getStringValue() {
        assertEquals("and", Operator.AND.getStringValue());
    }

    @Test
    public void getComputeFunction_AND() {

        Set<DataValue> inputValues = new HashSet<>();
        inputValues.add(DataValueFactory.generateBooleanDataValue(true));
        inputValues.add(DataValueFactory.generateBooleanDataValue(true));
        inputValues.add(DataValueFactory.generateBooleanDataValue(true));
        DataValueFeed inputFeed = new ValueSetFeed(inputValues);

        DataValueFeed outputFeed = Operator.AND.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertTrue(((ValueFeed) outputFeed).getDataValue().getBooleanValue());

        inputValues = new HashSet<>();
        inputValues.add(DataValueFactory.generateBooleanDataValue(true));
        inputValues.add(DataValueFactory.generateBooleanDataValue(false));
        inputValues.add(DataValueFactory.generateBooleanDataValue(true));
        inputFeed = new ValueSetFeed(inputValues);

        outputFeed = Operator.AND.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertFalse(((ValueFeed) outputFeed).getDataValue().getBooleanValue());

    }

    @Test
    public void getComputeFunction_OR() {

        Set<DataValue> inputValues = new HashSet<>();
        inputValues.add(DataValueFactory.generateBooleanDataValue(false));
        inputValues.add(DataValueFactory.generateBooleanDataValue(true));
        inputValues.add(DataValueFactory.generateBooleanDataValue(false));
        DataValueFeed inputFeed = new ValueSetFeed(inputValues);

        DataValueFeed outputFeed = Operator.OR.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertTrue(((ValueFeed) outputFeed).getDataValue().getBooleanValue());

        inputValues = new HashSet<>();
        inputValues.add(DataValueFactory.generateBooleanDataValue(false));
        inputValues.add(DataValueFactory.generateBooleanDataValue(false));
        inputValues.add(DataValueFactory.generateBooleanDataValue(false));
        inputFeed = new ValueSetFeed(inputValues);

        outputFeed = Operator.OR.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertFalse(((ValueFeed) outputFeed).getDataValue().getBooleanValue());

    }

    @Test
    public void getComputeFunction_NOT() {

        DataValue inputValue = DataValueFactory.generateBooleanDataValue(false);
        DataValueFeed inputFeed = new ValueFeed(inputValue);

        DataValueFeed outputFeed = Operator.NOT.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertTrue(((ValueFeed) outputFeed).getDataValue().getBooleanValue());

        inputValue = DataValueFactory.generateBooleanDataValue(true);
        inputFeed = new ValueFeed(inputValue);

        outputFeed = Operator.NOT.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertFalse(((ValueFeed) outputFeed).getDataValue().getBooleanValue());

    }

    @Test
    public void getComputeFunction_SUM() {

        Set<DataValue> inputValues = new HashSet<>();
        inputValues.add(DataValueFactory.generateDoubleDataValue(1.0));
        inputValues.add(DataValueFactory.generateDoubleDataValue(3.6));
        inputValues.add(DataValueFactory.generateDoubleDataValue(9.5));
        DataValueFeed inputFeed = new ValueSetFeed(inputValues);

        DataValueFeed outputFeed = Operator.SUM.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertEquals(14.1, ((ValueFeed) outputFeed).getDataValue().getDoubleValue(), 0.0001);

        inputValues = new HashSet<>();
        inputValues.add(DataValueFactory.generateDoubleDataValue(1.0));
        inputValues.add(DataValueFactory.generateDoubleDataValue(-3.6));
        inputValues.add(DataValueFactory.generateDoubleDataValue(9.5));
        inputFeed = new ValueSetFeed(inputValues);

        outputFeed = Operator.SUM.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertEquals(6.9, ((ValueFeed) outputFeed).getDataValue().getDoubleValue(), 0.0001);

    }

    @Test
    public void getComputeFunction_PROD() {

        Set<DataValue> inputValues = new HashSet<>();
        inputValues.add(DataValueFactory.generateDoubleDataValue(1.0));
        inputValues.add(DataValueFactory.generateDoubleDataValue(3.6));
        inputValues.add(DataValueFactory.generateDoubleDataValue(9.5));
        DataValueFeed inputFeed = new ValueSetFeed(inputValues);

        DataValueFeed outputFeed = Operator.PROD.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertEquals(34.2, ((ValueFeed) outputFeed).getDataValue().getDoubleValue(), 0.0001);

        inputValues = new HashSet<>();
        inputValues.add(DataValueFactory.generateDoubleDataValue(1.0));
        inputValues.add(DataValueFactory.generateDoubleDataValue(-3.6));
        inputValues.add(DataValueFactory.generateDoubleDataValue(9.5));
        inputFeed = new ValueSetFeed(inputValues);

        outputFeed = Operator.PROD.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertEquals(-34.2, ((ValueFeed) outputFeed).getDataValue().getDoubleValue(), 0.0001);

    }

    @Test
    public void getComputeFunction_INV() {

        DataValue inputValue = DataValueFactory.generateDoubleDataValue(2.0);
        DataValueFeed inputFeed = new ValueFeed(inputValue);

        DataValueFeed outputFeed = Operator.INV.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertEquals(0.5, ((ValueFeed) outputFeed).getDataValue().getDoubleValue(), 0.0001);

        inputValue = DataValueFactory.generateDoubleDataValue(0.5);
        inputFeed = new ValueFeed(inputValue);

        outputFeed = Operator.INV.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertEquals(2.0, ((ValueFeed) outputFeed).getDataValue().getDoubleValue(), 0.0001);

    }

    @Test
    public void getComputeFunction_MAX() {

        Set<DataValue> inputValues = new HashSet<>();
        inputValues.add(DataValueFactory.generateDoubleDataValue(1.0));
        inputValues.add(DataValueFactory.generateDoubleDataValue(3.6));
        inputValues.add(DataValueFactory.generateDoubleDataValue(9.5));
        DataValueFeed inputFeed = new ValueSetFeed(inputValues);

        DataValueFeed outputFeed = Operator.MAX.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertEquals(9.5, ((ValueFeed) outputFeed).getDataValue().getDoubleValue(), 0.0001);

        inputValues = new HashSet<>();
        inputValues.add(DataValueFactory.generateDoubleDataValue(1.0));
        inputValues.add(DataValueFactory.generateDoubleDataValue(3.6));
        inputValues.add(DataValueFactory.generateDoubleDataValue(-9.5));
        inputFeed = new ValueSetFeed(inputValues);

        outputFeed = Operator.MAX.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertEquals(3.6, ((ValueFeed) outputFeed).getDataValue().getDoubleValue(), 0.0001);

    }

    @Test
    public void getComputeFunction_MIN() {

        Set<DataValue> inputValues = new HashSet<>();
        inputValues.add(DataValueFactory.generateDoubleDataValue(1.0));
        inputValues.add(DataValueFactory.generateDoubleDataValue(3.6));
        inputValues.add(DataValueFactory.generateDoubleDataValue(9.5));
        DataValueFeed inputFeed = new ValueSetFeed(inputValues);

        DataValueFeed outputFeed = Operator.MIN.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertEquals(1.0, ((ValueFeed) outputFeed).getDataValue().getDoubleValue(), 0.0001);

        inputValues = new HashSet<>();
        inputValues.add(DataValueFactory.generateDoubleDataValue(1.0));
        inputValues.add(DataValueFactory.generateDoubleDataValue(3.6));
        inputValues.add(DataValueFactory.generateDoubleDataValue(-9.5));
        inputFeed = new ValueSetFeed(inputValues);

        outputFeed = Operator.MIN.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertEquals(-9.5, ((ValueFeed) outputFeed).getDataValue().getDoubleValue(), 0.0001);

    }

    @Test
    public void getComputeFunction_ABS() {

        DataValue inputValue = DataValueFactory.generateDoubleDataValue(2.7);
        DataValueFeed inputFeed = new ValueFeed(inputValue);

        DataValueFeed outputFeed = Operator.ABS.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertEquals(2.7, ((ValueFeed) outputFeed).getDataValue().getDoubleValue(), 0.0001);

        inputValue = DataValueFactory.generateDoubleDataValue(0.5);
        inputFeed = new ValueFeed(inputValue);

        outputFeed = Operator.INV.getComputeFunction().apply(inputFeed);

        assertTrue(outputFeed instanceof ValueFeed);
        assertEquals(2.0, ((ValueFeed) outputFeed).getDataValue().getDoubleValue(), 0.0001);

    }

}