package argumentor;

import java.util.HashMap;
import java.util.Random;

/** Nodes of the tree. Contains the word used to build sentences and a list of connected edges.
*/
public class Node {
    //public int endTickets;
    public String word;
    public HashMap<Node,Edge> edges;

    public Node(String s) {
        //this.endTickets = 1;
        this.word = s;
        this.edges = new HashMap<Node,Edge>();
    }

    /** Determines the next Node by first calculating the sum of Edges with edgeSum()
     * and then choosing a random number between that and 0. The result is then given to checkWinner(),
     * that uses it to calculate the winning Node
    * @return Node that won the lottery provided by checkWinner().
    */
    public Node lottery() {
        Random rng = new Random();
        int sum = this.edgeSum();
        int winner = (rng.nextInt(sum));
        return this.checkWinner(winner);
    }

    /** Calculates the total sum of tickets in the edges map.
     * @return int representing the sum of tickets.
    */
    public int edgeSum() {
        int sum = 0;
        for (Edge edge : this.edges.values()) {
            sum += edge.getTickets();
        }
        return sum;
    }

    /** Goes through the map of Edges and calculates wich one contains the winning ticket.
    * @param winningTicket int representing the winning ticket.
    * @return Node contained in the winning edge. null if something goes wrong.
    */
    public Node checkWinner(int winningTicket) {
        Node winner = null;
        for (Edge edge : this.edges.values()) {
            winningTicket -= edge.getTickets();
            if (winningTicket <= 0) {
                winner = edge.getNode();
                break;
            }
        }
        return winner;
    }

    /** Checks whether or not the sentence should end based on the amount of words it currently has.
     * Returns false if the Node has no Edges connected to it.
    * @param count int representing the amount of words currently in the sentence.
    * @return true if the sentence will end, false if not
    */
    public boolean checkEnd(int count) {
        if (this.edges.isEmpty()) {
            return true;
        }
        Random rng = new Random();
        count -= rng.nextInt(12) + 3;
        if (count > 0) return true;
        return false;
    }

    /** Adds and Edge to the edges map.
    * @param node Node to be added as a key.
    * @param edge Edge to be added as a value.
    */
    public void addEdge(Node node, Edge edge) {
        this.edges.put(node, edge);
    }

    /** Adds one to the endTickets variable.
    */
    //public void addEndTicket() {
    //    this.endTickets++;
    //}

    /** Returns the amount of ending lottery tickets the Node has. These determine the chance of ending the sentence.
    * @return int representing the amount of ending tickets.
    */
    //public int getEndTickets() {
    //    return this.endTickets;
    //}

    /** Returns the word contained within the Node.
    * @return String representing the word.
    */
    public String getWord() {
        return this.word;
    }

    /** Returns a value from the edges map based on the given key Node.
    * @return Edge that is connected to the given Node.
    */
    public Edge getEdge(Node node) {
        return this.edges.get(node);
    }

    /** Determines that two Nodes are equal if they contain the same String in the word variable.
    */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            return this.word.equals(((Node) obj).getWord());
        }
    }
}