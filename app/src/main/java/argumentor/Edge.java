package argumentor;

/** Edge connected to a Word variable functoning as a node. Weighted by the amount of tickets.
*/
public class Edge {
    public Word word;
    public int tickets;

    public Edge(Word word) {
        this.word = word;
        this.tickets = 1;
    }

    /** Adds one to the tickets variable.
    */
    public void addTicket() {
        this.tickets += 1;
    }

    /** Returns the word variable representing the connected node.
    * @return Word that the edge is connected to.
    */
    public Word getWord() {
       return this.word; 
    }

    /** Returns the amount of lottery tickets the edge has.
    * @return int representing amount of tickets.
    */
    public int getTickets() {
        return this.tickets;
    }

    /** Determines that two edges are equal if they are connected to the same word.
    */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            return this.getWord().equals(((Edge)obj).getWord());
        }
    }
}
