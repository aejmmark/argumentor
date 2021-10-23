package argumentor;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

/**
* Contains the main functions for processing data and generating sentences.
*/
public class Functions {

    /**
    * 
    */
    public void processData(final String fileName, final Trie trie,
    final int chainLength) throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir")
        + "/" + fileName);
        Scanner scn = new Scanner(file);
        ArrayList<String> wordList = new ArrayList<>();
        while (scn.hasNextLine()) {
            String line = scn.nextLine().toLowerCase();
            String[] strings = line.split(" ");
            for (String str : strings) {
                if (matchesChars(str)) {
                    wordList.add(str);
                    if (wordList.size() > chainLength) {
                        wordList.remove(0);
                    }
                    if (wordList.size() == chainLength) {
                        if (trie.addNodes(wordList)) {
                            wordList.clear();
                        }
                    }
                }
            }
        }
        scn.close();
    }

    public String generate(final int length,
    final Trie trie, final int listSize) {
        Node currNode = trie.getRoot();
        ArrayList<String> wordList = new ArrayList<>();
        String sentence = "";
        int maxWords = length;
        int wordCount = 0;
        while (true) {
            if (checkGenerationEnd(maxWords, wordCount)) {
                sentence = checkPunctuation(sentence);
                break;
            }
            if (wordList.size() == listSize) {
                if (trie.checkSentenceEnd(wordList.get(wordList.size()-1))) {
                    wordList.clear();
                } else {
                    wordList = trie.nodeSearch(wordList);
                }
            }
            if (wordList.size() < listSize) {
                if (!wordList.isEmpty()) {
                    currNode = currNode.getNode(wordList.get(wordList.size()-1));
                    if (currNode.getTicketSum() == 0 
                    || trie.checkSentenceEnd(wordList.get(wordList.size()-1))) {
                        wordList.clear();
                    }
                }
                if (wordList.isEmpty()) {
                    currNode = trie.getRoot();
                }
                wordList.add(currNode.lottery());
            }
            sentence = (sentence + " " + wordList.get(wordList.size()-1));
            wordCount++;
        }
        return sentence;
    }

    //sentence = sentence.substring(0, 1).toUpperCase()    yeah this should probably be after all puncs
    //+ sentence.substring(1).toLowerCase();

    /**
    * Checks whether or not the sentence should end
    * based on the amount of words it currently has.
    * @param maxWords maximum length of entence determined by user.
    * 0 leads to a randomized solution.
    * @param wordCount int representing the amount of words
    currently in the sentence.
    * @return true if the sentence will end, false if not
    */
    public boolean checkGenerationEnd(final int maxWords, final int wordCount) {
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

    public boolean matchesChars(final String str) {
        if (str.matches("[-,.?!'äöa-z]+")
            && !(str.equals(".") || str.equals("?")
            || str.equals("!") || str.equals("'"))) {
                return true;
        }
        return false;
    }
}
