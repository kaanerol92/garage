package com.kaanerol.garage.entity.concretes;

import com.kaanerol.garage.enumeration.VehicleSizeEnum;
import com.kaanerol.garage.exception.SizeNotFoundException;

public class VehicleEntity {

	private String plate;
	private String color;
	private String type;
	
	public VehicleEntity() {}

	public VehicleEntity(String plate, String color, String type) {
		this.plate = plate;
		this.color = color;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Vehicle [plate=" + plate + ", color=" + color + ", type=" + type + "]";
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public VehicleSizeEnum getSize() {
		switch (type.toLowerCase()) {
		case "car":
			return VehicleSizeEnum.S;
		case "jeep":
			return VehicleSizeEnum.M;
		case "truck":
			return VehicleSizeEnum.L;
		default:
			throw new SizeNotFoundException("Size not found with given type : " + type);
		}
	}

}
