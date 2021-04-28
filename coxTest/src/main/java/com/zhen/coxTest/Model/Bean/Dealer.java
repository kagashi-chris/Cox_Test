package com.zhen.coxTest.Model.Bean;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private int dealerId;
    private String name;
    private List<Vehicle> vehicles = new ArrayList<>();

    public Dealer(){}

    public Dealer(int dealerId) {
        this.dealerId = dealerId;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "dealerId=" + dealerId +
                ", name='" + name + '\'' +
                ", vehicleList=" + vehicles +
                '}';
    }
}
