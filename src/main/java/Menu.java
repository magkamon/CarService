import repository.ActiveCarRepository;
import repository.FixedCarRepository;
import util.Util;

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
    private static final String INVALID_OPTION_MESSAGE = "Wybierz jedną z dostępnych opcji.";

    public void start() {
        boolean shouldContinue;
        do {
            printMenu();
            shouldContinue = handleUserChoice();
        } while (shouldContinue);
    }

    private void printMenu() {
        System.out.println(MENU_HEADER);
        for (String menuOption : MENU_OPTIONS) {
            System.out.println(menuOption);
        }
    }

    private boolean handleUserChoice() {
        String userChoice = getUserChoice();

        GarageManager garageManager = new GarageManager(new ActiveCarRepository(), new FixedCarRepository());
        switch (userChoice) {
            case "1": {
                System.out.println(MENU_OPTIONS[Integer.parseInt(userChoice)-1]);
                garageManager.printCarList();
                break;
            }
            case "2": {
                System.out.println(MENU_OPTIONS[Integer.parseInt(userChoice)-1]);
                garageManager.registerCar();
                break;
            }
            case "3": {
                System.out.println(MENU_OPTIONS[Integer.parseInt(userChoice)-1]);
                garageManager.fixCar();
                break;
            }
            case "4": {
                System.out.println(MENU_OPTIONS[Integer.parseInt(userChoice)-1]);
                break;
            }
            case "5": {
                System.out.println(MENU_OPTIONS[Integer.parseInt(userChoice)-1]);
                return false;
            }
            default: {
                System.out.println(INVALID_OPTION_MESSAGE);
            }
        }
        return true;
    }

    private String getUserChoice() {
        return Util.readFromUser(CHOICE_MESSAGE);
    }

}
