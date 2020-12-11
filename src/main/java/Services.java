import Util.Util;
import domain.Car;

import java.time.LocalDate;
import java.util.List;

import Util.CarDataBase;

public class Services {

    private static final String NAME_HEADER = "Nazwa: ";
    private static final String COLOR_HEADER = "Kolor: ";
    private static final String PRODUCTION_HEADER = "Rocznik: ";
    private static final String TO_FIX_DIRECTORY = "active";
    private static final String FIXED_DIRECTORY = getCurrentDate();
    private static final String USER_CONFIRMATION = "Potwierdź: T/N";
    private static final String USER_CHOICE_MESSAGE = "Wybrano: ";
    private static final String CAR_NUMBER_TO_FIX_MESSAGE = "Podaj numer samochodu do naprawy: ";
    private static final String EMPTY_LIST_MESSAGE = "Aktulanie lista jest pusta.";

    public void registerCar() {
        Car newCar = new Car(
                Util.readFromUser(NAME_HEADER),
                Util.readFromUser(COLOR_HEADER),
                Util.readFromUser(PRODUCTION_HEADER),
                false
        );
        String userConfirmation = Util.readFromUser(USER_CONFIRMATION);
        if (userConfirmation.equalsIgnoreCase("T")) {
            CarDataBase carDataBase = new CarDataBase();
            List<Car> activeCars = carDataBase.readCarList(TO_FIX_DIRECTORY);
            activeCars.add(newCar);
            carDataBase.saveCarList(activeCars, TO_FIX_DIRECTORY);
        }
    }

    public void fixCar() {
        int numberOfCarToFix = Integer.parseInt(Util.readFromUser(CAR_NUMBER_TO_FIX_MESSAGE));
        CarDataBase carDataBase = new CarDataBase();
        List<Car> activeCars = carDataBase.readCarList(TO_FIX_DIRECTORY);
        Car fixedCar = activeCars.get(numberOfCarToFix - 1);
        System.out.println(USER_CHOICE_MESSAGE + fixedCar.getName() + ", " + fixedCar.getColor() + ", " + fixedCar.getProductionDate());
        String userConfirmation = Util.readFromUser(USER_CONFIRMATION);
        if (userConfirmation.equalsIgnoreCase("T")) {
            fixedCar.setFixed(true);
            carDataBase.addCarToList(fixedCar, FIXED_DIRECTORY);
            activeCars.remove((numberOfCarToFix) - 1);
            new CarDataBase().saveCarList(activeCars, TO_FIX_DIRECTORY);
        }
    }

    public void printCarList() {
        CarDataBase carDataBase = new CarDataBase();
        List<Car> activeCars = carDataBase.readCarList(TO_FIX_DIRECTORY);
        if (activeCars.isEmpty()){
            System.out.println(EMPTY_LIST_MESSAGE);
        }
        for (Car car : activeCars) {
            System.out.println((activeCars.indexOf(car) + 1) + ". " + car.getName() + ", " + car.getColor() + ", " + car.getProductionDate());
        }
    }

    private static String getCurrentDate() {
        LocalDate today = LocalDate.now();
        return today.toString();
    }
}
