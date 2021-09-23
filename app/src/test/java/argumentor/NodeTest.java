package argumentor;

import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {
    @Test public void testWordGetter() {
        Node testNode = new Node("test");
        assertTrue("Getter should return the word test", testNode.getWord().equals("test"));
    }

    @Test public void testEdgeGetter() {
        Node testNode = new Node("test");
        Edge edgeTest = new Edge(testNode);
        testNode.addEdge(testNode, edgeTest);
        assertNotNull("Getter should return Edge", testNode.getEdge(testNode));
    }

    @Test public void testEdgeMapGetter() {
        Node testNode = new Node("test");
        assertNotNull("Getter should return hashmap", testNode.getEdgeMap());
    }

    @Test public void testAddEdgeIncreasesValue() {
        Node testNode = new Node("test");
        Edge edgeTest = new Edge(testNode);
        int edges = testNode.getEdgeMap().size();
        testNode.addEdge(testNode, edgeTest);
        assertTrue("List size should increase", testNode.getEdgeMap().size() > edges);
    }

    @Test public void testEdgeSumReturnsCorrectValue() {
        Node testNode = new Node("test");
        Node testNode1 = new Node("test1");
        Node testNode2 = new Node("test2");
        Edge edgeTest1 = new Edge(testNode1);
        Edge edgeTest2 = new Edge(testNode2);
        testNode.addEdge(testNode1,edgeTest1);
        testNode.addEdge(testNode2,edgeTest2);
        assertTrue("Sum should be 2", 2 == testNode.edgeSum());
    }

    @Test public void testCheckWinnerReturnsCorrectWinner() {
        Node testNode = new Node("test");
        Node testNode1 = new Node("test1");
        Node testNode2 = new Node("test2");
        Edge edgeTest1 = new Edge(testNode1);
        Edge edgeTest2 = new Edge(testNode2);
        testNode.addEdge(testNode1, edgeTest1);
        testNode.addEdge(testNode2, edgeTest2);
        assertTrue("Should return testNode1 or testNode2", testNode.checkWinner(0).equals(testNode1) || testNode.checkWinner(0).equals(testNode2));
    }

    @Test public void testLotteryReturnsNode() {
        Node testNode = new Node("test");
        Node testNode1 = new Node("test1");
        Edge edgeTest1 = new Edge(testNode1);
        testNode.addEdge(testNode1, edgeTest1);
        assertTrue("Should return testNode1", testNode.lottery().equals(testNode1));
    }

    @Test public void testCheckEndTrueWhenMapEmpty() {
        Node testNode = new Node("test");
        assertTrue("Should return false", testNode.checkEnd(5));
    }

    @Test public void testCheckEndFalseWhenCountLow() {
        Node testNode = new Node("test");
        Node testNode1 = new Node("test1");
        Edge edgeTest1 = new Edge(testNode1);
        testNode.addEdge(testNode1, edgeTest1);
        assertFalse("Should return false", testNode.checkEnd(1));
    }

    @Test public void testCheckEndTrueWhenCountHigh() {
        Node testNode = new Node("test");
        Node testNode1 = new Node("test1");
        Edge edgeTest1 = new Edge(testNode1);
        testNode.addEdge(testNode1, edgeTest1);
        assertTrue("Should return false", testNode.checkEnd(20));
    }

    @Test public void testNodesWithSameWordEqual() {
        Node testNode = new Node("test");
        Node testNodeTwo = new Node("test");
        assertTrue("Wors with the same word should be equal", testNode.equals(testNodeTwo));
    }

    @Test public void testNodeNotEqualToNull() {
        Node testNode = new Node("test");
        assertFalse("Nodes should not be equal to null", testNode.equals(null));
    }
}
