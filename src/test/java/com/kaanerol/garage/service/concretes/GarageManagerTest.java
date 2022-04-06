package com.kaanerol.garage.service.concretes;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kaanerol.garage.component.GarageComponent;
import com.kaanerol.garage.entity.concretes.ParkingSlotEntity;
import com.kaanerol.garage.entity.concretes.VehicleEntity;
import com.kaanerol.garage.exception.SizeNotFoundException;
import com.kaanerol.garage.exception.TicketNotFoundException;

@ExtendWith(MockitoExtension.class)
class GarageManagerTest {

	@InjectMocks
	private GarageManager garageManager;

	@Mock
	private GarageComponent garage;

	@Test
	void whenNoTypeVehicleParkThenError() {
		VehicleEntity vehicle = new VehicleEntity();
		vehicle.setPlate("34-VF-TEST");
		vehicle.setColor("blue");
		assertThrowsExactly(SizeNotFoundException.class, () -> garageManager.park(vehicle));
	}

	@Test
	void whenUnknownTypeVehicleParkThenError() {
		VehicleEntity vehicle = new VehicleEntity("34-VF-TEST", "blue", "unknown");
		assertThrowsExactly(SizeNotFoundException.class, () -> garageManager.park(vehicle));
	}

	@Test
	void whenLastSlotThenSuccess() {
		ArrayList<ParkingSlotEntity> slotList = createSlotList();
		VehicleEntity vehicleJeep = new VehicleEntity("34-VF-TEST", "blue", "jeep");
		when(garageManager.getSlotList()).thenReturn(slotList);
		garageManager.park(vehicleJeep);
		garageManager.park(vehicleJeep);
		garageManager.park(vehicleJeep);
		VehicleEntity vehicleCar = new VehicleEntity("34-VF-TEST", "blue", "car");
		String result = garageManager.park(vehicleCar);
		assertTrue(result.contains("slot=9"));
	}

	@Test
	void whenCarTypeVehicleParkThenSuccess() {
		ArrayList<ParkingSlotEntity> slotList = createSlotList();
		VehicleEntity vehicle = new VehicleEntity("34-VF-TEST", "blue", "car");
		when(garageManager.getSlotList()).thenReturn(slotList);
		String result = garageManager.park(vehicle);
		assertTrue(result.contains("Allocated 1 slot"));
	}

	@Test
	void whenJeepTypeVehicleParkThenSuccess() {
		ArrayList<ParkingSlotEntity> slotList = createSlotList();
		VehicleEntity vehicle = new VehicleEntity("34-VF-TEST", "blue", "jeep");
		when(garageManager.getSlotList()).thenReturn(slotList);
		String result = garageManager.park(vehicle);
		assertTrue(result.contains("Allocated 2 slots"));
	}

	@Test
	void whenTruckTypeVehicleParkThenSuccess() {
		ArrayList<ParkingSlotEntity> slotList = createSlotList();
		VehicleEntity vehicle = new VehicleEntity("34-VF-TEST", "blue", "truck");
		when(garageManager.getSlotList()).thenReturn(slotList);
		String result = garageManager.park(vehicle);
		assertTrue(result.contains("Allocated 4 slots"));
	}

	@Test
	void whenGarageIsFullThenMessage() {
		ArrayList<ParkingSlotEntity> slotList = createSlotList();
		VehicleEntity vehicle = new VehicleEntity("34-VF-TEST", "blue", "truck");
		when(garageManager.getSlotList()).thenReturn(slotList);
		String result = garageManager.park(vehicle); // Allocated 4
		assertTrue(result.contains("Allocated 4 slots"));
		result = garageManager.park(vehicle); // Allocated 4
		assertTrue(result.contains("Allocated 4 slots"));
		result = garageManager.park(vehicle); // Garage Is Full
		assertTrue(result.contains("Garage is Full"));
	}

	@Test
	void whenStatusThenMessage() {
		ArrayList<ParkingSlotEntity> slotList = createSlotList();
		when(garageManager.getSlotList()).thenReturn(slotList);
		VehicleEntity vehicleTruck = new VehicleEntity("34-VF-TEST", "blue", "truck");
		garageManager.park(vehicleTruck);
		VehicleEntity vehicleJeep = new VehicleEntity("34-VF-TEST", "blue", "jeep");
		garageManager.park(vehicleJeep);
		VehicleEntity vehicleCar = new VehicleEntity("34-VF-TEST", "blue", "car");
		garageManager.park(vehicleCar);
		String result = garageManager.status();
		assertNotEquals("{}", result);
	}

	@Test
	void whenLeaveWithTickedIdThenSuccess() {
		ArrayList<ParkingSlotEntity> slotList = createSlotList();
		when(garageManager.getSlotList()).thenReturn(slotList);
		VehicleEntity vehicleCar = new VehicleEntity("34-VF-TEST", "blue", "car");
		garageManager.park(vehicleCar);
		garageManager.leave(0);
		assertNull(slotList.get(0).getTicket());
	}

	@Test
	void whenNotFoundTicketIdToLeaveThenError() {
		ArrayList<ParkingSlotEntity> slotList = createSlotList();
		when(garageManager.getSlotList()).thenReturn(slotList);
		assertThrowsExactly(TicketNotFoundException.class, () -> garageManager.leave(2));
	}

	private ArrayList<ParkingSlotEntity> createSlotList() {
		ArrayList<ParkingSlotEntity> slotList = new ArrayList<ParkingSlotEntity>();
		for (int i = 0; i < 10; i++) {
			slotList.add(new ParkingSlotEntity(i, null));
		}
		return slotList;
	}

}
