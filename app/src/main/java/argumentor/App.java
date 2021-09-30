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
        System.out.println("Greetings!"
        + "Type name of txt file in the app directory."
        + " (default dataTest.txt)");
        String fileName = scn.nextLine();
        func.processData(fileName, tree);
        System.out.println("Desired amount of words?"
        + "0 returns random length. exit leaves the app");
        while (true) {
            if (scn.hasNextInt()) {
                int length = scn.nextInt();
                String result = func.generate(length, tree);
                System.out.println(result);
            } else {
                String input = scn.nextLine();
                if (input.equals("exit")) {
                    break;
                }
            }
        }
        System.out.println("Farewell");
        scn.close();
    }
}
