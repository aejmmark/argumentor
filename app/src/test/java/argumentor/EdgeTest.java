package argumentor;

import org.junit.Test;
import static org.junit.Assert.*;

public class EdgeTest {
    @Test public void testTicketGetter() {
        Node testNode = new Node("test");
        Edge testEdge = new Edge(testNode);
        assertTrue("Getter should return ticket amount", testEdge.getTickets() == 1);
    }

    @Test public void testNodeGetter() {
        Node testNode = new Node("test");
        Edge testEdge = new Edge(testNode);
        assertNotNull("Getter should return Node", testEdge.getNode());
    }

    @Test public void testAddTicketIncreasesValue() {
        Node testNode = new Node("test");
        Edge testEdge = new Edge(testNode);
        int tickets = testEdge.getTickets();
        testEdge.addTicket();
        assertTrue("Ticket amount should increase", testEdge.getTickets() > tickets);
    }

    @Test public void testEdgesWithSameTextEqual() {
        Node testNode = new Node("test");
        Edge testEdge = new Edge(testNode);
        Edge testEdgeTwo = new Edge(testNode);
        assertTrue("Edges with the same text should be equal", testEdge.equals(testEdgeTwo));
    }

    @Test public void testEdgeNotEqualToNull() {
        Node testNode = new Node("test");
        Edge testEdge = new Edge(testNode);
        assertFalse("Edges should not be equal to null", testEdge.equals(null));
    }
}
