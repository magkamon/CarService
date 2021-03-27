package org.example.domain;
import java.util.Objects;

public class Car {

    private String name;
    private String color;
    private String productionDate;
    private Boolean isFixed;

    public Car(String name, String color, String productionDate) {
        this.name = name;
        this.color = color;
        this.productionDate = productionDate;
        this.isFixed = false;
    }

    public Car() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public Boolean getFixed() {
        return isFixed;
    }

    public void setFixed(Boolean fixed) {
        isFixed = fixed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name) &&
                Objects.equals(color, car.color) &&
                Objects.equals(productionDate, car.productionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, productionDate);
    }

    @Override
    public String toString() {
        return name + ", " + color + ", " + productionDate;
    }
}
