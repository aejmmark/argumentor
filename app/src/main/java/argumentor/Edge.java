package argumentor;

/** Edge connected to a Node. Weighted by the amount of tickets.
*/
public class Edge {
    public Node node;
    public int tickets;

    public Edge(Node Node) {
        this.node = Node;
        this.tickets = 1;
    }

    /** Adds one to the tickets variable.
    */
    public void addTicket() {
        this.tickets += 1;
    }

    /** Returns the Node.
    * @return Node that the Edge is connected to.
    */
    public Node getNode() {
       return this.node; 
    }

    /** Returns the amount of lottery tickets the Edge has.
    * @return int representing amount of tickets.
    */
    public int getTickets() {
        return this.tickets;
    }

    /** Determines that two Edges are equal if they are connected to the same Node.
    */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            return this.node.equals(((Edge)obj).getNode());
        }
    }
}
