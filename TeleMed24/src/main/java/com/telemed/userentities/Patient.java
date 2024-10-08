package com.telemed.userentities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.telemed.appointmententity.Appointment;
import com.telemed.storeentity.Cart;

@Component
public class Patient extends User{
	private Date dob;
	Cart cart;
	List<Cart> pastOrders=new ArrayList<>();
	
	public Patient(Date dob, Cart cart, List<Cart> pastOrders) {
		super();
		this.dob = dob;
		this.cart = cart;
		this.pastOrders = pastOrders;
	}
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Patient(int id, String name, String email, String phoneNo, String city, Appointment appointment) {
		super(id, name, email, phoneNo, city, appointment);
		// TODO Auto-generated constructor stub
	}
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public List<Cart> getPastOrders() {
		return pastOrders;
	}
	public void setPastOrders(Cart pastOrder) {
		this.pastOrders.add(pastOrder);
	}
	
	@Override
	public String toString() {
		return "Patient [dob=" + dob + ", id=" + id + ", name=" + name + ", email=" + email + ", phoneNo=" + phoneNo
				+ ", city=" + city + ", appointments=" + appointments + "]";
	}
	
	
	
	
}
