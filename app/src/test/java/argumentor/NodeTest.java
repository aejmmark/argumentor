package argumentor;

import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {
    @Test public void testWordGetter() {
        Node testNode = new Node("test");
        assertTrue("Getter should return the word test", testNode.getWord().equals("test"));
    }

    @Test public void testTicketGetter() {
        Node testNode = new Node("test");
        testNode.addEdge(testNode);
        assertNotNull("Getter should return 1", testNode.getTickets(testNode));
    }

    @Test public void testEdgeMapGetter() {
        Node testNode = new Node("test");
        assertNotNull("Getter should return hashmap", testNode.getEdgeMap());
    }

    @Test public void testAddEdgeIncreasesValue() {
        Node testNode = new Node("test");
        int edges = testNode.getEdgeMap().size();
        testNode.addEdge(testNode);
        assertTrue("List size should increase", testNode.getEdgeMap().size() > edges);
    }

    @Test public void testAddTicketIncreasesValue() {
        Node testNode = new Node("test");
        testNode.addEdge(testNode);
        int amount = testNode.getTickets(testNode);
        testNode.addTicket(testNode);
        assertTrue("Ticket amount should increase", testNode.getTickets(testNode) > amount);
    }

    @Test public void testCheckWinnerReturnsCorrectWinner() {
        Node testNode = new Node("test");
        Node testNode1 = new Node("test1");
        Node testNode2 = new Node("test2");
        testNode.addEdge(testNode1);
        testNode.addEdge(testNode2);
        assertTrue("Should return testNode1 or testNode2", testNode.checkWinner(0).equals(testNode1) || testNode.checkWinner(0).equals(testNode2));
    }

    @Test public void testLotteryReturnsNode() {
        Node testNode = new Node("test");
        Node testNode1 = new Node("test1");
        testNode.addEdge(testNode1);
        assertTrue("Should return testNode1", testNode.lottery().equals(testNode1));
    }

    @Test public void testCheckEndTrueWhenMapEmpty() {
        Node testNode = new Node("test");
        assertTrue("Should return false", testNode.checkEnd(0, 5));
    }

    @Test public void testCheckEndFalseWhenCountLow() {
        Node testNode = new Node("test");
        Node testNode1 = new Node("test1");
        testNode.addEdge(testNode1);
        assertFalse("Should return false", testNode.checkEnd(0, 1));
    }

    @Test public void testCheckEndTrueWhenCountHigh() {
        Node testNode = new Node("test");
        Node testNode1 = new Node("test1");
        testNode.addEdge(testNode1);
        assertTrue("Should return false", testNode.checkEnd(0, 20));
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
