package argumentor;

import java.util.HashMap;
import java.util.Random;

/**
* Nodes of the tree.
* Contains a string used for build sentences
* and a hashmap of connected edges.
*/
public class Node {
    /**
    * String representing the word contained in the Node.
    */
    private String word;
    /**
    * Map containing Node-int pairings referred to as edges.
    * Keys are the Nodes that edges are connected to.
    * ints represent tickets or the "weight" of the edge.
    */
    private HashMap<Node, Integer> edges;
    /**
    * int representing the sum of tickets in the edge map.
    */
    private int ticketSum;

    /**
    * Constructor that sets param str to word, 0 to ticketSum
    and creates a new hashmap.
    * @param str String set to word.
    */
    public Node(final String str) {
        this.word = str;
        this.edges = new HashMap<Node, Integer>();
        this.ticketSum = 0;
    }

    /**
    * Determines the next Node by choosing a random
    * number between 0 and the value of ticketSum.
    * The result is then given to checkWinner(),
    * that uses it to calculate the winning Node
    * @return Node that won the lottery.
    */
    public Node lottery() {
        Random rng = new Random();
        int winner = (rng.nextInt(ticketSum));
        return this.checkWinner(winner);
    }

    /**
    * Goes through the map of Node-int pairs
    * and calculates wich one contains the winning ticket.
    * @param count int representing the how far the loop will count.
    * @return Node contained in the winning edge. null if something goes wrong.
    */
    public Node checkWinner(final int count) {
        Node winner = null;
        int winningTicket = count;
        for (Node curr : this.edges.keySet()) {
            winningTicket -= edges.get(curr);
            if (winningTicket <= 0) {
                winner = curr;
                break;
            }
        }
        return winner;
    }

    /**
    * Adds an edge to the edges map.
    * @param node Node to be added as a key.
    */
    public void addEdge(final Node node) {
        this.edges.put(node, 1);
        this.ticketSum++;
    }

    /**
    * Increments the given edges ticket score and
    * also the sum of tickets.
    * @param node key to the hashmap.
    */
    public void addTicket(final Node node) {
        this.edges.put(node, this.edges.get(node) + 1);
        this.ticketSum++;
    }

    /**
    * Returns the word contained within the Node.
    * @return String representing the word.
    */
    public String getWord() {
        return this.word;
    }

    /**
    * Returns a value from the edges map based on the given key Node.
    * @param node Node serving as the key to the hashmap.
    * @return int representing the amount of tickets.
    */
    public Integer getTickets(final Node node) {
        return this.edges.get(node);
    }

    /**
    * Returns the edges map.
    * @return Hashmap containing all the Node-int pairings.
    */
    public HashMap<Node, Integer> getEdgeMap() {
        return this.edges;
    }

    /**
    * Returns the value of the ticketSum variable.
    * @return int representing the total of tickets in the edges map.
    */
    public int getTicketSum() {
        return this.ticketSum;
    }

    /**
     * hashCode method.
    */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((word == null) ? 0 : word.hashCode());
        return result;
    }

    /**
    * Determines equality based on the value of the word variable.
    * @return true if Nodes are equal, false if not or object is null.
    */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Node other = (Node) obj;
        if (word == null) {
            if (other.word != null) {
                return false;
            }
        } else if (!word.equals(other.word)) {
            return false;
        }
        return true;
    }
}
