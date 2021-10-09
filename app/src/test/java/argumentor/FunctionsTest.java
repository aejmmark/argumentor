package argumentor;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class FunctionsTest {
    private Functions func;
    private Tree tree;

    @Before public void setUp() {
    	this.func = new Functions();
        this.tree = new Tree();
    }

    @Test public void testOGProcessDataAddsNodesToRoot() {
        try {
            this.func.processData(false, "data.txt", this.tree);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertTrue("Value should increase", this.tree.getRoot().getTicketSum() > 0);
    }

    @Test public void testAltProcessDataAddsNodesToRoot() {
        try {
            this.func.processData(true, "data.txt", this.tree);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertTrue("Value should increase", this.tree.getRoot().getTicketSum() > 0);
    }

    @Test public void testGenerateReturnSentence() {
        try {
            this.func.processData(true, "data.txt", this.tree);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertNotNull("Should return String", this.func.generate(0, this.tree));
    }

    @Test public void testGeneratedSentenceBelowGivenLength() {
        try {
            this.func.processData(true, "data.txt", this.tree);
        } catch (Exception e) {
            System.out.println(e);
        }
        String sentence = this.func.generate(5, this.tree);
        String[] words = sentence.split(" ");
        assertTrue("Amount of words should be below 5", words.length <= 5);
    
    }

    @Test public void testCheckEndFalseWhenCountBelowMax() {
        assertFalse("Should return false", this.func.checkEnd(10, 1));
    }

    @Test public void testCheckEndTrueWhenCountAboveMax() {
        assertTrue("Should return true", this.func.checkEnd(10, 11));
    }

    @Test public void checkPunctuationDeletesComma() {
        String str = "test,";
        assertTrue("Length should be equal", str.length() == this.func.checkPunctuation(str).length());
    }

    @Test public void checkPunctuationAddsDot() {
        String str = "test";
        assertTrue("Length should be greater", str.length() < this.func.checkPunctuation(str).length());
    }

    @Test public void checkPunctuationIgnoresDot() {
        String str = "test.";
        assertTrue("Strings should be equal", str.equals(this.func.checkPunctuation(str)));
    }

    @Test public void checkPunctuationIgnoresQmark() {
        String str = "test?";
        assertTrue("Strings should be equal", str.equals(this.func.checkPunctuation(str)));
    }

    @Test public void checkPunctuationIgnoresExmark() {
        String str = "test!";
        assertTrue("Strings should be equal", str.equals(this.func.checkPunctuation(str)));
    }
}
