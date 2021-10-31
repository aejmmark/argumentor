package argumentor;

import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class TrieTest {
    private Trie trie;
    private ArrayList<String> list;
    private final int chainLength = 3;

    @Before
    public void setUp() {
        this.trie = new Trie();
        this.list = new ArrayList<String>();
        this.list.add("i");
        this.list.add("was");
    }

    @Test
    public void testRootGetter() {
        assertNotNull("Should return root Node",
        this.trie.getRoot());
    }

    @Test
    public void testAddNodeAddsEdgesToRoot() {
        this.trie.addNodes(list);
        assertTrue("Hashmap size should increase",
        this.trie.getRoot().getEdgeMap().size() > 0);
    }

    @Test
    public void testAddNodeAddsStopsAtDot() {
        this.list = new ArrayList<>();
        this.list.add("unit.");
        this.list.add("test");
        this.list.add("list");
        this.trie.addNodes(list);
        assertTrue("Hashmap size should be 0",
        this.trie.getRoot().getNode("unit.").getEdgeMap().size()
        == 0);
    }

    @Test
    public void testAddNodeAddsFreq() {
        this.trie.addNodes(list);
        int freq = this.trie.getRoot().getNode("i").getFreq();
        this.trie.addNodes(list);
        assertTrue("Freq should increase",
        this.trie.getRoot().getNode("i").getFreq() > freq);
    }

    @Test
    public void testAddNodeAddsToTicketSum() {
        int sum = this.trie.getRoot().getTicketSum();
        this.trie.addNodes(list);
        assertTrue("TicketSum should increase",
        this.trie.getRoot().getTicketSum() > sum);
    }

    @Test
    public void testAddNodeReturnsFalseWhenNoDotsPresent() {
        assertFalse("Should return false",
        this.trie.addNodes(list));
    }

    @Test
    public void testAddNodeReturnsTrueWhenDotsPresent() {
        this.list.clear();
        this.list.add("list.");
        this.list.add("list");
        assertTrue("Should return true",
        this.trie.addNodes(list));
    }

    @Test
    public void testNodeSearchReturnsEqualSizeList() {
        Functions func = new Functions();
        try {
            String path = System.getProperty("user.dir")
            + "/data.txt";
            func.processData(path, this.trie, chainLength);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertTrue("List size should remain the same",
        this.list.size() == this.trie.nodeSearch(this.list).size());
    }

    @Test
    public void testCheckPunctuationReturnsTrueWhenDotPresent() {
        assertTrue("Should return True",
        this.trie.checkPunctuation("str."));
    }

    @Test
    public void testCheckPunctuationReturnsTrueWhenXmarkPresent() {
        assertTrue("Should return True",
        this.trie.checkPunctuation("str!"));
    }

    @Test
    public void testCheckPunctuationReturnsTrueWhenQuestionmarkPresent() {
        assertTrue("Should return True",
        this.trie.checkPunctuation("str?"));
    }

    @Test
    public void testCheckPunctuationReturnsFalseWhenNoDotPresent() {
        assertFalse("Should return False",
        this.trie.checkPunctuation("str"));
    }
}
