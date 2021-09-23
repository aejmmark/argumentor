package argumentor;

public class App {

    /**
    * Runs the application.
    */
    public static void main(String[] args) {
        Tree tree = new Tree();
        Functions func = new Functions();
        func.processData(tree);
        String result = func.generate(tree);
        System.out.println(result);
    }
}
