package argumentor;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class TrieTest {
    private Tree tree;

    @Before
    public void setUp() {
        tree = new Tree();
    }

    @Test
    public void testRootGetter() {
        assertNotNull("Should return root Node",
        tree.getRoot());
    }

    @Test
    public void testMapGetter() {
        assertNotNull("Should return HashMap",
        tree.getAllNodes());
    }

    @Test
    public void testAddNodeAddsEdgeToNode() {
        Node testNode = new Node("test");
        int size = testNode.getEdgeMap().size();
        tree.addNode("str", testNode, false);
        assertTrue("Hashmap size should increase",
        size < testNode.getEdgeMap().size());
    }

    @Test
    public void testAddNodeAddsEdgeToNodeAlt() {
        Node testNode = new Node("test");
        int size = testNode.getEdgeMap().size();
        tree.addNode("str", testNode, true);
        assertTrue("Hashmap size should increase",
        size < testNode.getEdgeMap().size());
    }

    @Test
    public void testAddNodeIncrementsTickets() {
        Node testNode = new Node("test");
        tree.addNode("str", testNode, false);
        int tickets = testNode.getTicketSum();
        tree.addNode("str", testNode, false);
        assertTrue("Sum of tickets should increase",
        tickets < testNode.getTicketSum());
    }

    @Test
    public void testAddNodeIncrementsTicketsAlt() {
        Node testNode = new Node("test");
        tree.addNode("str", testNode, true);
        int tickets = testNode.getTicketSum();
        tree.addNode("str", testNode, true);
        assertTrue("Sum of tickets should increase",
        tickets < testNode.getTicketSum());
    }

    @Test
    public void testAddNodeReturnsNode() {
        assertNotNull("Should return Node",
        tree.addNode("str", tree.getRoot(), false));
    }

    @Test
    public void testAddNodeReturnsNodeAlt() {
        assertNotNull("Should return Node",
        tree.addNode("str", tree.getRoot(), true));
    }

    @Test
    public void testAddNodeIncreasesAllNodes() {
        int size = tree.getAllNodes().size();
        tree.addNode("str", tree.getRoot(), false);
        assertTrue("Hashmap size should increase",
        size < tree.getAllNodes().size());
    }

    @Test
    public void testAddNodeAltDoesNotIncreaseAllNodes() {
        int size = tree.getAllNodes().size();
        tree.addNode("str", tree.getRoot(), true);
        assertTrue("Hashmap size not should increase",
        size == tree.getAllNodes().size());
    }

    @Test
    public void testAddToAllNodesReturnsNode() {
        Node testNode = new Node("test");
        assertTrue("Should return testNode",
        tree.addToAllNodes("test").equals(testNode));
    }

    @Test
    public void testAddToAllNodesIncreasesMapSize() {
        int size = tree.getAllNodes().size();
        tree.addToAllNodes("str");
        assertTrue("Size should increase",
        size < tree.getAllNodes().size());
    }

    @Test
    public void testCheckEndReturnsRoot() {
        Node testNode = new Node("test");
        assertTrue("Should return root",
        tree.checkSentenceEnd("str.", testNode).equals(tree.getRoot()));
    }

    @Test
    public void testCheckEndReturnsCurrentNode() {
        Node testNode = new Node("test");
        assertTrue("Should return testNode",
        tree.checkSentenceEnd("str", testNode).equals(testNode));
    }
}
