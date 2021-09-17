package argumentor;

import argumentor.Word;

public class Edge {
    public Word word;
    public int tickets;

    public Edge(Word word) {
        this.word = word;
        this.tickets = 1;
    }

    public void addTicket() {
        this.tickets += 1;
    }

    public Word getWord() {
       return this.word; 
    }

    public int getTickets() {
        return this.tickets;
    }

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
