package argumentor;

public final class App {

    /**
    * Constructor.
    */
    private App() {
    }

    /**
    * Runs the application.
    * @param args
    */
    public static void main(final String[] args) {
        UserInterface ui = new UserInterface();
        ui.mainMenu();
    }
}
