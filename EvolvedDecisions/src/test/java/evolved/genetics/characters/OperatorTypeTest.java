package evolved.genetics.characters;

import evolved.genetics.GeneOption;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperatorTypeTest {

    @Test
    public void test_valueOf() {

        OperatorType testA = OperatorType.getByGeneOption(GeneOption.BASE2);
        OperatorType testB = OperatorType.getByGeneOption(GeneOption.BASEA);

        assertEquals(OperatorType.NOT, testA);
        assertEquals(OperatorType.MAX, testB);

    }

}