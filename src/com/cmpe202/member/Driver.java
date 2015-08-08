package com.cmpe202.member;

public class Driver extends Member {

	private String driver_type;
	private String driver_location;
	private int vehicleId;
	private String driverstatus;

	public String getDriverstatus() {
		return driverstatus;
	}

	public void setDriverstatus(String driverstatus) {
		this.driverstatus = driverstatus;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getDriver_type() {
		return driver_type;
	}

	public void setDriver_type(String driver_type) {
		this.driver_type = driver_type;
	}

	public String getDriver_location() {
		return driver_location;
	}

	public void setDriver_location(String driver_location) {
		this.driver_location = driver_location;
	}
}
