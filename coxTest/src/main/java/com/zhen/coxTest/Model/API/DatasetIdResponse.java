package com.zhen.coxTest.Model.API;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatasetIdResponse {


    @JsonProperty(value = "datasetId")
    private String datasetId;

    public DatasetIdResponse(){}

    public DatasetIdResponse(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    @Override
    public String toString() {
        return "DatasetIdResponse{" +
                "datasetId='" + datasetId + '\'' +
                '}';
    }
}
