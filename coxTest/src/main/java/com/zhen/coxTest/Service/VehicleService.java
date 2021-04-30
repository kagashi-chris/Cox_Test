package com.zhen.coxTest.Service;


import com.zhen.coxTest.Model.API.DatasetIdResponse;
import com.zhen.coxTest.Model.API.VehicleIdListResponse;
import com.zhen.coxTest.Model.Bean.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    WebClient webClient;


    //Takes in the DatasetIdResponse object and returns the VehicleIdListResponse

    public VehicleIdListResponse getVehicleIds(DatasetIdResponse datasetIdResponse)
    {

        return webClient.get()
                .uri(String.format("/%s/vehicles", datasetIdResponse.getDatasetId()))
                .retrieve()
                .bodyToMono(VehicleIdListResponse.class)
                .block();

    }

    //Takes in Integer vehicleId and the DatasetIdResponse and returns the Vehicle object

    public Vehicle getVehicleInfo(int vehicleId, DatasetIdResponse datasetIdResponse)
    {

        Mono<Vehicle> response = webClient.get()
                .uri(String.format("/%s/vehicles/%d", datasetIdResponse.getDatasetId(), vehicleId))
                .retrieve()
                .bodyToMono(Vehicle.class);

        return response.block();
    }

    //Takes in the list of vehicles and run the getVehicleInfo method on each of them in parallel and return a list
    //of vehicles (the reason this is run in parallel is because it takes 1-4 sec per request, so it'll be faster
    // to send in the request in parallel instead of waiting for each of them to finish before running the other)

    public List<Vehicle> vehicleList(List<Integer> VehicleIdListResponse, DatasetIdResponse DatasetIdResponse)
    {
        List<Vehicle> vehicleList = new ArrayList<>();

        VehicleIdListResponse.stream().parallel().forEach(object ->
        {
            vehicleList.add(getVehicleInfo(object.intValue(),DatasetIdResponse));
        });

        return vehicleList;
    }

}
