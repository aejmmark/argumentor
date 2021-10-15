package argumentor;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

public class FunctionsTest {
    private Functions func;
    private Tree tree;

    @Before
    public void setUp() {
        this.func = new Functions();
        this.tree = new Tree();
    }

    @Test
    public void testOGProcessDataAddsNodesToRoot() {
        try {
            this.func.processData(false, "data.txt", this.tree);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertTrue("Value should increase",
        this.tree.getRoot().getTicketSum() > 0);
    }

    @Test
    public void testAltProcessDataAddsNodesToRoot() {
        try {
            this.func.processData(true, "data.txt", this.tree);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertTrue("Value should increase",
        this.tree.getRoot().getTicketSum() > 0);
    }

    @Test
    public void testGenerateReturnSentence() {
        try {
            this.func.processData(true, "data.txt", this.tree);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertNotNull("Should return String",
        this.func.generate(0, this.tree));
    }

    @Test
    public void testGeneratedSentenceBelowGivenLength() {
        final int maxLength = 5;
        try {
            this.func.processData(true, "data.txt", this.tree);
        } catch (Exception e) {
            System.out.println(e);
        }
        String sentence = this.func.generate(maxLength, this.tree);
        String[] words = sentence.split(" ");
        assertTrue("Amount of words should be below 5",
        words.length <= maxLength);
    }

    @Test
    public void testCheckEndFalseWhenCountBelowMax() {
        final int check = 10;
        final int count = 1;
        assertFalse("Should return false",
        this.func.checkGenerationEnd(check, count));
    }

    @Test
    public void testCheckEndTrueWhenCountAboveMax() {
        final int check = 10;
        final int count = 11;
        assertTrue("Should return true",
        this.func.checkGenerationEnd(check, count));
    }

    @Test
    public void checkPunctuationDeletesComma() {
        String str = "test,";
        assertTrue("Length should be equal",
        str.length() == this.func.checkPunctuation(str).length());
    }

    @Test
    public void checkPunctuationAddsDot() {
        String str = "test";
        assertTrue("Length should be greater",
        str.length() < this.func.checkPunctuation(str).length());
    }

    @Test
    public void checkPunctuationIgnoresDot() {
        String str = "test.";
        assertTrue("Strings should be equal",
        str.equals(this.func.checkPunctuation(str)));
    }

    @Test
    public void checkPunctuationIgnoresQmark() {
        String str = "test?";
        assertTrue("Strings should be equal",
        str.equals(this.func.checkPunctuation(str)));
    }

    @Test
    public void checkPunctuationIgnoresExmark() {
        String str = "test!";
        assertTrue("Strings should be equal",
        str.equals(this.func.checkPunctuation(str)));
    }
}
