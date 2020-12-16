import repository.CarRepository;
import util.Util;
import domain.Car;

import java.time.LocalDate;
import java.util.List;

import util.CarDataBase;

public class GarageManager {

    private static final String NAME_HEADER = "Nazwa: ";
    private static final String COLOR_HEADER = "Kolor: ";
    private static final String PRODUCTION_HEADER = "Rocznik: ";
    private static final String TO_FIX_DIRECTORY = "active";

    private static final String USER_CONFIRMATION = "Potwierdź: T/N";
    private static final String USER_CHOICE_MESSAGE = "Wybrano: ";
    private static final String CAR_NUMBER_TO_FIX_MESSAGE = "Podaj numer samochodu do naprawy: ";
    private static final String EMPTY_LIST_MESSAGE = "Aktulanie lista jest pusta.";

    private final CarRepository activeCarRepository;
    private final CarRepository fixedCarRepository;

    public GarageManager(CarRepository activeCarRepository, CarRepository fixedCarRepository) {
        this.activeCarRepository = activeCarRepository;
        this.fixedCarRepository = fixedCarRepository;
    }


    public void registerCar() {
        Car newCar = new Car(
                Util.readFromUser(NAME_HEADER),
                Util.readFromUser(COLOR_HEADER),
                Util.readFromUser(PRODUCTION_HEADER)
        );
        String userConfirmation = Util.readFromUser(USER_CONFIRMATION);
        if (userConfirmation.equalsIgnoreCase("T")) {
            activeCarRepository.addCar(newCar);
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
            activeCarRepository.deleteCar(fixedCar);
            fixedCar.setFixed(true);
            fixedCarRepository.addCar(fixedCar);
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


}
