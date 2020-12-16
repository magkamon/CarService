package repository;

import domain.Car;
import util.CarDataBase;

import java.time.LocalDate;
import java.util.List;

public class FixedCarRepository implements CarRepository {

    private static final String FIXED_DIRECTORY = getCurrentDate();

    private static String getCurrentDate() {
        LocalDate today = LocalDate.now();
        return today.toString();
    }

    @Override
    public List<Car> getAllCars() {
        return null;
    }

    @Override
    public void addCar(Car car) {
        CarDataBase carDataBase = new CarDataBase();
        List<Car> activeCars = carDataBase.readCarList(FIXED_DIRECTORY);
        activeCars.add(car);
        carDataBase.saveCarList(activeCars, FIXED_DIRECTORY);
    }

    @Override
    public void updateCar(Car car) {

    }

    @Override
    public void deleteCar(Car car) {

    }
}
