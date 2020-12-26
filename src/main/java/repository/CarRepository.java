package repository;

import domain.Car;

import java.util.List;

public interface CarRepository {
    List<Car> getAllCars();
    void addCar(Car car);
    void deleteCar(Car car);
}
