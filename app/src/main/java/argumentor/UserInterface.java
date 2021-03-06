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
    private final int maxChain = 8;
    private final int minChain = 2;
    private final int[] testChains = {3, 5, 7};

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
        String path = createPath("data.txt");
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
                String fileName = this.scn.nextLine();
                path = createPath(fileName);
                System.out.println("data file changed");
            }
            if (input.equals("2")) {
                System.out.println("testing performance at chain length "
                + testChains[0]);
                performanceTest(testChains[0]);
                System.out.println("");
                System.out.println("testing performance at chain length "
                + testChains[1]);
                performanceTest(testChains[1]);
                System.out.println("");
                System.out.println("testing performance at chain length "
                + testChains[2]);
                performanceTest(testChains[2]);
                break;
            }
            if (input.equals("1")) {
                System.out.println(
                "enter desired chain length between "
                + minChain + " and " + maxChain);
                while (true) {
                    if (this.scn.hasNextInt()) {
                        int chainLength = this.scn.nextInt();
                        if (chainLength < minChain) {
                            chainLength = minChain;
                        }
                        if (chainLength > maxChain) {
                            chainLength = maxChain;
                        }
                        int listSize = chainLength - 1;
                        try {
                            this.func.processData(path, trie, chainLength);
                            sentenceGeneration(listSize);
                            return;
                        } catch (Exception e) {
                            System.out.println(e);
                            return;
                        }
                    } else {
                        input = this.scn.nextLine();
                        if (input.equals("exit")) {
                            return;
                        }
                    }
                }
            }
        }
        this.scn.close();
    }

    /**
    * Sentence generation loop.
    * @param listSize used by generate().
    */
    public void sentenceGeneration(final int listSize) {
        System.out.println("chain length " + (listSize + 1));
        System.out.println("enter desired amount of words");
        System.out.println("(0) generates random length");
        System.out.println("(exit) leaves the app");
        while (true) {
            if (this.scn.hasNextInt()) {
                int length = this.scn.nextInt();
                String result = this.func.generate(length, trie, listSize);
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
    * Creates a path to the file.
    * @param fileName String representing the name of the txt file.
    * @return complete path.
    */
    public String createPath(final String fileName) {
        final int pathIndex = 18;
        String path = App.class.getProtectionDomain()
        .getCodeSource().getLocation().getPath();
        path = path.substring(0, path.length() - pathIndex)
        + fileName;
        return path;
    }

    /**
    * Runs performance tests for the main functions.
    * Completes each one 100 times and chooses the median value.
    * @param chainLength determines size of the markov chains.
    */
    public void performanceTest(final int chainLength) {
        long startTime;
        int listSize = chainLength - 1;
        final int tests = 100;
        final int[] sentenceLength = {10, 100, 1000, 10000};
        final double timeConverter = 1000000000.0;
        ArrayList<Double> results = new ArrayList<>();
        try {
            for (int i = 0; i < tests; i++) {
                this.trie = new Trie();
                startTime = System.nanoTime();
                this.func.processData("data.txt", this.trie, chainLength);
                results.add((System.nanoTime() - startTime) / timeConverter);
            }
        } catch (Exception e) {
            System.out.println("unexpected error");
        }
        Collections.sort(results);
        System.out.println("file processing took "
        + results.get(tests / 2) + " seconds");

        for (int i = 0; i < sentenceLength.length; i++) {
            results = new ArrayList<>();
            for (int j = 0; j < tests; j++) {
                startTime = System.nanoTime();
                this.func.generate(sentenceLength[i], this.trie, listSize);
                results.add((System.nanoTime() - startTime) / timeConverter);
            }
            Collections.sort(results);
            System.out.println("generating " + sentenceLength[i]
            + " word sentence took "
            + results.get(tests / 2) + " seconds");
        }
    }
}
