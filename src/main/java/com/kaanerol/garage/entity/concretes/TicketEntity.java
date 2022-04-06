package com.kaanerol.garage.entity.concretes;

public class TicketEntity {
	private int id;
	private VehicleEntity vehicle;
	private int slot;

	public TicketEntity() {}

	public TicketEntity(int id, VehicleEntity vehicle, int slot) {
		this.id = id;
		this.vehicle = vehicle;
		this.slot = slot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public VehicleEntity getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleEntity vehicle) {
		this.vehicle = vehicle;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", vehicle=" + vehicle + ", slot=" + slot + "]";
	}

}
