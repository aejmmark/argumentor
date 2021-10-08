package argumentor;

import java.util.HashMap;

/**
* Tree structure containing the Nodes and Edges
* used to store words and form sentences.
*/
public class Tree {
    /**
    * HashMap containing all Nodes.
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
    * Returns the map containing all String - Node pairings.
    * @return HashMap containing all Nodes.
    */
    public HashMap<String, Node> getAllNodes() {
        return this.allNodes;
    }

    /**
    * Adds a node if absent to the allNodes map using the given string.
    * @param str String used in the Node constructor.
    * @return Node containing the given String.
    */
    public Node addNode(final String str) {
        Node node = this.allNodes.get(str);
        if (node == null) {
            node = new Node(str);
            this.allNodes.put(str, node);
        }
        return node;
    }

    /**
    * Creates an edge (Node - int pair)
    * connecting the previous Node to the current Node.
    * Adds one to the tickets if one already exists.
    * Returns if Nodes have the same word.
    * @param prev The previous Node
    * @param curr The current Node
    */
    public void addEdge(final Node prev, final Node curr) {
        if (prev.equals(curr)) {
            return;
        }
        Integer tickets = prev.getEdgeMap().get(curr);
        if (tickets == null) {
            prev.addEdge(curr);
        } else {
            prev.addTicket(curr);
        }
    }

    /**
    * Checks if the given String contains a sentence ending character.
    * @param str String containing the current word
    * @param curr The current Node
    * @return Returns the root Node if str ends the sentence.
    Otherwise it return the param Node.
    */
   public Node checkEnd(final String str, final Node curr) {
    if (str.length() > 1 && (str.charAt(str.length() - 1) == '.'
    || str.charAt(str.length() - 1) == '!'
    || str.charAt(str.length() - 1) == '?')) {
        return this.root;
    }
    return curr;
   }
}
