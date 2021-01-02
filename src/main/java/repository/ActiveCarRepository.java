package repository;

import DataBase.CarDataBase;
import domain.Car;
import DataBase.CarDataBaseJson;
import java.util.List;

public class ActiveCarRepository implements CarRepository {
    private static final String TO_FIX_DIRECTORY = "active";

    @Override
    public List<Car> getAllCars() {
        CarDataBase carDataBase = new CarDataBaseJson();
        return carDataBase.readCarList(TO_FIX_DIRECTORY);
    }

    @Override
    public void addCar(Car car) {
        CarDataBase carDataBase = new CarDataBaseJson();
        List<Car> activeCars = carDataBase.readCarList(TO_FIX_DIRECTORY);
        activeCars.add(car);
        carDataBase.saveCarList(activeCars, TO_FIX_DIRECTORY);
    }

    @Override
    public void deleteCar(Car car) {
        CarDataBase carDataBase = new CarDataBaseJson();
        List<Car> activeCars = carDataBase.readCarList(TO_FIX_DIRECTORY);
        activeCars.remove(car);
        carDataBase.saveCarList(activeCars,TO_FIX_DIRECTORY);
    }
}
