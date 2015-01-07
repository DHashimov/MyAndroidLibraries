package com.deniz.server;

import javax.validation.constraints.Pattern;

public class Address {

	@Pattern(
			regexp = "[^0-9]*")
	private String country;
	@Pattern(
			regexp = "[^0-9]*")
	private String city;

	private String street;
	@Pattern(
			regexp = "[0-9]*")
	private int pincode;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

}
