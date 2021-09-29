package argumentor;

import org.junit.Test;
import static org.junit.Assert.*;

public class TreeTest {
    @Test public void testRootGetter() {
        Tree tree = new Tree();
        assertNotNull("Should return root Node", tree.getRoot());
    }

    @Test public void testMapGetter() {
        Tree tree = new Tree();
        assertNotNull("Should return HashMap", tree.getAllNodes());    
    }

    @Test public void testAddNodeReturnsNode() {
        Tree tree = new Tree();
        Node testNode = new Node("test");
        assertTrue("Should return testNode", tree.addNode("test").equals(testNode));
    }

    @Test public void testAddNodeIncreasesMapSize() {
        Tree tree = new Tree();
        int size = tree.getAllNodes().size();
        tree.addNode("str");
        assertTrue("Size should increase", size < tree.getAllNodes().size());
    }

    @Test public void testAddEdgeIncreasesMapSize() {
        Tree tree = new Tree();
        Node testPrev = new Node("prev");
        Node testCurr = new Node("curr");
        int size = testPrev.getEdgeMap().size();
        tree.addEdge(testPrev, testCurr);
        assertTrue("Size should increase", size < testPrev.getEdgeMap().size());
    }

    @Test public void testAddEdgeSameEdgeIncreasesTicketValue() {
        Tree tree = new Tree();
        Node testPrev = new Node("prev");
        Node testCurr = new Node("curr");
        tree.addEdge(testPrev, testCurr);
        int tickets = testPrev.getTickets(testCurr);
        tree.addEdge(testPrev, testCurr);
        assertTrue("Tickets value should increase", tickets < testPrev.getTickets(testCurr));
    }

    @Test public void testCheckEndReturnsRoot() {
        Tree tree = new Tree();
        Node testNode = new Node("test");
        assertTrue("Should return root",tree.checkEnd("str.", testNode).equals(tree.getRoot()));
    }

    @Test public void testCheckEndReturnsCurrentNode() {
        Tree tree = new Tree();
        Node testNode = new Node("test");
        assertTrue("Should return testNode",tree.checkEnd("str", testNode).equals(testNode));
        
    }
}