package argumentor;

import org.junit.Test;
import static org.junit.Assert.*;

public class EdgeTest {
    @Test public void testTicketGetter() {
        Word testWord = new Word("test");
        Edge testEdge = new Edge(testWord);
        assertTrue("Getter should return ticket amount", testEdge.getTickets() == 1);
    }

    @Test public void testWordGetter() {
        Word testWord = new Word("test");
        Edge testEdge = new Edge(testWord);
        assertNotNull("Getter should return Word", testEdge.getWord());
    }

    @Test public void testAddTicketIncreasesValue() {
        Word testWord = new Word("test");
        Edge testEdge = new Edge(testWord);
        int tickets = testEdge.getTickets();
        testEdge.addTicket();
        assertTrue("Ticket amount should increase", testEdge.getTickets() > tickets);
    }

    @Test public void testEdgesWithSameTextEqual() {
        Word testWord = new Word("test");
        Edge testEdge = new Edge(testWord);
        Edge testEdgeTwo = new Edge(testWord);
        assertTrue("Edges with the same text should be equal", testEdge.equals(testEdgeTwo));
    }
}
