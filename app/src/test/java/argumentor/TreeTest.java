package argumentor;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TreeTest {
    private Tree tree;

    @Before public void setUp() {
    	tree = new Tree();
    }

    @Test public void testRootGetter() {
        assertNotNull("Should return root Node", tree.getRoot());
    }

    @Test public void testMapGetter() {
        assertNotNull("Should return HashMap", tree.getAllNodes());    
    }

    @Test public void testAddNodeReturnsNode() {
        Node testNode = new Node("test");
        assertTrue("Should return testNode", tree.addNode("test").equals(testNode));
    }

    @Test public void testAddNodeIncreasesMapSize() {
        int size = tree.getAllNodes().size();
        tree.addNode("str");
        assertTrue("Size should increase", size < tree.getAllNodes().size());
    }

    @Test public void testAddEdgeIncreasesMapSize() {
        Node testPrev = new Node("prev");
        Node testCurr = new Node("curr");
        int size = testPrev.getEdgeMap().size();
        tree.addEdge(testPrev, testCurr);
        assertTrue("Size should increase", size < testPrev.getEdgeMap().size());
    }

    @Test public void testAddEdgeSameEdgeIncreasesTicketValue() {
        Node testPrev = new Node("prev");
        Node testCurr = new Node("curr");
        tree.addEdge(testPrev, testCurr);
        int tickets = testPrev.getTickets(testCurr);
        tree.addEdge(testPrev, testCurr);
        assertTrue("Tickets value should increase", tickets < testPrev.getTickets(testCurr));
    }

    @Test public void testCheckEndReturnsRoot() {
        Node testNode = new Node("test");
        assertTrue("Should return root",tree.checkEnd("str.", testNode).equals(tree.getRoot()));
    }

    @Test public void testCheckEndReturnsCurrentNode() {
        Node testNode = new Node("test");
        assertTrue("Should return testNode",tree.checkEnd("str", testNode).equals(testNode));
        
    }
}