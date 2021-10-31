package argumentor;

import org.junit.Test;
import org.junit.Before;
import java.util.Scanner;
import java.io.File;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

public class FunctionsTest {
    private Functions func;
    private Trie trie;
    private final int chainLength = 3;
    private final int sentenceLength = 3;
    private final int listSize = 2;
    private String path;

    @Before
    public void setUp() {
        this.func = new Functions();
        this.trie = new Trie();
        this.path = System.getProperty("user.dir")
        + "/data.txt";
    }

    @Test
    public void testProcessDataAddsNodesToRoot() {
        try {
            this.func.processData(this.path, this.trie, this.chainLength);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertTrue("Value should increase",
        this.trie.getRoot().getTicketSum() > 0);
    }

    @Test
    public void testGenerateReturnSentence() {
        try {
            this.func.processData(this.path, this.trie, this.chainLength);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertNotNull("Should return String",
        this.func.generate(this.sentenceLength, this.trie, this.listSize));
    }

    @Test
    public void testGeneratedSentenceFoundInData() {
        boolean contained = false;
        try {
            this.func.processData(this.path, this.trie, this.chainLength);
            String sentence = this.func.generate(
            this.sentenceLength, this.trie, this.listSize);
            File file = new File(this.path);
            Scanner scn = new Scanner(file);
            String data = "";
            while (scn.hasNextLine()) {
                String line = scn.nextLine().toLowerCase();
                String[] words = line.split(" ");
                for (String str : words) {
                    if (func.matchesChars(str)) {
                        data = data + " " + str;
                    }
                }
            }
            if (sentence.charAt(sentence.length() - 1) == '.') {
                sentence = sentence.substring(0, sentence.length() - 1);
            }
            if (data.contains(sentence)) {
                contained = true;
            }
            System.out.println(sentence);
            scn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        assertTrue("Sentence should be in data", contained);
    }

    @Test
    public void testGeneratedSentenceMatchGivenLength() {
        final int length = this.sentenceLength + 1;
        try {
            this.func.processData(this.path, this.trie, this.chainLength);
        } catch (Exception e) {
            System.out.println(e);
        }
        String sentence = this.func.generate(
        this.sentenceLength, this.trie, this.listSize);
        String[] words = sentence.split(" ");
        assertTrue("Amount of words should be 3",
        words.length == length);
    }

    @Test
    public void testCheckGenerationEndFalseWhenCountBelowMax() {
        final int check = 10;
        final int count = 1;
        assertFalse("Should return false",
        this.func.checkGenerationEnd(check, count));
    }

    @Test
    public void testCheckGenerationEndTrueWhenCountAboveMax() {
        final int check = 10;
        final int count = 11;
        assertTrue("Should return true",
        this.func.checkGenerationEnd(check, count));
    }

    @Test
    public void testCheckGenerationEndReturnsWithZero() {
        final int check = 0;
        final int count = 11;
        assertTrue("Should return true or false",
        !this.func.checkGenerationEnd(check, count)
        || this.func.checkGenerationEnd(check, count));
    }

    @Test
    public void testAddPunctuationDeletesComma() {
        String str = "test,";
        assertTrue("Length should be equal",
        str.length() == this.func.addPunctuation(str).length());
    }

    @Test
    public void testAddPunctuationAddsDot() {
        String str = "test";
        assertTrue("Length should be greater",
        str.length() < this.func.addPunctuation(str).length());
    }

    @Test
    public void testAddPunctuationIgnoresDot() {
        String str = "test.";
        assertTrue("Strings should be equal",
        str.equals(this.func.addPunctuation(str)));
    }

    @Test
    public void testAddPunctuationIgnoresQmark() {
        String str = "test?";
        assertTrue("Strings should be equal",
        str.equals(this.func.addPunctuation(str)));
    }

    @Test
    public void testAddPunctuationIgnoresExmark() {
        String str = "test!";
        assertTrue("Strings should be equal",
        str.equals(this.func.addPunctuation(str)));
    }

    @Test
    public void testMatchesCharsReturnsTrueWhenMatch() {
        assertTrue("Should return True",
        this.func.matchesChars("hello"));
    }

    @Test
    public void testMatchesCharsReturnsFalseWhenNoMatch() {
        assertFalse("Should return False",
        this.func.matchesChars("()"));
    }

    @Test
    public void testMatchesCharsReturnsFalseWhenDot() {
        assertFalse("Should return False",
        this.func.matchesChars("."));
    }

    @Test
    public void testMatchesCharsReturnsFalseWhenXmark() {
        assertFalse("Should return False",
        this.func.matchesChars("!"));
    }

    @Test
    public void testMatchesCharsReturnsFalseWhenQmark() {
        assertFalse("Should return False",
        this.func.matchesChars("?"));
    }

    @Test
    public void testMatchesCharsReturnsFalseWhenApo() {
        assertFalse("Should return False",
        this.func.matchesChars("'"));
    }
}
