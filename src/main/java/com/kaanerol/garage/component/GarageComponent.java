package com.kaanerol.garage.component;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.kaanerol.garage.entity.concretes.ParkingSlotEntity;

@Component
public class GarageComponent {
	private ArrayList<ParkingSlotEntity> slotList = new ArrayList<ParkingSlotEntity>();

	private int ticketId;

	public GarageComponent() {
		createSlotsOfGarage();
	}

	private void createSlotsOfGarage() {
		for (int i = 0; i < 10; i++) {
			slotList.add(new ParkingSlotEntity(i, null));
		}
	}

	public ArrayList<ParkingSlotEntity> getSlotList() {
		return slotList;
	}

	public int getTicketId() {
		return ticketId++;
	}

}
