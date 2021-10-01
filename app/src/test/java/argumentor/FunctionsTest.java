package argumentor;

import org.junit.Test;
import static org.junit.Assert.*;

public class FunctionsTest {
    @Test public void testOGProcessDataAddsNodesToRoot() {
        Functions func = new Functions();
        Tree tree = new Tree();
        try {
            func.processData(false, "data.txt", tree);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertFalse("Should return false",tree.getRoot().checkEnd(0, 1));
    }

    @Test public void testAltProcessDataAddsNodesToRoot() {
        Functions func = new Functions();
        Tree tree = new Tree();
        try {
            func.processData(true, "data.txt", tree);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertFalse("Should return false",tree.getRoot().checkEnd(0, 1));
    }

    @Test public void testGenerateReturnSentence() {
        Functions func = new Functions();
        Tree tree = new Tree();
        try {
            func.processData(true, "data.txt", tree);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertNotNull("Should return String", func.generate(0, tree));
    }

    @Test public void testGenerateRandomLengthSentenceAdequateLength() {
        Functions func = new Functions();
        Tree tree = new Tree();
        try {
            func.processData(true, "data.txt", tree);
        } catch (Exception e) {
            System.out.println(e);
        }
        String sentence = func.generate(0, tree);
        assertTrue("Should return String", sentence.length() >= 6);
    }

    @Test public void testGeneratedSentenceBelowGivenLength() {
        Functions func = new Functions();
        Tree tree = new Tree();
        try {
            func.processData(true, "data.txt", tree);
        } catch (Exception e) {
            System.out.println(e);
        }
        String sentence = func.generate(5, tree);
        String[] words = sentence.split(" ");
        assertTrue("Amount of words should be below 5", words.length <= 5);
    
    }
}
