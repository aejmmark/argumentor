package argumentor;

import org.junit.Test;
import static org.junit.Assert.*;

public class FunctionsTest {
    @Test public void testProcessDataAddsNodesToRoot() {
        Functions func = new Functions();
        Tree tree = new Tree();
        func.processData(tree);
        assertFalse("Should return false",tree.getRoot().checkEnd(1));
    }

    @Test public void testGenerateReturnSentence() {
        Functions func = new Functions();
        Tree tree = new Tree();
        func.processData(tree);
        assertNotNull("Should return String", func.generate(tree));
    }

    @Test public void testGenerateSentenceAdequateLength() {
        Functions func = new Functions();
        Tree tree = new Tree();
        func.processData(tree);
        String sentence = func.generate(tree);
        assertTrue("Should return String", sentence.length() >= 6);
    }
}
