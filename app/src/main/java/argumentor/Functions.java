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
    * Reads through the given txt file and adds all the words to the
    * trie in a list determined by the parameter chainLength.
    * Clears the list if addNodes returns false meaning that a
    * sentence ending character has been encountered.
    * Sets all characters to lowercase and only allows words that pass
    * the matchesChars() check.
    * @param path String representing the path to the the data file.
    * @param trie The trie that all the new branches are added to.
    * @param chainLength int representing the depth of the trie and
    * the size of the list used.
    */
    public void processData(final String path, final Trie trie,
    final int chainLength) throws FileNotFoundException {
        File file = new File(path);
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

    /**
    * Generates a sentence from words in the given trie.
    * Uses checkGenerationEnd() to determine when to stop.
    * Clears the list if checkPunctuation returns true.
    * Uses nodeSearch if the list size matches listSize.
    * Otherwise uses lottery to get the next string.
    * Returns to root if the list is empty.
    * @param length Length of generated sentence.
    * 0 returns random length.
    * @param trie Trie containing all the strings.
    * @param listSize Size of the list used for generation.
    * @return String representing the generated sentence.
    */
    public String generate(final int length,
    final Trie trie, final int listSize) {
        Node currNode = trie.getRoot();
        ArrayList<String> wordList = new ArrayList<>();
        String sentence = "";
        int maxWords = length;
        int wordCount = 0;
        while (true) {
            if (checkGenerationEnd(maxWords, wordCount)) {
                sentence = addPunctuation(sentence);
                break;
            }
            if (!wordList.isEmpty()
            && trie.checkPunctuation(wordList.get(wordList.size() - 1))) {
                wordList.clear();
            }
            if (wordList.size() == listSize) {
                wordList = trie.nodeSearch(wordList);
            }
            if (wordList.size() < listSize) {
                if (!wordList.isEmpty()) {
                    currNode = currNode.getNode(wordList.get(
                    wordList.size() - 1));
                    if (currNode.getTicketSum() == 0) {
                        wordList.clear();
                    }
                }
                if (wordList.isEmpty()) {
                    currNode = trie.getRoot();
                }
                wordList.add(currNode.lottery());
            }
            sentence = (sentence + " " + wordList.get(wordList.size() - 1));
            wordCount++;
        }
        return sentence;
    }

    /**
    * Checks whether or not the sentence should end
    * based on the amount of words it currently has.
    * @param maxWords maximum length of sentence determined by user.
    * 0 leads to a randomized solution.
    * @param wordCount int representing the amount of words
    currently in the sentence.
    * @return true if the sentence will end, false if not
    */
    public boolean checkGenerationEnd(final int maxWords, final int wordCount) {
        if (maxWords == 0 && wordCount != 0) {
            Random rng = new Random();
            final int chance = 10;
            int count = rng.nextInt(chance);
            if (count == 1) {
                return true;
            }
            return false;
        } else if (maxWords == 0 && wordCount == 0) {
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
    public String addPunctuation(final String str) {
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

    /**
    * Checks if the given string matches the allowed characters.
    * @param str String to be checked.
    * @return true if matches, false if not.
    */
    public boolean matchesChars(final String str) {
        if (str.matches("[-,.?!'äöa-z]+")
            && !(str.equals(".") || str.equals("?")
            || str.equals("!") || str.equals("'"))) {
                return true;
        }
        return false;
    }
}
