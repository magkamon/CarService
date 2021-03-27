import org.example.repository.ActiveCarRepository;
import org.example.repository.FixedCarRepository;
import org.example.service.GarageService;
import org.example.util.Util;

public class Menu {

    private static final String MENU_HEADER = "Witaj w Serwisie samochodowym";
    private static final String[] MENU_OPTIONS = {
            "1. Pokaż aktywną listę",
            "2. Dodaj nowy pojazd",
            "3. Napraw pojazd",
            "4. Zamknij"
    };
    private static final String CHOICE_MESSAGE = "Twój wybór: ";
    private static final String INVALID_OPTION_MESSAGE = "Wybierz jedną z dostępnych opcji.";

    public void start() {
        do {
            printMenu();
            int choiceNumber = Integer.parseInt(getUserChoice());
            if (choiceNumber != 4) {
                handleUserChoice(choiceNumber - 1);
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
        GarageService garageService = new GarageService(new ActiveCarRepository(), new FixedCarRepository());
        GarageView garageView = new GarageView(garageService);

        System.out.println(MENU_OPTIONS[choiceNumber]);
        switch (choiceNumber) {
            case 0: {
                garageView.printCarList();
                break;
            }
            case 1: {
                garageView.showRegisterCarView();
                break;
            }
            case 2: {
                garageView.showFixCarView();
                break;
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
