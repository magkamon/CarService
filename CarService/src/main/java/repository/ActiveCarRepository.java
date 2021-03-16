package repository;

import DataBase.CarDataBase;
import domain.Car;
import DataBase.CarDataBaseJson;
import java.util.List;

public class ActiveCarRepository implements CarRepository {

    private static final String TO_FIX_DIRECTORY = "active";
    private final CarDataBase carDataBase = new CarDataBaseJson();

    @Override
    public List<Car> getAllCars() {
        return carDataBase.readCarList(TO_FIX_DIRECTORY);
    }

    @Override
    public void addCar(Car car) {
        List<Car> activeCars = carDataBase.readCarList(TO_FIX_DIRECTORY);
        activeCars.add(car);
        carDataBase.saveCarList(activeCars, TO_FIX_DIRECTORY);
    }

    @Override
    public void deleteCar(Car car) {
        List<Car> activeCars = carDataBase.readCarList(TO_FIX_DIRECTORY);
        activeCars.remove(car);
        carDataBase.saveCarList(activeCars,TO_FIX_DIRECTORY);
    }
}
