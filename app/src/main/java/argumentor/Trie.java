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

    public ArrayList<String> nodeSearch(final ArrayList<String> list, final int chainLength) {
        ArrayList<String> newList = new ArrayList<String>(list);
        Node curr = this.root;
        for (String str : newList) {
            curr = curr.getNode(str);
            if (curr == null || curr.getTicketSum() == 0) { // failsafe in case branch is shorter
                System.out.println("nodeSearch NULL");
                newList.clear();
                newList.add(this.root.lottery());
                return newList;
            }
        }
        System.out.println("nodeSearch as intended");
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
