package argumentor;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
* User interface for the sentence generator app.
*/
public class UserInterface {
    /**
    * Scanner for reading user inputs.
    */
    private Scanner scn;
    /**
    * Trie class used by the main functions.
    */
    private Trie trie;
    /**
    * Contains the data processing and
    * sentence generating functions.
    */
    private Functions func;
    /**
    * Maximum and minimum length for
    * markov chains.
    */
    final int maxChain = 7;
    final int minChain = 2;

    /**
    * Constructor.
    */
    public UserInterface() {
        this.scn = new Scanner(System.in);
        this.trie = new Trie();
        this.func = new Functions();
    }

    /**
    * Main interface that opens when the app is run.
    */
    public void mainMenu() {
        String fileName = "data.txt";
        String input;
        System.out.println("greetings!");
        System.out.println("(1) sentence generation");
        System.out.println("(2) performance test");
        System.out.println("(3) change data file");
        System.out.println("(exit) leaves the app");
        while (true) {
            input = this.scn.nextLine();
            if (input.equals("exit")) {
                break;
            }
            if (input.equals("3")) {
                System.out.println("enter name of txt file in app directory");
                fileName = this.scn.nextLine();
                System.out.println("data file changed");
            }
            if (input.equals("2")) {
                System.out.println("testing performance");
                //performanceTest();
                break;
            }
            if (input.equals("1")) {
                System.out.println("enter desired chain length between 2 and 7");
                while(true) {
                    if (this.scn.hasNextInt()) {
                        int chainLength = this.scn.nextInt();
                        if (chainLength < minChain) {
                            chainLength = minChain;
                        }
                        if (chainLength > maxChain) {
                            chainLength = maxChain;
                        }
                        try {
                            this.func.processData(fileName, trie, chainLength+1);
                            sentenceGeneration(chainLength);
                            return;
                            
                        } catch (Exception e) {
                            System.out.println(e);
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("");
        System.out.println("bye");
        this.scn.close();
    }

    /**
    * Sentence generation loop.
    */
    public void sentenceGeneration(final int chainLength) {
        System.out.println("enter desired amount of words");
        System.out.println("(0) generates random length");
        System.out.println("(exit) leaves the app");
        while (true) {
            //System.out.println("Node 1");
            //for (String str : this.trie.getRoot().getEdgeMap().keySet()) {
            //    System.out.println(str);
            //}
            //for (TrieNode node : this.trie.getRoot().getEdgeMap().values()) {
            //    System.out.println("New Branch!");
            //    for (TrieNode node2 : node.getEdgeMap().values()) {
            //        System.out.println("Node 2");
            //        for (String str2 : node.getEdgeMap().keySet()) {
            //            System.out.println(str2);
            //        }
            //        System.out.println("Node 3");
            //        for (String str3 : node2.getEdgeMap().keySet()) {
            //            System.out.println(str3);
            //        }
            //    }
            //}
            if (this.scn.hasNextInt()) {
                int length = this.scn.nextInt();
                String result = this.func.generate(length, trie, chainLength);
                System.out.println(result);
            } else {
                String input = this.scn.nextLine();
                if (input.equals("exit")) {
                    break;
                }
            }
        }
    }

    /**
    * Runs performance tests for the main functions.
    * Completes each one 500 times and chooses the median value.
    * @param alt determines wich version of the tree is used for tests.
    */
    //public void performanceTest(final boolean alt) {
    //    long startTime;
    //    final int tests = 500;
    //    final int[] sentenceLength = {10, 100, 1000, 10000};
    //    final double timeConverter = 1000000000.0;
    //    ArrayList<Double> results = new ArrayList<>();
    //    try {
    //        for (int i = 0; i < tests; i++) {
    //            this.tree = new Tree();
    //            startTime = System.nanoTime();
    //            this.func.processData(alt, "data.txt", this.tree);
    //            results.add((System.nanoTime() - startTime) / timeConverter);
    //        }
    //    } catch (Exception e) {
    //        System.out.println("unexpected error");
    //    }
    //    Collections.sort(results);
    //    System.out.println("file processing took "
    //    + results.get(tests / 2) + " seconds");
//
    //    for (int i = 0; i < sentenceLength.length; i++) {
    //        results = new ArrayList<>();
    //        for (int j = 0; j < tests; j++) {
    //            startTime = System.nanoTime();
    //            this.func.generate(sentenceLength[i], this.tree);
    //            results.add((System.nanoTime() - startTime) / timeConverter);
    //        }
    //        Collections.sort(results);
    //        System.out.println("generating " + sentenceLength[i]
    //        + " word sentence took "
    //        + results.get(tests / 2) + " seconds");
    //    }
    //}
}
