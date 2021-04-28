package com.zhen.coxTest.Model.API;

import com.zhen.coxTest.Model.Bean.Dealer;

import java.util.List;

public class DealersAndVehiclesForDatasetRequest {

    private List<Dealer> dealers;

    public DealersAndVehiclesForDatasetRequest() {
    }

    public List<Dealer> getDealers() {
        return dealers;
    }

    public void setDealers(List<Dealer> dealers) {
        this.dealers = dealers;
    }

    @Override
    public String toString() {
        return "DealersAndVehiclesForDatasetResponse{" +
                "dealers=" + dealers +
                '}';
    }
}


