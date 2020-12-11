import Util.Util;

public class Menu {

    private static final String MENU_HEADER = "Witaj w Serwisie samochodowym";
    private static final String[] MENU_OPTIONS = {
            "1. Pokaż aktywną listę",
            "2. Dodaj nowy pojazd",
            "3. Napraw pojazd",
            "4. Wczytaj listę z pliku",
            "5. Zamknij"
    };
    private static final String CHOICE_MESSAGE = "Twój wybór: ";

    public void start() {
        boolean shouldContinue;
        do {
            printMenu();
            shouldContinue = handleUserChoice();
        } while (shouldContinue);
    }

    private void printMenu() {
        System.out.println(MENU_HEADER);
        for (String menuOption : MENU_OPTIONS
        ) {
            System.out.println(menuOption);
        }
    }

    private boolean handleUserChoice() {
        String userChoice = getUserChoice();

        switch (userChoice) {
            case "1": {
                System.out.println("1. Pokaż aktywną listę");
                new Services().printCarList();
                break;
            }
            case "2": {
                System.out.println("2. Dodaj nowy pojazd");
                new Services().registerCar();
                break;
            }
            case "3": {
                System.out.println("3. Napraw pojazd");
                new Services().fixCar();
                break;
            }
            case "4": {
                System.out.println("4. Wczytaj listę z pliku");
                break;
            }
            case "5": {
                System.out.println("5. Zamknij");
                return false;
            }
            default: {
                System.out.println("Wybierz jedną z dostępnych opcji.");
            }
        }
        return true;
    }

    private String getUserChoice() {
        return Util.readFromUser(CHOICE_MESSAGE);
    }

}
