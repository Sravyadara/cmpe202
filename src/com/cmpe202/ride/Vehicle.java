package com.cmpe202.ride;

public class Vehicle {

	private String vehicleType;
	private int noOfSeats;
	private int vehicleId;
	private String vehicle_spec;
	private String vehicle_model;

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicle_spec() {
		return vehicle_spec;
	}

	public void setVehicle_spec(String vehicle_spec) {
		this.vehicle_spec = vehicle_spec;
	}

	public String getVehicle_model() {
		return vehicle_model;
	}

	public void setVehicle_model(String vehicle_model) {
		this.vehicle_model = vehicle_model;
	}
}