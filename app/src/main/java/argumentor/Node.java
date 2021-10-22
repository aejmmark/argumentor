package argumentor;

import java.util.Random;
import java.util.HashMap;

public class Node {
    private HashMap<String, Node> edges;
    private int ticketSum;
    private boolean punc;
    private int freq;

    public Node() {
        this.edges = new HashMap<String, Node>();
        this.ticketSum = 0;
        this.punc = false;
        this.freq = 1;
    }

    public String lottery() {
        System.out.println("SUM " + ticketSum);
        Random rng = new Random();
        int winner = (rng.nextInt(ticketSum));
        return this.checkWinner(winner);
    }

    public String checkWinner(final int count) {
        String winner = null;
        int winningTicket = count;
        for (String str : this.edges.keySet()) {
            winningTicket -= this.edges.get(str).getFreq(); //could try to optimize this
            if (winningTicket <= 0) {
                winner = str;
                break;
            }
        }
        return winner;
    }

    public void addEdge(final String str, final Node node) {
        this.edges.put(str, node);
        this.ticketSum++;
    }

    public Node getNode(final String str) {
        return this.edges.get(str);
    }

    public HashMap<String, Node> getEdgeMap() {
        return this.edges;
    }

    public int getTicketSum() {
        return this.ticketSum;
    }

    public void setPunc() {
        this.punc = true;
    }

    public boolean isPunc() {
        return this.punc;
    }

    public int getFreq() {
        return this.freq;
    }

    public void incrementFreq() {
        this.freq++;;
    }

    public void incrementTicketSum() {
        this.ticketSum++;;
    }
}
