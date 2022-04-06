package com.kaanerol.garage.entity.concretes;

public class ParkingSlotEntity {
	private int id;
	private TicketEntity ticket;
	
	public ParkingSlotEntity() {}

	public ParkingSlotEntity(int id, TicketEntity ticket) {
		this.id = id;
		this.ticket = ticket;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TicketEntity getTicket() {
		return ticket;
	}

	public void setTicket(TicketEntity ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "ParkingSlot [id=" + id + ", ticket=" + ticket + "]";
	}
	
	

}
