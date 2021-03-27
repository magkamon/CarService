import org.example.domain.Car;
import org.example.service.GarageService;
import org.example.util.Util;

import java.util.List;

public class GarageView {

    private static final String NAME_HEADER = "Nazwa: ";
    private static final String COLOR_HEADER = "Kolor: ";
    private static final String PRODUCTION_HEADER = "Rocznik: ";
    private static final String USER_CHOICE_MESSAGE = "Wybrano: ";
    private static final String CAR_NUMBER_TO_FIX_MESSAGE = "Podaj numer samochodu do naprawy: ";
    private static final String EMPTY_LIST_MESSAGE = "Aktulanie lista jest pusta.";
    private static final String PRODUCTION_DATE_ERROR = "Rocznik został podany niepoprawnie, podaj dane jeszcze raz";

    GarageService garageService;

    public GarageView(GarageService garageService) {
        this.garageService = garageService;
    }

    public void showRegisterCarView() {

        String name = Util.readFromUser(NAME_HEADER);
        String color = Util.readFromUser(COLOR_HEADER);
        String productionDate = Util.readFromUser(PRODUCTION_HEADER);

        while(!Util.isNumber(productionDate)){
            System.out.println(PRODUCTION_DATE_ERROR);
            productionDate = Util.readFromUser(PRODUCTION_HEADER);
        }

        if(Util.readUserConfirmation(name + ", " + color + ", " + productionDate)){
            garageService.registerCar(name, color, productionDate);
        }
    }

    public void showFixCarView() {
        int numberOfCarToFix = Integer.parseInt(Util.readFromUser(CAR_NUMBER_TO_FIX_MESSAGE));
        List<Car> activeCars = garageService.getAllActiveCars();
        Car carForFix = activeCars.get(numberOfCarToFix - 1);

        if (Util.readUserConfirmation(USER_CHOICE_MESSAGE + carForFix)) {
            garageService.fixCar(carForFix);
        }
    }

    public void printCarList() {
        List<Car> activeCars = garageService.getAllActiveCars();
        if (activeCars.isEmpty()){
            System.out.println(EMPTY_LIST_MESSAGE);
        }
        for (Car car : activeCars) {
            System.out.println((activeCars.indexOf(car) + 1) + ". " + car);
        }
    }

}
