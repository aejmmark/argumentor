package argumentor;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

import argumentor.Word;


public class Functions {
    public ArrayList<Word> starters;
    public ArrayList<Word> allWords;
    public Random rng;


    public Functions() {
        this.starters = new ArrayList<Word>();
        this.allWords = new ArrayList<Word>();
        this.rng = new Random();
    }

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
                        i = prev.edges.indexOf(curr);
                        if (i > -1) {
                            prev.edges.get(i).addTicket();
                        } else {
                            prev.edges.add(new Edge(curr));
                        }
                    }
                    if (s.charAt(s.length()-1) == '.' || s.charAt(s.length()-1) == '!' || s.charAt(s.length()-1) == '?') {
                        curr.addEndTickets();
                        prev = null;
                    } else {
                        prev = curr;
                    }
                }
            }
        }
        catch(Exception e) {
            System.out.println("FILE READ ERROR");
        }
    }

    public String generate() {
        int i = this.rng.nextInt(this.starters.size()-1);
        Word curr = this.starters.get(i);
        Edge edge = null;
        String sentence = curr.getText();
        boolean generating = true;
        int wordLimit = 10;
        while(generating) {
            //check end of sentence first
            //temporary solution
            wordLimit--;
            if (wordLimit == 0) {
                sentence.concat(".");
                break;
            }

            i = lottery(curr);
            curr = ((Word)((Edge)curr.getEdges().get(i)).getWord());
            sentence.concat(" ");
            sentence.concat(curr.getText());
        }
        return sentence;
    }

    public int lottery(Word w) {
        int sum = w.edgeSum();
        int winner = (this.rng.nextInt(sum));
        return w.checkWinner(winner);
    }
}
