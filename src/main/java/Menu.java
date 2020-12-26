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
        do {
            printMenu();
            int choiceNumber = Integer.parseInt(getUserChoice()) - 1;
            if (choiceNumber != 5) {
                handleUserChoice(choiceNumber);
            } else {
                break;
            }
        } while (true);
    }

    private void printMenu() {
        System.out.println(MENU_HEADER);
        for (String menuOption : MENU_OPTIONS) {
            System.out.println(menuOption);
        }
    }

    private void handleUserChoice(int choiceNumber) {
        GarageManager garageManager = new GarageManager(new ActiveCarRepository(), new FixedCarRepository());
        switch (choiceNumber) {
            case 0: {
                System.out.println(MENU_OPTIONS[choiceNumber]);
                garageManager.printCarList();
                break;
            }
            case 1: {
                System.out.println(MENU_OPTIONS[choiceNumber]);
                garageManager.registerCar();
                break;
            }
            case 2: {
                System.out.println(MENU_OPTIONS[choiceNumber]);
                garageManager.fixCar();
                break;
            }
            case 3: {
                System.out.println(MENU_OPTIONS[choiceNumber]);
                break;
            }
            case 4: {
                System.out.println(MENU_OPTIONS[choiceNumber]);
            }
            default: {
                System.out.println(INVALID_OPTION_MESSAGE);
            }
        }
    }

    private String getUserChoice() {
        return Util.readFromUser(CHOICE_MESSAGE);
    }
}
