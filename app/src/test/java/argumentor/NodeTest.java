package argumentor;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class NodeTest {
    private Node testNode;
    private Node testNodeTwo;

    @Before
    public void setUp() {
        testNode = new Node();
        testNodeTwo = new Node();
    }

    @Test
    public void testNodeGetter() {
        testNode.addEdge("str", testNodeTwo);
        assertNotNull("Getter should return testNodeTwo",
        testNode.getNode("str"));
    }

    @Test
    public void testTicketSumGetter() {
        assertTrue("Getter should return 0",
        testNode.getTicketSum() == 0);
    }

    @Test
    public void testFreqGetter() {
        assertTrue("Getter should return 1",
        testNode.getFreq() == 1);
    }

    @Test
    public void testEdgeMapGetter() {
        assertNotNull("Getter should return hashmap",
        testNode.getEdgeMap());
    }

    @Test
    public void testAddEdgeIncreasesValue() {
        int edges = testNode.getEdgeMap().size();
        testNode.addEdge("str", testNodeTwo);
        assertTrue("List size should increase",
        testNode.getEdgeMap().size() > edges);
    }

    @Test
    public void testIncrementTicketSumIncreasesValue() {
        int amount = testNode.getTicketSum();
        testNode.incrementTicketSum();
        assertTrue("TicketSum should increase",
        testNode.getTicketSum() > amount);
    }

    @Test
    public void testIncrementFreqIncreasesValue() {
        int amount = testNode.getFreq();
        testNode.incrementFreq();
        assertTrue("Freq should increase",
        testNode.getFreq() > amount);
    }

    @Test
    public void testLotteryReturnsWinningString() {
        Node testNodeThree = new Node();
        testNode.addEdge("two", testNodeTwo);
        testNode.addEdge("three", testNodeThree);
        assertTrue("Should return two or three",
        testNode.lottery().equals("two")
        || testNode.lottery().equals("three"));
    }

    @Test
    public void testLotteryReturnsString() {
        testNode.addEdge("str", testNodeTwo);
        assertTrue("Should return str",
        testNode.lottery().equals("str"));
    }
}
