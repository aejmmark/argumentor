package argumentor;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
* Contains the main functions for processing data and generating sentences.
*/
public class Functions {

    /**
    * Constructor.
    */
    public Functions() {
    }

    /**
    * Scans through the data.txt file and
    * adds all the separate words to the given Tree.
    * Makes use of the addNode(), addEdge() and checkEnd() methods.
    * alt False uses the original tree based on the allNodes map.
    * alt True uses the alternative structure closer to a trie.
    * @param tree The Tree where all the String will be stored.
    * @param fileName name of the txt file.
    * @param alt boolean determining the storage method
    */
    public void processData(final boolean alt, final String fileName, final Tree tree) throws FileNotFoundException{
        long startTime = System.nanoTime();
        File file = new File(System.getProperty("user.dir")
        + "/" + fileName);
        Scanner scn = new Scanner(file);
        Node prev = tree.getRoot();
        while (scn.hasNextLine()) {
            String line = scn.nextLine().toLowerCase();
            String[] strs = line.split(" ");
            for (String str : strs) {
                if (str.matches("[-,.?!'äöa-z]+")
                && !(str.equals(".") || str.equals("?")
                || str.equals("!") || str.equals("'"))) {
                    Node curr;
                    if (alt) {
                        curr = prev.addNode(str); 
                    } else {
                        curr = tree.addNode(str);
                        tree.addEdge(prev, curr);
                    }
                    prev = tree.checkEnd(str, curr);
                }
            }
        }
        scn.close();
        System.out.println("Processing the file took "
        + ((System.nanoTime() - startTime) / 1000000000.0) + " seconds");
    }


    /**
    * Forms sentences by utilizing the Nodes lottery() and checkEnd() method.
    * @param length length of sentence determined by user.
    * 0 returns random length.
    * @param tree The Tree containing the necessary data to form the sentence.
    * @return String representing the formed sentence.
    */
    public String generate(final int length, final Tree tree) {
        long startTime = System.nanoTime();
        Node root = tree.getRoot();
        Node curr = root.lottery();
        String sentence = curr.getWord();
        sentence = sentence.substring(0, 1).toUpperCase()
        + sentence.substring(1).toLowerCase();
        int maxWords = length;
        int wordCount = 1;
        while (true) {
            if (curr.checkEnd(maxWords, wordCount)) {
                sentence = checkPunctuation(sentence);
                break;
            }
            if (curr.getTicketSum() == 0) {
                curr = root.lottery();
            } else {
                curr = curr.lottery();
            }
            sentence = (sentence + " " + curr.getWord());
            wordCount++;
        }
        System.out.println("Generating the sentence took "
        + ((System.nanoTime() - startTime) / 1000000000.0) + " seconds");
        return sentence;
    }

    /**
    * Ensures that the generated sentence ends correctly.
    * @param str sentence to be checked.
    * @return The complete sentence.
    */
    public String checkPunctuation(final String str) {
        String sentence = str;
        char end = sentence.charAt(sentence.length() - 1);
        if (!(end == '.' || end == '?' || end == '!')) {
            if (end == ',') {
                sentence = sentence.substring(0, sentence.length());
            }
            sentence = (sentence + ".");
        }
        return sentence;
    }
}
