package org.example.DataBase;

import org.example.domain.Car;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlRootElement(name = "Cars")
public class CarsXml {
    private List<Car> cars = null;

    public List<Car> getCars() {
        return cars;
    }

    @XmlElement(name = "Car")
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
