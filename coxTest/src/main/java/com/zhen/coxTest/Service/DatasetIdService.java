package com.zhen.coxTest.Service;

import com.zhen.coxTest.Model.API.DatasetIdResponse;
import com.zhen.coxTest.Model.API.DealersAndVehiclesForDatasetRequest;
import com.zhen.coxTest.Model.API.DealersAndVehiclesForDatasetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class DatasetIdService {

    @Autowired
    WebClient webClient;

    public DatasetIdResponse getClient()
    {
        return webClient.get()
                .uri("/datasetId")
                .retrieve()
                .bodyToMono(DatasetIdResponse.class)
                .block();
    }

    public DealersAndVehiclesForDatasetResponse postAnswer(DatasetIdResponse datasetIdResponse, DealersAndVehiclesForDatasetRequest dealersAndVehiclesForDatasetRequest)
    {
        return webClient.post()
                .uri(String.format("/%s/answer", datasetIdResponse.getDatasetId()))
                .body(Mono.just(dealersAndVehiclesForDatasetRequest), DealersAndVehiclesForDatasetRequest.class)
                .retrieve()
                .bodyToMono(DealersAndVehiclesForDatasetResponse.class)
                .block();
    }

}
