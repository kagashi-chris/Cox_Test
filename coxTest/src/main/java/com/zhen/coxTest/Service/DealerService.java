package com.zhen.coxTest.Service;


import com.zhen.coxTest.Model.API.DatasetIdResponse;
import com.zhen.coxTest.Model.Bean.Dealer;
import com.zhen.coxTest.Model.Bean.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DealerService {

    @Autowired
    WebClient webClient;



    public Set<Integer> getDealerIds(List<Vehicle> vehicleList)
    {
        Set<Integer> dealerIds = new HashSet<>();
        for(Vehicle v:vehicleList)
        {
            dealerIds.add(v.getDealerId());
        }
        return dealerIds;
    }

    public Dealer getDealerInfo(Integer dealerId, DatasetIdResponse datasetId)
    {
        return webClient.get()
                .uri(String.format("/%s/dealers/%d", datasetId.getDatasetId(), dealerId))
                .retrieve()
                .bodyToMono(Dealer.class)
                .block();

    }

    public List<Dealer> getDealersInfo(Set<Integer> dealerIds, DatasetIdResponse datasetId)
    {
        List<Dealer> dealerList = new ArrayList<>();

        dealerIds.stream().parallel().forEach(object ->
        {
            dealerList.add(getDealerInfo(object.intValue(), datasetId));
        });
        return dealerList;
    }

}
