package argumentor;

import org.junit.Test;
import static org.junit.Assert.*;

public class FunctionsTest {
    @Test public void testProcessDataAddsNodesToRoot() {
        Functions func = new Functions();
        Tree tree = new Tree();
        try {
            func.processData(true, "dataTest.txt", tree);
        } catch (Exception e) {
            System.out.println(e);
        }assertFalse("Should return false",tree.getRoot().checkEnd(0, 1));
    }

    @Test public void testGenerateReturnSentence() {
        Functions func = new Functions();
        Tree tree = new Tree();
        try {
            func.processData(true, "dataTest.txt", tree);
        } catch (Exception e) {
            System.out.println(e);
        }assertNotNull("Should return String", func.generate(0, tree));
    }

    @Test public void testGenerateSentenceAdequateLength() {
        Functions func = new Functions();
        Tree tree = new Tree();
        try {
            func.processData(true, "dataTest.txt", tree);
        } catch (Exception e) {
            System.out.println(e);
        }
        String sentence = func.generate(0, tree);
        assertTrue("Should return String", sentence.length() >= 6);
    }
}
