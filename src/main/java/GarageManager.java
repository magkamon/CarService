import repository.CarRepository;
import util.Util;
import domain.Car;

import java.util.List;

public class GarageManager {

    private static final String NAME_HEADER = "Nazwa: ";
    private static final String COLOR_HEADER = "Kolor: ";
    private static final String PRODUCTION_HEADER = "Rocznik: ";
    private static final String USER_CHOICE_MESSAGE = "Wybrano: ";
    private static final String CAR_NUMBER_TO_FIX_MESSAGE = "Podaj numer samochodu do naprawy: ";
    private static final String EMPTY_LIST_MESSAGE = "Aktulanie lista jest pusta.";
    private static final String PRODUCTION_DATE_ERROR = "Rocznik został podany niepoprawnie, podaj dane jeszcze raz";

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
        while(!Util.isNumber(newCar.getProductionDate())){
            System.out.println(PRODUCTION_DATE_ERROR);
            newCar.setProductionDate(Util.readFromUser(PRODUCTION_HEADER));
        }
        if (Util.readUserConfirmation(newCar.toString())) {
            activeCarRepository.addCar(newCar);
        }
    }

    public void fixCar() {
        int numberOfCarToFix = Integer.parseInt(Util.readFromUser(CAR_NUMBER_TO_FIX_MESSAGE));
        List<Car> activeCars = activeCarRepository.getAllCars();
        Car fixedCar = activeCars.get(numberOfCarToFix - 1);
        if (Util.readUserConfirmation(USER_CHOICE_MESSAGE + fixedCar)) {
            activeCarRepository.deleteCar(fixedCar);
            fixedCar.setFixed(true);
            fixedCarRepository.addCar(fixedCar);
        }
    }

    public void printCarList() {
        List<Car> activeCars = activeCarRepository.getAllCars();
        if (activeCars.isEmpty()){
            System.out.println(EMPTY_LIST_MESSAGE);
        }
        for (Car car : activeCars) {
            System.out.println((activeCars.indexOf(car) + 1) + ". " + car);
        }
    }
}
