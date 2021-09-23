package argumentor;

import java.util.Scanner;
import java.io.File;

public class Functions {

    /** Contains the main functions for processing data and generating sentences
    */
    public Functions() {
    }

    /** Scans through the data.txt file and adds all the separate words to the given Tree.
     * Makes use of the Trees addNode(), addEdge() and checkEnd() methods.
    * @param tree The Tree where all the String will be stored.
    */
    public void processData(Tree tree) {
        try {
            File file = new File(System.getProperty("user.dir") + "/data.txt");
            Scanner scn = new Scanner(file);
            Node prev = tree.getRoot();
            while(scn.hasNextLine()) {
                String line = scn.nextLine().toLowerCase();
                String[] strs = line.split(" ");
                for (String str : strs) {
                    Node curr = tree.addNode(str);
                    tree.addEdge(prev, curr);
                    prev = tree.checkEnd(str, curr);
                }
            }
            scn.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }


    /** Forms sentences by utilizing the Nodes lottery() and checkEnd() method.
    * @param tree The Tree containing the necessary data to form the sentence.
    * @return String representing the formed sentence.
    */
    public String generate(Tree tree) {
        Node curr = tree.getRoot().lottery();
        String sentence = curr.getWord();
        int wordCount = 1;
        while(true) {
            wordCount++;
            if (curr.checkEnd(wordCount)) {
                sentence = sentence.replaceAll("[^a-zA-Z ]", "");
                sentence = (sentence + ".");
                break;
            }
            curr = curr.lottery();
            sentence = (sentence + " " + curr.getWord());
        }
        return sentence;
    }
}
