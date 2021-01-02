package DataBase;

import domain.Car;

import java.util.List;

public interface CarDataBase {

    void saveCarList(List<Car> carsList, String filename);
    List<Car> readCarList(String filename);
}
