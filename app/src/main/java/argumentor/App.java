package argumentor;

import java.util.Scanner;

public class App {

    /**
    * Runs the application.
    */
    public static void main(String[] args) {
        Tree tree = new Tree();
        Functions func = new Functions();
        Scanner scn = new Scanner(System.in);
        func.processData(tree);
        System.out.println("Greetings! Type something to gain enlightenment. EXIT leaves the app.");
        while (true) {
            String input = scn.nextLine();
            if (input.equals("EXIT")) {
                break;
            } else {
                String result = func.generate(tree);
                System.out.println(result);
            }
        }
        System.out.println("Farewell");
        scn.close();
    }
}
