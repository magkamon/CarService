package org.example.DataBase;

import org.example.domain.Car;

import java.util.List;

public interface CarDataBase {

    void saveCarList(List<Car> cars, String filename);
    List<Car> readCarList(String filename);
}
