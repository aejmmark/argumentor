package argumentor;

import java.util.HashMap;

/**
* Tree structure containing the Nodes and Edges
* used to store words and form sentences.
*/
public class Tree {
    /**
    * Hashmap containing all Nodes.
    * Keys are the Strings contained in the Nodes
    */
    private HashMap<String, Node> allNodes;
    /**
    * Root Node of the tree.
    */
    private Node root;

    /**
    * Constructor that creates the allNodes map and the root.
    */
    public Tree() {
        this.allNodes = new HashMap<String, Node>();
        this.root = new Node("ROOT");
    }

    /**
    * Returns the root Node.
    * @return Node representing the root of the tree.
    */
    public Node getRoot() {
        return this.root;
    }

    /**
    * Returns the map containing all String-Node pairings.
    * @return Hashmap containing all Nodes.
    */
    public HashMap<String, Node> getAllNodes() {
        return this.allNodes;
    }

    /**
    * Searches for Node with given string and
    * creates new one if not present.
    * Connects it with the given Node prev.
    * Increases tickets if already present.
    * Does not add to allNodes if alt is true.
    * @param str String used in creating the new Node.
    * @return returns the Node that was connected to this one.
    */
    public Node addNode(final String str, final Node prev, final boolean alt) {
        Node newNode = new Node(str);
        if (!alt) {
            newNode = addToAllNodes(str);
        }
        Integer tickets = prev.getTickets(newNode);
        if (tickets == null) {
            prev.addEdge(newNode);
            return newNode;
        } else {
            prev.addTicket(newNode);
            return newNode;
        }
    }

    /**
    * Adds a node if absent to the allNodes map using the given string.
    * @param str String used in the Node constructor.
    * @return Node containing the given String.
    */
    public Node addToAllNodes(final String str) {
        Node newNode = this.allNodes.get(str);
        if (newNode == null) {
            newNode = new Node(str);
            this.allNodes.put(str, newNode);
        }
        return newNode;
    }

    /**
    * Checks if the given String contains a sentence ending character.
    * @param str String containing the current word
    * @param curr The current Node
    * @return Returns the root Node if str ends the sentence.
    Otherwise it return the param Node.
    */
   public Node checkSentenceEnd(final String str, final Node curr) {
    if (str.length() > 1 && (str.charAt(str.length() - 1) == '.'
    || str.charAt(str.length() - 1) == '!'
    || str.charAt(str.length() - 1) == '?')) {
        return this.root;
    }
    return curr;
   }
}
