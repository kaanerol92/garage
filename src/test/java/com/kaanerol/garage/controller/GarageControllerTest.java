package com.kaanerol.garage.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaanerol.garage.entity.concretes.VehicleEntity;

@SpringBootTest
@AutoConfigureMockMvc
class GarageControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private String baseUrl = "/garage";

	@Test
	public void whenCarParkThenSuccess() throws Exception {
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));
		VehicleEntity vehicleCar = new VehicleEntity("34-VF-TEST", "blue", "car");
		mockMvc.perform(post(baseUrl + "/park").content(objectMapper.writeValueAsString(vehicleCar))
				.accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().string(containsString("Allocated 1 slot")));
	}

	@Test
	public void whenJeepParkThenSuccess() throws Exception {
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));
		VehicleEntity vehicleCar = new VehicleEntity("34-VF-TEST", "blue", "jeep");
		mockMvc.perform(post(baseUrl + "/park").content(objectMapper.writeValueAsString(vehicleCar))
				.accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().string(containsString("Allocated 2 slots")));
	}

	@Test
	public void whenTruckParkThenSuccess() throws Exception {
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));
		VehicleEntity vehicleCar = new VehicleEntity("34-VF-TEST", "blue", "truck");
		mockMvc.perform(post(baseUrl + "/park").content(objectMapper.writeValueAsString(vehicleCar))
				.accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().string(containsString("Allocated 4 slots")));
	}

	@Test
	public void whenStatusThenSuccess() throws Exception {
		mockMvc.perform(get(baseUrl + "/status")).andExpect(status().isOk());
	}

	@Test
	public void whenNotFoundTicketThenError() throws Exception {
		mockMvc.perform(get(baseUrl + "/leave/15")).andExpect(status().isBadRequest()).andExpect(content().string(containsString("Ticket not found with given id : 15")));
	}

}
