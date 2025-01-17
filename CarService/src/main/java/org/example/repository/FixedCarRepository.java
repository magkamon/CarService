package org.example.repository;

import org.example.DataBase.CarDataBase;
import org.example.DataBase.CarDataBaseXml;
import org.example.domain.Car;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FixedCarRepository implements CarRepository {

    private static final String FIXED_DIRECTORY = getCurrentDate();

    @Override
    public List<Car> getAllCars() {
        return new ArrayList<>();
    }

    @Override
    public void addCar(Car car) {
        CarDataBase carDataBase = new CarDataBaseXml();
        List<Car> activeCars = carDataBase.readCarList(FIXED_DIRECTORY);
        activeCars.add(car);
        carDataBase.saveCarList(activeCars, FIXED_DIRECTORY);
    }

    //TODO
    @Override
    public void deleteCar(Car car) {

    }

    private static String getCurrentDate() {
        LocalDate today = LocalDate.now();
        return today.toString();
    }
}
