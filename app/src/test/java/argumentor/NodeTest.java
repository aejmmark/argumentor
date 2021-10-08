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
        assertTrue("Getter should return 1", testNode.getTickets(testNode) == 1);
    }

    @Test public void testTicketSum() {
        Node testNode = new Node("test");
        assertTrue("Getter should return 0", testNode.getTicketSum() == 0);
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

    @Test public void testAddNodeIncreasesMapSize() {
        Node testNode = new Node("test1");
        int amount = testNode.getEdgeMap().size();
        testNode.addNode("test2");
        assertTrue("Maps size should increase", testNode.getEdgeMap().size() > amount);
    }

    @Test public void testAddNodeWontAddDuplicate() {
        Node testNode = new Node("test1");
        testNode.addNode("test2");
        int amount = testNode.getEdgeMap().size();
        testNode.addNode("test2");
        assertTrue("Maps size should not increase", testNode.getEdgeMap().size() == amount);
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
        assertTrue("Should return true", testNode.checkEnd(0, 5));
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
        assertTrue("Should return true", testNode.checkEnd(0, 20));
    }

    @Test public void testCheckEndFalseWhenCountBelowMax() {
        Node testNode = new Node("test");
        Node testNode1 = new Node("test1");
        testNode.addEdge(testNode1);
        assertFalse("Should return false", testNode.checkEnd(10, 1));
    }

    @Test public void testCheckEndTrueWhenCountAboveMax() {
        Node testNode = new Node("test");
        Node testNode1 = new Node("test1");
        testNode.addEdge(testNode1);
        assertTrue("Should return true", testNode.checkEnd(10, 11));
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

    @Test public void testNodeNotEqualToOtherObj() {
        Node testNode = new Node("test");
        Integer one = 1;
        assertFalse("Should not be equal", testNode.equals(one));
    }

    @Test public void testNodeNotEqualIfOtherIsNull() {
        Node testNode1 = new Node(null);
        Node testNode2 = new Node("test");
        assertFalse("Should not be equal", testNode1.equals(testNode2));
    }
}
