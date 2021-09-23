package argumentor;

import java.util.HashMap;

/** Tree structure containing the Nodes and Edges used to store words and form sentences.
*/
public class Tree {
    public HashMap<String, Node> allNodes;
    public Node root;

    public Tree() {
        this.allNodes = new HashMap<String,Node>();
        this.root = new Node("ROOT");
    }

    /** Returns the root Node.
    * @return Node representing the root of the tree.
    */
    public Node getRoot() {
        return this.root;
    }

    /** Adds a node if absent to the allNodes map using the given string.
    * @param str String used in the Node constructor.
    * @return Node containing the given String.
    */
    public Node addNode(String str) {
        Node node = this.allNodes.get(str);
        if (node == null) {
            node = new Node(str);
            this.allNodes.put(str,node);
        }
        return node;
    }

    /** Creates an Edge connecting the previous Node to the current Node.
     * Adds one to the Edges tickets if one already exists.
    * @param prev The previous Node
    * @param curr The current Node
    */
    public void addEdge(Node prev, Node curr) {
        Edge edge = prev.getEdge(curr);
        if (edge == null) {
            prev.addEdge(curr, new Edge(curr));
        } else {
            edge.addTicket();
        }
    }

    /** Checks if the given String contains a sentence ending character.
    * @param str String containing the current word
    * @param curr The current Node
    * @return Returns the root Node if str ends the sentence. Otherwise it return the param Node.
    */
   public Node checkEnd(String str, Node curr) {
    if (str.length() > 1 && (str.charAt(str.length()-1) == '.' || str.charAt(str.length()-1) == '!' || str.charAt(str.length()-1) == '?')) {
        //curr.addEndTicket();
        return this.root;
    }
    return curr;
   }
}
