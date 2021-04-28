package com.zhen.coxTest.Model.API;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class VehicleIdListResponse {


    @JsonProperty(value = "vehicleIds")
    private List<Integer> vehicleIds;

    public VehicleIdListResponse(){}

    public VehicleIdListResponse(List<Integer> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public List<Integer> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<Integer> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    @Override
    public String toString() {
        return "VehicleIdListResponse{" +
                "vehicleIds=" + vehicleIds +
                '}';
    }
}
