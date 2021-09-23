package argumentor;

import java.util.HashMap;
import java.util.Random;

/**
* Nodes of the tree.
* Contains a string used to build sentences
* and a map of connected edges.
*/
public class Node {
    /**
    * String representing the word contained in the Node.
    */
    private String word;
    /**
    * Map containing Node - Edge pairings.
    * Keys are the Nodes that Edges are connected to.
    * Edges are the edges connected to this Node.
    */
    private HashMap<Node, Edge> edges;

    /**
    * Constructor that sets str to word and creates a new HashMap.
    * @param str String set to word.
    */
    public Node(final String str) {
        this.word = str;
        this.edges = new HashMap<Node, Edge>();
    }

    /**
    * Determines the next Node by first calculating
    * the sum of Edges with edgeSum()
    * and then choosing a random number between that and 0.
    * The result is then given to checkWinner(),
    * that uses it to calculate the winning Node
    * @return Node that won the lottery provided by checkWinner().
    */
    public Node lottery() {
        Random rng = new Random();
        int sum = this.edgeSum();
        int winner = (rng.nextInt(sum));
        return this.checkWinner(winner);
    }

    /**
     * Calculates the total sum of tickets in the edges map.
     * @return int representing the sum of tickets.
    */
    public int edgeSum() {
        int sum = 0;
        for (Edge edge : this.edges.values()) {
            sum += edge.getTickets();
        }
        return sum;
    }

    /**
    * Goes through the map of Edges
    * and calculates wich one contains the winning ticket.
    * @param count int representing the how far the loop will count.
    * @return Node contained in the winning edge. null if something goes wrong.
    */
    public Node checkWinner(final int count) {
        Node winner = null;
        int winningTicket = count;
        for (Edge edge : this.edges.values()) {
            winningTicket -= edge.getTickets();
            if (winningTicket <= 0) {
                winner = edge.getNode();
                break;
            }
        }
        return winner;
    }

    /**
    * Checks whether or not the sentence should end
    * based on the amount of words it currently has.
    * Returns false if the Node has no Edges connected to it.
    * @param wordCount int representing the amount of words
    currently in the sentence.
    * @return true if the sentence will end, false if not
    */
    public boolean checkEnd(final int wordCount) {
        if (this.edges.isEmpty()) {
            return true;
        }
        Random rng = new Random();
        int count = wordCount;
        final int maxCount = 12;
        final int minCount = 3;
        count -= rng.nextInt(maxCount) + minCount;
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
    * Adds and Edge to the edges map.
    * @param node Node to be added as a key.
    * @param edge Edge to be added as a value.
    */
    public void addEdge(final Node node, final Edge edge) {
        this.edges.put(node, edge);
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
    * @param node Node serving as the key to the Map.
    * @return Edge that is connected to the given Node.
    */
    public Edge getEdge(final Node node) {
        return this.edges.get(node);
    }

    /**
    * Returns the edges map.
    * @return HashMap containing all the Node - Edge pairings.
    */
    public HashMap<Node, Edge> getEdgeMap() {
        return this.edges;
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
