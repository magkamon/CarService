import domain.Car;
import repository.ActiveCarRepository;
import repository.FixedCarRepository;
import util.Util;

import java.util.List;

public class GarageService {

    ActiveCarRepository activeCarRepository;
    FixedCarRepository fixedCarRepository;

    public GarageService(ActiveCarRepository activeCarRepository, FixedCarRepository fixedCarRepository) {
        this.activeCarRepository = activeCarRepository;
        this.fixedCarRepository = fixedCarRepository;
    }

    public boolean registerCar(String name, String color, String productionDate) {
        Car newCar = new Car(name, color, productionDate);
        if (Util.isNumber(newCar.getProductionDate())) {
            activeCarRepository.addCar(newCar);
            return true;
        }
        return false;
    }

    public List<Car> getAllActiveCars (){
        return activeCarRepository.getAllCars();
    }

    public boolean fixCar(Car carForFix) {
            activeCarRepository.deleteCar(carForFix);
            carForFix.setFixed(true);
            fixedCarRepository.addCar(carForFix);
            return true;
    }



}
