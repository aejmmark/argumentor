package argumentor;

/**
* Edge connected to a Node. Weighted by the amount of tickets.
*/
public class Edge {
    /**
    * Node that the Edge is connected to.
    */
    private Node node;
    /**
    * int representing the amount of tickets the edge has.
    */
    private int tickets;

    /**
    * Constructor that sets newNode to node and tickets to 1.
    * @param newNode Node connected to the Edge.
    */
    public Edge(final Node newNode) {
        this.node = newNode;
        this.tickets = 1;
    }

    /**
    * Adds one to the tickets variable.
    */
    public void addTicket() {
        this.tickets += 1;
    }

    /**
    * Returns the Node.
    * @return Node that the Edge is connected to.
    */
    public Node getNode() {
       return this.node;
    }

    /**
    * Returns the amount of lottery tickets the Edge has.
    * @return int representing amount of tickets.
    */
    public int getTickets() {
        return this.tickets;
    }

    /**
    * hashCode method.
    */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((node == null) ? 0 : node.hashCode());
        return result;
    }

    /**
    * Determines equality based on the value of the node variable.
    * @return true if Edges are equal, false if not or object is null.
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
        Edge other = (Edge) obj;
        if (node == null) {
            if (other.node != null) {
                return false;
            }
        } else if (!node.equals(other.node)) {
            return false;
        }
        return true;
    }
}
