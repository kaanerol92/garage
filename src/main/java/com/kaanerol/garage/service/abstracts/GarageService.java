package com.kaanerol.garage.service.abstracts;

import com.kaanerol.garage.entity.concretes.VehicleEntity;

public interface GarageService {

	String status();

	String park(VehicleEntity vehicle);

	void leave(int ticketId);

}
