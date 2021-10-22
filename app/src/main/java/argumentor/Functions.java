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
                } else {
                    continue;
                }
                if (wordList.size() > chainLength) {
                    wordList.remove(0);
                }
                if (trie.addNodes(wordList)) {
                    wordList.clear();
                }
            }
        }
        scn.close();
    }

    public boolean matchesChars(final String str) {
        if (str.matches("[-,.?!'äöa-z]+")
            && !(str.equals(".") || str.equals("?")
            || str.equals("!") || str.equals("'"))) {
                return true;
        }
        return false;
    }

    public String generate(final int length,
    final Trie trie, final int chainLength) {
        Node curr = trie.getRoot();
        ArrayList<String> wordList = new ArrayList<>();
        String sentence = "";
        //sentence = sentence.substring(0, 1).toUpperCase()    yeah this should probably be after all puncs
        //+ sentence.substring(1).toLowerCase();
        int maxWords = length;
        int wordCount = 0;
        while (true) {
            if (checkGenerationEnd(maxWords, wordCount)) {
                sentence = checkPunctuation(sentence);
                break;
            }

            System.out.println(wordList);
            

            // check list if empty
            if (wordList.isEmpty()) {
                curr = trie.getRoot();
            } else {
                curr = curr.getNode(wordList.get(wordList.size()-1));
                if (trie.checkSentenceEnd(wordList.get(wordList.size()-1))) { // REMOVE THIS
                    System.out.println("Punc word!");
                    if (curr.isPunc()) {
                        System.out.println("Curr is punc, ticketsum" + curr.getTicketSum());
                    } else {
                        System.out.println("Curr is not punc, ticketsum " + curr.getTicketSum());
                    }
                }
            }

            System.out.println("Skipped!");

            // this stuff is for <chainlength lists or is it???
            if (curr.getTicketSum() == 0) {
                System.out.println("Ticketsum 0!");
                if (curr.isPunc()) {
                    System.out.println("Punc word handled!");
                    System.out.println("Punc word! " + wordList.get(wordList.size()-1));
                    wordList.clear();
                }
                curr = trie.getRoot();
            }



            if (wordList.size() == chainLength) {
                wordList = trie.nodeSearch(wordList, chainLength);
            } else {
                wordList.add(curr.lottery());
            }
            sentence = (sentence + " " + wordList.get(wordList.size()-1));
            wordCount++;
        }
        return sentence;
    }
}
