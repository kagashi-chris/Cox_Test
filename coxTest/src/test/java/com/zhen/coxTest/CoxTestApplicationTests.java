package com.zhen.coxTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhen.coxTest.Model.API.DatasetIdResponse;
import com.zhen.coxTest.Model.API.DealersAndVehiclesForDatasetRequest;
import com.zhen.coxTest.Model.API.DealersAndVehiclesForDatasetResponse;
import com.zhen.coxTest.Model.API.VehicleIdListResponse;
import com.zhen.coxTest.Model.Bean.Dealer;
import com.zhen.coxTest.Model.Bean.Vehicle;
import com.zhen.coxTest.Service.DatasetIdService;
import com.zhen.coxTest.Service.DealerService;
import com.zhen.coxTest.Service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class CoxTestApplicationTests {

	DatasetIdResponse datasetId;


	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	WebClient webClient;

	@Autowired
	DatasetIdService datasetIdService;

	@Autowired
	VehicleService vehicleService;

	@Autowired
	DealerService dealerService;

	@Test
	void contextLoads() {
	}

	@Test
	public void getClient()
	{
		this.datasetId = datasetIdService.getClient();
		System.out.println(datasetId.getDatasetId());
		VehicleIdListResponse vehicleIdList = vehicleService.getVehicleIds(this.datasetId);
		System.out.println(vehicleIdList.getVehicleIds());
		List<Vehicle> vehicleList = vehicleService.vehicleList(vehicleIdList.getVehicleIds(), datasetId);
		for (Vehicle v:vehicleList)
		{
			System.out.println(v.getVehicleId());
			System.out.println(v.getDealerId());
			System.out.println(v.getMake());
			System.out.println(v.getModel());
			System.out.println(v.getYear());
		}
		Set<Integer> dealerIds = dealerService.getDealerIds(vehicleList);
		System.out.println(dealerIds);
		List<Dealer> dealerList = dealerService.getDealersInfo(dealerIds, datasetId);
		Map<Integer, Dealer> dealerMap = new HashMap<>();
		for (Dealer dealer: dealerList)
		{
			dealerMap.put(dealer.getDealerId(), dealer);
			System.out.println(dealer.getDealerId());
			System.out.println(dealer.getName());
		}

		DealersAndVehiclesForDatasetRequest dealersAndVehiclesForDatasetRequest = new DealersAndVehiclesForDatasetRequest();

		for(Vehicle v: vehicleList)
		{
			dealerMap.get(v.getDealerId()).getVehicles().add(v);
		}
		dealersAndVehiclesForDatasetRequest.setDealers(dealerList);
		System.out.println(dealersAndVehiclesForDatasetRequest);
		try {
			System.out.println(objectMapper.writeValueAsString(dealersAndVehiclesForDatasetRequest));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		DealersAndVehiclesForDatasetResponse dealersAndVehiclesForDatasetResponse = datasetIdService.postAnswer(datasetId, dealersAndVehiclesForDatasetRequest);
		System.out.println(dealersAndVehiclesForDatasetResponse);
		try {
			System.out.println(objectMapper.writeValueAsString(dealersAndVehiclesForDatasetResponse));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}



}
