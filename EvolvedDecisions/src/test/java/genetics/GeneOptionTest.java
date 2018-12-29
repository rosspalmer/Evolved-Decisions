package genetics;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneOptionTest {

    @Test
    public void test_toString() {
        String testString = GeneOption.BASE2.toString() + GeneOption.BASE6.toString() + GeneOption.BASEF.toString();
        assertEquals("26F", testString);
    }

    public void test_getCount() {

    }

}