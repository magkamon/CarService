package org.example.formObjects;

public class CarForm {

    private String name;

    private String color;

    private String productionDate;

    private Boolean isFixed;

    public CarForm(){}

    public CarForm(String name, String color, String productionDate, Boolean isFixed) {
        this.name = name;
        this.color = color;
        this.productionDate = productionDate;
        this.isFixed = isFixed;
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
}
