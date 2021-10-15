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

    @Test public void testAddToAllNodesReturnsNode() {
        Node testNode = new Node("test");
        assertTrue("Should return testNode", tree.addToAllNodes("test").equals(testNode));
    }

    @Test public void testAddToAllNodesIncreasesMapSize() {
        int size = tree.getAllNodes().size();
        tree.addToAllNodes("str");
        assertTrue("Size should increase", size < tree.getAllNodes().size());
    }

    @Test public void testCheckEndReturnsRoot() {
        Node testNode = new Node("test");
        assertTrue("Should return root",tree.checkSentenceEnd("str.", testNode).equals(tree.getRoot()));
    }

    @Test public void testCheckEndReturnsCurrentNode() {
        Node testNode = new Node("test");
        assertTrue("Should return testNode",tree.checkSentenceEnd("str", testNode).equals(testNode));
        
    }
}