package argumentor;

import java.util.ArrayList;

/**
* Trie structure used to store chains of strings.
*/
public class Trie {
    /**
    * The root node.
    */
    private Node root;

    /**
    * Sets up root.
    */
    public Trie() {
        this.root = new Node();
    }

    /**
    * Returns the root.
    * @return the root Node.
    */
    public Node getRoot() {
        return this.root;
    }

    /**
    * Starts from the root and compares the strings on
    * the given list to the edges of the trie.
    * Once it reaches depth-1 of the three it uses the lottery function
    * to determine the next word and adds it to the list.
    * Removes the oldest element of the list.
    * Returns an empty list if it encounters irregularities
    * in the data.
    * @param list The list used for comparison.
    * @return The modified list.
    */
    public ArrayList<String> nodeSearch(final ArrayList<String> list) {
        ArrayList<String> newList = new ArrayList<String>(list);
        Node curr = this.root;
        for (String str : newList) {
            curr = curr.getNode(str);
            if (curr == null || curr.getTicketSum() == 0) {
                newList.clear();
                return newList;
            }
        }
        newList.remove(0);
        newList.add(curr.lottery());
        return newList;
    }

    /**
    * Builds a branch of the trie by using the given list of strings.
    * Creates new nodes if edges matching the given string are not present.
    * Increments frequency and ticketSum if it encounters a previously created node.
    * Stops the process and returns true if it encounters a sentence ending character.
    * @param strings list used to build the branch.
    * @return false by default. true if encounters !/?/.
    */
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
            if (checkPunctuation(str)) {
                return true;
            }
            prev = curr;
        }
        return false;
    }

    /**
    * Checks whether or not the string ends in !/?/.
    * @param str String to be checked.
    * @return false by default. true if encounters !/?/.
    */
    public boolean checkPunctuation(final String str) {
        if (str.length() > 1 && (str.charAt(str.length() - 1) == '.'
        || str.charAt(str.length() - 1) == '!'
        || str.charAt(str.length() - 1) == '?')) {
            return true;
        }
        return false;
    }
}
