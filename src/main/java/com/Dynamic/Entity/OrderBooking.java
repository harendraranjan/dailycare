package com.Dynamic.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String date;
	private String phone;
	private String specialRequest;
	
	
	
	public OrderBooking(int id, String name, String email, String date, String phone, String specialRequest) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.date = date;
		this.phone = phone;
		this.specialRequest = specialRequest;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSpecialRequest() {
		return specialRequest;
	}
	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}
	
	
	

}
