package com.kaanerol.garage.enumeration;

import com.kaanerol.garage.exception.SizeNotFoundException;

public enum VehicleSizeEnum {
	S,M,L;
	
	public static int getSize(VehicleSizeEnum size) {
		switch (size) {
		case S:
			return 1;
		case M:
			return 2;
		case L:
			return 4;
		default:
			throw new SizeNotFoundException("Size not found with given size type : " + size);
		}
	}
}
