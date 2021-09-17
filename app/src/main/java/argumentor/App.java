package argumentor;

public class App {

    public static void main(String[] args) {
        Functions func = new Functions();
        func.processData();
        String result = func.generate();
        System.out.println(result);
    }
}
