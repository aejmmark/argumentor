package argumentor;

import java.util.ArrayList;

public class Trie {
    private Node root;

    public Trie() {
        this.root = new Node();
    }

    public Node getRoot() {
        return this.root;
    }

    public ArrayList<String> nodeSearch(final ArrayList<String> list) {
        ArrayList<String> newList = new ArrayList<String>(list);
        Node curr = this.root;
        for (String str : newList) {
            curr = curr.getNode(str);
            if (curr == null || curr.getTicketSum() == 0) { // failsafe in case branch is shorter
                newList.clear();
                return newList;
            }
        }
        newList.remove(0);
        newList.add(curr.lottery());
        return newList;
    }

    public boolean addNodes(final ArrayList<String> strings) {
        Node prev = this.root;
        for (String str : strings) {
            Node curr = prev.getNode(str);
            if (curr == null) {
                curr = new Node();
                prev.addEdge(str, curr);
            } else {
                curr.incrementFreq();
                prev.incrementTicketSum();    
            }
            if (checkSentenceEnd(str)) {
                return true;
            }
            prev = curr;
        }
        return false;
    }

    public boolean checkSentenceEnd(final String str) {
        if (str.length() > 1 && (str.charAt(str.length() - 1) == '.'
        || str.charAt(str.length() - 1) == '!'
        || str.charAt(str.length() - 1) == '?')) {
            return true;
        }
        return false;
    }
}
