package argumentor;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

public class NodeTest {
    private Node testNode;

    @Before
    public void setUp() {
        testNode = new Node("test");
    }

    @Test
    public void testWordGetter() {
        assertTrue("Getter should return the word test",
        testNode.getWord().equals("test"));
    }

    @Test
    public void testTicketGetter() {
        testNode.addEdge(testNode);
        assertTrue("Getter should return 1",
        testNode.getTickets(testNode) == 1);
    }

    @Test
    public void testTicketSum() {
        assertTrue("Getter should return 0",
        testNode.getTicketSum() == 0);
    }

    @Test
    public void testEdgeMapGetter() {
        assertNotNull("Getter should return hashmap",
        testNode.getEdgeMap());
    }

    @Test
    public void testAddEdgeIncreasesValue() {
        int edges = testNode.getEdgeMap().size();
        testNode.addEdge(testNode);
        assertTrue("List size should increase",
        testNode.getEdgeMap().size() > edges);
    }

    @Test
    public void testAddTicketIncreasesValue() {
        testNode.addEdge(testNode);
        int amount = testNode.getTickets(testNode);
        testNode.addTicket(testNode);
        assertTrue("Ticket amount should increase",
        testNode.getTickets(testNode) > amount);
    }

    @Test
    public void testCheckWinnerReturnsCorrectWinner() {
        Node testNode2 = new Node("test2");
        Node testNode3 = new Node("test3");
        testNode.addEdge(testNode2);
        testNode.addEdge(testNode3);
        assertTrue("Should return testNode2 or testNode3",
        testNode.checkWinner(0).equals(testNode2)
        || testNode.checkWinner(0).equals(testNode3));
    }

    @Test
    public void testLotteryReturnsNode() {
        Node testNode2 = new Node("test2");
        testNode.addEdge(testNode2);
        assertTrue("Should return testNode2",
        testNode.lottery().equals(testNode2));
    }

    @Test
    public void testNodesWithSameWordEqual() {
        Node testNode2 = new Node("test");
        assertTrue("Wors with the same word should be equal",
        testNode.equals(testNode2));
    }

    @Test
    public void testNodeNotEqualToNull() {
        assertFalse("Nodes should not be equal to null",
        testNode.equals(null));
    }

    @Test
    public void testNodeNotEqualToOtherObj() {
        Integer one = 1;
        assertFalse("Should not be equal",
        testNode.equals(one));
    }

    @Test
    public void testNodeNotEqualIfOtherIsNull() {
        testNode = new Node(null);
        Node testNode2 = new Node("test");
        assertFalse("Should not be equal",
        testNode.equals(testNode2));
    }
}
