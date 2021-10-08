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
    * Tree class used by the main functions.
    */
    private Tree tree;
    /**
    * Contains the data processing and
    * sentence generating functions.
    */
    private Functions func;

    /**
    * Constructor.
    */
    public UserInterface() {
        this.scn = new Scanner(System.in);
        this.tree = new Tree();
        this.func = new Functions();
    }

    /**
    * Main interface that opens when the app is run.
    */
    public void mainMenu() {
        String fileName = "data.txt";
        String input;
        System.out.println("greetings!");
        System.out.println("(1) default sentence generation");
        System.out.println("(2) alternative sentence generation");
        System.out.println("(3) performance test");
        System.out.println("(4) change data file");
        System.out.println("(exit) leaves the app");
        while (true) {
            input = this.scn.nextLine();
            if (input.equals("exit")) {
                break;
            }
            if (input.equals("4")) {
                System.out.println("enter name of txt file in app directory");
                fileName = this.scn.nextLine();
            }
            if (input.equals("3")) {
                System.out.println("testing default version");
                performanceTest(false);
                System.out.println("testing alternative version");
                performanceTest(true);
                break;
            }
            if (input.equals("2") || input.equals("1")) {
                try {
                    boolean alt = input.equals("2");
                    this.func.processData(alt, fileName, this.tree);
                    sentenceGeneration();
                    break;
                } catch (Exception e) {
                    System.out.println("invalid file");
                }
            }
        }
        System.out.println("bye");
        this.scn.close();
    }

    /**
    * Sentence generation loop.
    */
    public void sentenceGeneration() {
        System.out.println("enter desired amount of words");
        System.out.println("(0) generates random length");
        System.out.println("(exit) leaves the app");
        while (true) {
            if (this.scn.hasNextInt()) {
                int length = this.scn.nextInt();
                String result = this.func.generate(length, this.tree);
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
    * Completes each one 100 times and chooses the median value.
    * @param alt determines wich version of the tree is used for tests.
    */
    public void performanceTest(final boolean alt) {
        long startTime;
        final int tests = 100;
        final int sentenceLength = 1000;
        final double timeConverter = 1000000000.0;
        ArrayList<Double> results = new ArrayList<>();
        try {
            for (int i = 0; i < tests; i++) {
                this.tree = new Tree();
                startTime = System.nanoTime();
                this.func.processData(alt, "data.txt", this.tree);
                results.add((System.nanoTime() - startTime) / timeConverter);
            }
        } catch (Exception e) {
            System.out.println("unexpected error");
        }
        Collections.sort(results);
        System.out.println("file processing took "
        + results.get(tests / 2) + " seconds");

        results = new ArrayList<>();
        for (int i = 0; i < tests; i++) {
            startTime = System.nanoTime();
            this.func.generate(sentenceLength, this.tree);
            results.add((System.nanoTime() - startTime) / timeConverter);
        }
        Collections.sort(results);
        System.out.println("generating " + sentenceLength
        + " word sentence took "
        + results.get(tests / 2) + " seconds");
    }
}
