package org.example.domain;

public class VehiclePart {
    private String type;
    private String name;
    private int repairCost;
    private boolean isBroken;

    public VehiclePart(String type, String name, int repairCost, boolean isBroken) {
        this.type = type;
        this.name = name;
        this.repairCost = repairCost;
        this.isBroken = isBroken;
    }

    public VehiclePart() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(int repairCost) {
        this.repairCost = repairCost;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setIsBroken(boolean isBroken) {
        this.isBroken = isBroken;
    }

}
