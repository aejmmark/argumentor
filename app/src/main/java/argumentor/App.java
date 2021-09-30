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
        String fileName = "dataTest.txt";
        System.out.println("Greetings! "
        + "Type name of txt file in the app directory."
        + " (default dataTest.txt)");
        System.out.println("exit leaves the app");
        while (true) {
            fileName = scn.nextLine();
            if (fileName.equals("exit")) {
                break;
            }
            try {
                func.processData(fileName, tree);
                break;
            } catch (Exception e) {
                System.out.println("No such file");
            }
        }
        if (!fileName.equals("exit")) {
            System.out.println("Desired amount of words?"
            + " 0 returns random length.");
            System.out.println("exit leaves the app");
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
        }
        System.out.println("Farewell");
        scn.close();
    }
}
