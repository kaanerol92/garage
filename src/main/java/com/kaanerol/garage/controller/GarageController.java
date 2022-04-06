package com.kaanerol.garage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaanerol.garage.entity.concretes.VehicleEntity;
import com.kaanerol.garage.service.abstracts.GarageService;

@RestController
@RequestMapping("/garage")
public class GarageController {

	@Autowired
	GarageService garageService;

	@PostMapping("/park")
	public ResponseEntity<String> park(@RequestBody VehicleEntity vehicle) {
		return ResponseEntity.ok(garageService.park(vehicle));
	}

	@GetMapping("/leave/{ticketId}")
	public ResponseEntity<String> leave(@PathVariable(value = "ticketId") int ticketId) {
		garageService.leave(ticketId);
		return ResponseEntity.ok("Have a good day!");
	}

	@GetMapping("/status")
	public ResponseEntity<String> status() {
		return ResponseEntity.ok(garageService.status());
	}

}
