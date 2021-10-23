package argumentor;

import java.util.Random;
import java.util.HashMap;

/**
* Trie Nodes.
*/
public class Node {
    /**
    * Contains all the edges that the Node is connected to.
    */
    private HashMap<String, Node> edges;
    /**
    * Sum of freq values from connected Nodes.
    */
    private int ticketSum;
    /**
    * Frequency of the Node.
    * Determined by how often the string assosiated with
    * this Node appears in a similar context in the source
    * data.
    */
    private int freq;

    /**
    * Sets up the default values.
    */
    public Node() {
        this.edges = new HashMap<String, Node>();
        this.ticketSum = 0;
        this.freq = 1;
    }

    /**
    * Randomizes the winning ticket and then reduces it
    * one by one with all the frequencies in the edges
    * map until it reaches zero. The current edge at
    * that point wins the lottery.
    */
    public String lottery() {
        Random rng = new Random();
        int winningTicket = (rng.nextInt(ticketSum));
        String winner = null;
        for (String str : this.edges.keySet()) {
            winningTicket -= this.edges.get(str).getFreq();
            if (winningTicket <= 0) {
                winner = str;
                break;
            }
        }
        return winner;
    }

    /**
    * Adds an edge to the edges hashmap.
    * Increments the ticketSum variable.
    * @param str String used as the key.
    * @param node Node used as the value.
    */
    public void addEdge(final String str, final Node node) {
        this.edges.put(str, node);
        this.ticketSum++;
    }

    /**
    * Returns the Node associated wit the
    * given String key.
    * @param str String used as the key.
    * @return Node connected to this one.
    */
    public Node getNode(final String str) {
        return this.edges.get(str);
    }

    /**
    * Returns the hashma containing all edges.
    * @return edges hashmap.
    */
    public HashMap<String, Node> getEdgeMap() {
        return this.edges;
    }

    /**
    * Returns the total sum of tickets.
    * @return int representing the above.
    */
    public int getTicketSum() {
        return this.ticketSum;
    }

    /**
    * Returns the frequency of the Node.
    * @return int representing the above.
    */
    public int getFreq() {
        return this.freq;
    }

    /**
    * Increases the freq variable by one.
    */
    public void incrementFreq() {
        this.freq++;
    }

    /**
    * Increases the ticketSum variable by one.
    */
    public void incrementTicketSum() {
        this.ticketSum++;
    }
}
