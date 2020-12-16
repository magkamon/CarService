package repository;

import domain.Car;
import util.CarDataBase;

import java.util.List;

public class ActiveCarRepository implements CarRepository {
    private static final String TO_FIX_DIRECTORY = "active";

    @Override
    public List<Car> getAllCars() {
        return null;
    }

    @Override
    public void addCar(Car car) {
        CarDataBase carDataBase = new CarDataBase();
        List<Car> activeCars = carDataBase.readCarList(TO_FIX_DIRECTORY);
        activeCars.add(car);
        carDataBase.saveCarList(activeCars, TO_FIX_DIRECTORY);

    }

    @Override
    public void updateCar(Car car) {


    }

    @Override
    public void deleteCar(Car car) {
        CarDataBase carDataBase = new CarDataBase();
        List<Car> activeCars = carDataBase.readCarList(TO_FIX_DIRECTORY);
        activeCars.remove(car);
        carDataBase.saveCarList(activeCars,TO_FIX_DIRECTORY);
    }
}
