package argumentor;

import java.util.ArrayList;

import argumentor.Edge;

public class Word {
    public int endTickets;
    public String text;
    public ArrayList<Edge> edges;

    public Word(String s) {
        this.endTickets = 1;
        this.text = s;
        this.edges = new ArrayList<Edge>();
    }

    public int edgeSum() {
        int sum = 0;
        for (Edge e : this.edges) {
            sum += e.getTickets();
        }
        return sum;
    }

    public int checkWinner(int count) {
        int i = 0;
        for (Edge e : this.edges) {
            count -= e.getTickets();
            if (count <= 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void addToEdges(Edge e) {
        this.edges.add(e);
    }

    public void addEndTickets() {
        this.endTickets++;
    }

    public int getEndTickets() {
        return this.endTickets;
    }

    public String getText() {
        return this.text;
    }

    public ArrayList getEdges() {
        return this.edges;
    }

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