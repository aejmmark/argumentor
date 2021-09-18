package argumentor;

import org.junit.Test;
import static org.junit.Assert.*;

public class WordTest {
    @Test public void testEndTicketGetter() {
        Word testWord = new Word("test");
        assertTrue("Getter should return ticket amount", testWord.getEndTickets() == 1);
    }

    @Test public void testTextGetter() {
        Word testWord = new Word("test");
        assertTrue("Getter should return text", testWord.getText().equals("test"));
    }

    @Test public void testEdgesGetter() {
        Word testWord = new Word("test");
        assertNotNull("Getter should return list", testWord.getEdges());
    }

    @Test public void testAddEndTicketIncreasesValue() {
        Word testWord = new Word("test");
        int tickets = testWord.getEndTickets();
        testWord.addEndTicket();
        assertTrue("Ticket amount should increase", testWord.getEndTickets() > tickets);
    }

    @Test public void testAddEdgeIncreasesValue() {
        Word testWord = new Word("test");
        Edge edgeTest = new Edge(testWord);
        int edges = testWord.getEdges().size();
        testWord.addEdge(edgeTest);
        assertTrue("List size should increase", testWord.getEdges().size() > edges);
    }

    @Test public void testEdgeSumReturnsValue() {
        Word testWord = new Word("test");
        Edge edgeTest1 = new Edge(testWord);
        Edge edgeTest2 = new Edge(testWord);
        testWord.addEdge(edgeTest1);
        testWord.addEdge(edgeTest2);
        assertTrue("Sum should be 2", 2 == testWord.edgeSum());
    }

    @Test public void testCheckWinnnerReturnsCorrectWinnerOne() {
        Word testWord = new Word("test");
        Edge edgeTest1 = new Edge(testWord);
        edgeTest1.addTicket();
        Edge edgeTest2 = new Edge(testWord);
        testWord.addEdge(edgeTest1);
        testWord.addEdge(edgeTest2);
        assertTrue("Should return 0", testWord.checkWinner(1) == 0);
    }

    @Test public void testCheckWinnnerReturnsCorrectWinnerTwo() {
        Word testWord = new Word("test");
        Edge edgeTest1 = new Edge(testWord);
        edgeTest1.addTicket();
        Edge edgeTest2 = new Edge(testWord);
        testWord.addEdge(edgeTest1);
        testWord.addEdge(edgeTest2);
        assertTrue("Should return 1", testWord.checkWinner(3) == 1);
    }

    @Test public void testLotteryReturnsValue() {
        Word testWord = new Word("test");
        Edge edgeTest1 = new Edge(testWord);
        Edge edgeTest2 = new Edge(testWord);
        testWord.addEdge(edgeTest1);
        testWord.addEdge(edgeTest2);
        assertTrue("Should return 1 or 0", testWord.lottery() == 1 || testWord.lottery() == 0);
    }

    @Test public void testLotteryReturnsRandomValue() {
        Word testWord = new Word("test");
        Word testWordTwo = new Word("testtwo");
        Word testWordThree = new Word("testtwothree");
        Edge edgeTest1 = new Edge(testWord);
        Edge edgeTest2 = new Edge(testWordTwo);
        Edge edgeTest3 = new Edge(testWordThree);
        testWord.addEdge(edgeTest1);
        testWord.addEdge(edgeTest2);
        testWord.addEdge(edgeTest3);
        int testVal = 0;
        for (int i = 0; i < 10; i++) {
            if (testWord.lottery() == 1) {
                testVal = 1;
            }
        }
        assertTrue("Should return 1", testVal == 1);
    }

    @Test public void testWordsWithSameTextEqual() {
        Word testWord = new Word("test");
        Word testWordTwo = new Word("test");
        assertTrue("Wors with the same text should be equal", testWord.equals(testWordTwo));
    }
}
