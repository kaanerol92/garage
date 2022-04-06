package com.kaanerol.garage.service.concretes;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaanerol.garage.component.GarageComponent;
import com.kaanerol.garage.entity.concretes.ParkingSlotEntity;
import com.kaanerol.garage.entity.concretes.TicketEntity;
import com.kaanerol.garage.entity.concretes.VehicleEntity;
import com.kaanerol.garage.enumeration.VehicleSizeEnum;
import com.kaanerol.garage.exception.SizeNotFoundException;
import com.kaanerol.garage.exception.TicketNotFoundException;
import com.kaanerol.garage.service.abstracts.GarageService;

@Service
public class GarageManager implements GarageService {

	@Autowired
	private GarageComponent garage;

	@Override
	public String park(VehicleEntity vehicle) {

		if (vehicle.getType() == null)
			throw new SizeNotFoundException("No Size for Unknown Type");

		int sizeOfVehicle = VehicleSizeEnum.getSize(vehicle.getSize());

		int startingCountOfFreeSlot = -1;
		for (int currentIndex = 0; currentIndex < getSlotList().size(); currentIndex++) {
			if (!slotHasTicket(currentIndex)) {
				if (rearSlotHasTicket(currentIndex))
					continue;

				if (startingCountOfFreeSlot == -1) {
					startingCountOfFreeSlot = currentIndex;
					if (isLastAndHasFreeSpace(currentIndex, startingCountOfFreeSlot, sizeOfVehicle))
						return createTicketAndFillSlots(garage.getTicketId(), vehicle, startingCountOfFreeSlot,
								currentIndex + 1);
				}

				else if (hasEnoughSizeUntilThisIndex(currentIndex, startingCountOfFreeSlot, sizeOfVehicle)) {
					return createTicketAndFillSlots(garage.getTicketId(), vehicle, startingCountOfFreeSlot,
							currentIndex);
				}
			} else {
				startingCountOfFreeSlot = -1;
			}

		}

		return "Garage is Full";
	}

	@Override
	public void leave(int ticketId) {

		boolean isRemoved = false;
		for (int currentIndex = 0; currentIndex < getSlotList().size(); currentIndex++) {
			if (isTicketMatching(currentIndex, ticketId)) {
				getSlotList().get(currentIndex).setTicket(null);
				isRemoved = true;
			}
		}
		if (!isRemoved)
			throw new TicketNotFoundException("Ticket not found with given id : " + ticketId);
	}

	@Override
	public String status() {

		Hashtable<VehicleEntity, List<Integer>> returnMap = new Hashtable();
		for (int currentIndex = 0; currentIndex < getSlotList().size(); currentIndex++) {
			if (slotHasTicket(currentIndex)) {
				List<Integer> vehicleSlotList = returnMap.get(getSlotList().get(currentIndex).getTicket().getVehicle());
				if (vehicleSlotList == null) {
					vehicleSlotList = new ArrayList();
					returnMap.put(getSlotList().get(currentIndex).getTicket().getVehicle(), vehicleSlotList);
				}
				vehicleSlotList.add(currentIndex);
			}
		}
		return returnMap.toString();
	}

	private String createTicketAndFillSlots(int id, VehicleEntity vehicle, int slot, int lastIndex) {

		int sizeOfVehicle = VehicleSizeEnum.getSize(vehicle.getSize());
		TicketEntity ticket = new TicketEntity(id, vehicle, slot);
		for (int slotIndex = slot; slotIndex < lastIndex; slotIndex++) {
			getSlotList().get(slotIndex).setTicket(ticket);
		}
		return "Allocated " + sizeOfVehicle + " slot" + (sizeOfVehicle == 1 ? " \n" : "s \n") + ticket.toString();
	}

	public ArrayList<ParkingSlotEntity> getSlotList() {
		return garage.getSlotList();
	}

	private boolean slotHasTicket(int currentIndex) {
		return getSlotList().get(currentIndex).getTicket() != null;
	}

	private boolean rearSlotHasTicket(int currentIndex) {
		return currentIndex > 0 && getSlotList().get(currentIndex - 1).getTicket() != null;
	}

	private boolean isLastAndHasFreeSpace(int currentIndex, int startingCountOfFreeSlot, int sizeOfVehicle) {
		return currentIndex == getSlotList().size() - 1 && currentIndex + 1 - startingCountOfFreeSlot == sizeOfVehicle;
	}

	private boolean hasEnoughSizeUntilThisIndex(int currentIndex, int startingCountOfFreeSlot, int sizeOfVehicle) {
		return currentIndex - startingCountOfFreeSlot == sizeOfVehicle;
	}

	private boolean isTicketMatching(int currentIndex, int ticketId) {
		return getSlotList().get(currentIndex).getTicket() != null
				&& getSlotList().get(currentIndex).getTicket().getId() == ticketId;
	}

}
