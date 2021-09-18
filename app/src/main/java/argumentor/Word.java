package argumentor;

import java.util.ArrayList;
import java.util.Random;

/** Serve as nodes connected by Edge variables. Contains the words used to build sentences and a list of connected edges.
    */
public class Word {
    public int endTickets;
    public String text;
    public ArrayList<Edge> edges;

    public Word(String s) {
        this.endTickets = 1;
        this.text = s;
        this.edges = new ArrayList<Edge>();
    }

    /** Counts sum of tickets with edgeSum(). Calculates winning tickets with rng and checks the winner with checkWinner().
    * @return int representing the index of the winning edge provided by checkWinner().
    */
    public int lottery() {
        Random rng = new Random();
        int sum = this.edgeSum();
        int winner = (rng.nextInt(sum));
        return this.checkWinner(winner);
    }

    /** Calculates the total sum of tickets in the edges list.
     * @return int representing the sum of tickets.
    */
    public int edgeSum() {
        int sum = 0;
        for (Edge e : this.edges) {
            sum += e.getTickets();
        }
        return sum;
    }

    /** Goes through the list of edges and calculates wich one holds the winning ticket.
    * @param winningTicket represents the winning ticket.
    * @return int representing the index of the winning edge. -1 if something goes wrong.
    */
    public int checkWinner(int winningTicket) {
        int i = 0;
        for (Edge e : this.edges) {
            winningTicket -= e.getTickets();
            if (winningTicket <= 0) {
                return i;
            }
            i++;
        }
        return -1; // SOMETIMES FAILS
    }

    /** Adds and edge to the edges list.
    * @param edge edge to be added.
    */
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    /** Adds one to the endTickets variable.
    */
    public void addEndTicket() {
        this.endTickets++;
    }

    /** Returns the amount of ending lottery tickets the word has. These determine the chance of ending the sentence.
    * @return int representing the amount of ending tickets.
    */
    public int getEndTickets() {
        return this.endTickets;
    }

    /** Returns the text of the word. The word itself if you may.
    * @return String representing the word.
    */
    public String getText() {
        return this.text;
    }

    /** Returns an ArrayList of edges connected to the word.
    * @return ArrayList edges
    */
    public ArrayList getEdges() {
        return this.edges;
    }

    /** Determines that two words are equal if they are have the same text.
    */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            return this.getText().equals(((Word) obj).getText());
        }
    }
}