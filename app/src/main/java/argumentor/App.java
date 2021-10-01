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
        boolean alt = false;
        System.out.println("Greetings! "
        + "type name of txt file in the app directory."
        + " (default dataTest.txt)");
        System.out.println("exit leaves the app");
        System.out.println("alt enters alternative storage mode");
        while (true) {
            fileName = scn.nextLine();
            if (fileName.equals("exit")) {
                break;
            }
            if (fileName.equals("alt")) {
                alt = true;
                System.out.println("alternative mode activated");
                continue;
            }
            try {
                func.processData(alt, fileName, tree);
                break;
            } catch (Exception e) {
                System.out.println("No such file");
            }
        }
        if (!fileName.equals("exit")) {
            System.out.println("desired amount of words?"
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
