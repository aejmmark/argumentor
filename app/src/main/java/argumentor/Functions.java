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
    * Makes use of the Trees addNode(), addEdge() and checkEnd() methods.
    * @param tree The Tree where all the String will be stored.
    * @param fileName name of the txt file.
    */
    public void processData(final String fileName, final Tree tree) throws FileNotFoundException{
        File file = new File(System.getProperty("user.dir")
        + "/" + fileName);
        Scanner scn = new Scanner(file);
        Node prev = tree.getRoot();
        while (scn.hasNextLine()) {
            String line = scn.nextLine().toLowerCase();
            String[] strs = line.split(" ");
            for (String str : strs) {
                if (str.matches("[.?!'äöa-z]+")
                && !(str.equals(".") || str.equals("?")
                || str.equals("!") || str.equals("'"))) {
                Node curr = tree.addNode(str);
                tree.addEdge(prev, curr);
                prev = tree.checkEnd(str, curr);
                }
            }
        }
        scn.close();
    }


    /**
    * Forms sentences by utilizing the Nodes lottery() and checkEnd() method.
    * @param length length of sentence determined by user.
    * 0 returns random length.
    * @param tree The Tree containing the necessary data to form the sentence.
    * @return String representing the formed sentence.
    */
    public String generate(final int length, final Tree tree) {
        Node curr = tree.getRoot().lottery();
        String sentence = curr.getWord();
        sentence = sentence.substring(0, 1).toUpperCase()
        + sentence.substring(1).toLowerCase();
        int maxWords = length;
        int wordCount = 1;
        while (true) {
            if (curr.checkEnd(maxWords, wordCount)) {
                char end = sentence.charAt(sentence.length() - 1);
                if (!(end == '.' || end == '?' || end == '!')) {
                    sentence = (sentence + ".");
                }
                break;
            }
            curr = curr.lottery();
            sentence = (sentence + " " + curr.getWord());
            wordCount++;
        }
        return sentence;
    }
}
