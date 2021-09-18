package argumentor;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Functions {
    public ArrayList<Word> starters;
    public ArrayList<Word> allWords;

    /** Contains the main functions used by the app. Probably will be refactored later.
    */
    public Functions() {
        this.starters = new ArrayList<Word>();
        this.allWords = new ArrayList<Word>();
    }

    /** Reads lines from determined file and splits them into separate strings. Creates Words from strings.
     * Adds Word to starters if the previous word ended a sentence.
     * Adds Word to allWords if not previously encountered. Otherwise adds a ticket to the edge connecting the previous Word to the current Word.
     * Sets previous Word to null if the current ended a sentence. Otherwise sets previous Word as the current one.
    */
    public void processData() {
        try {
            File file = new File(System.getProperty("user.dir") + "/data.txt");
            Scanner scn = new Scanner(file);
            Word prev = null;
            while(scn.hasNextLine()) {
                String line = scn.nextLine();
                line = line.toLowerCase();
                String[] strs = line.split(" ");
                for (String s : strs) {
                    Word curr = new Word(s);
                    int i = this.allWords.indexOf(curr);
                    if (i > -1) {
                        curr = (Word)this.allWords.get(i);
                    } else {
                        this.allWords.add(curr);
                    }
                    if (prev == null) {
                        if (!this.starters.contains(curr)) {
                            this.starters.add(curr);
                        }
                    } else {
                        Edge e = new Edge(curr);
                        i = prev.getEdges().indexOf(e);
                        if (i > -1) {
                            ((Edge)prev.getEdges().get(i)).addTicket();
                        } else {
                            prev.addEdge(new Edge(curr));
                        }
                    }
                    if (s.length() > 1 && (s.charAt(s.length()-1) == '.' || s.charAt(s.length()-1) == '!' || s.charAt(s.length()-1) == '?')) {
                        curr.addEndTicket();
                        prev = null;
                    } else {
                        prev = curr;
                    }
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }


    /** Generates the sentence. Starts by choosing a word from the starters list.
     * Chooses next words using the current words lottery() function. Currently ends sentence at 10 words.
    * @return String representing the formed sentence.
    */
    public String generate() {
        Random rng = new Random();
        int i = rng.nextInt(this.starters.size());
        Word curr = this.starters.get(i);
        String sentence = curr.getText();
        int wordLimit = 10;
        while(true) {
            //temporary solution
            wordLimit--;
            if (wordLimit == 0) {
                sentence = sentence.replaceAll("[^a-zA-Z ]", "");
                sentence = (sentence + ".");
                break;
            }
            i = curr.lottery();
            curr = ((Word)((Edge)curr.getEdges().get(i)).getWord());
            sentence = (sentence + " " + curr.getText());
        }
        return sentence;
    }
}
