package argumentor;

import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

/**
* Contains the main functions for processing data and generating sentences.
*/
public class Functions {
    /**
    * Scans through the data.txt file and
    * adds all the separate words to the given Tree.
    * Makes use of the addNode(), addEdge() and checkEnd() methods.
    * alt False uses the original tree based on the allNodes map.
    * alt True uses the alternative structure closer to a trie.
    * This version uses the addNodeAlt function instead.
    * @param tree The Tree where all the String will be stored.
    * @param fileName name of the txt file.
    * @param alt boolean determining the storage method
    */
    public void
    processData(final boolean alt, final String fileName, final Tree tree)
    throws FileNotFoundException {
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
                    Node curr = tree.addNode(str, prev, alt);
                    prev = tree.checkEnd(str, curr);
                }
            }
        }
        scn.close();
    }


    /**
    * Forms sentences by utilizing CheckEnd() and the Nodes lottery() method.
    * @param length length of sentence determined by user.
    * 0 returns random length.
    * @param tree The Tree containing the necessary data to form the sentence.
    * @return String representing the formed sentence.
    */
    public String generate(final int length, final Tree tree) {
        Node root = tree.getRoot();
        Node curr = root.lottery();
        String sentence = curr.getWord();
        sentence = sentence.substring(0, 1).toUpperCase()
        + sentence.substring(1).toLowerCase();
        int maxWords = length;
        int wordCount = 1;
        while (true) {
            if (checkEnd(maxWords, wordCount)) {
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
        return sentence;
    }

    /**
    * Checks whether or not the sentence should end
    * based on the amount of words it currently has.
    * @param maxWords maximum length of entence determined by user.
    * 0 leads to a randomized solution.
    * @param wordCount int representing the amount of words
    currently in the sentence.
    * @return true if the sentence will end, false if not
    */
    public boolean checkEnd(final int maxWords, final int wordCount) {
        if (maxWords == 0) {
            Random rng = new Random();
            final int chance = 10;
            int count = rng.nextInt(chance);
            if (count == 1) {
                return true;
            }
            return false;
        } else {
            return (maxWords <= wordCount);
        }

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
                sentence = sentence.substring(0, sentence.length() - 1);
            }
            sentence = (sentence + ".");
        }
        return sentence;
    }
}
