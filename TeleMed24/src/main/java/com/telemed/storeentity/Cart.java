package com.telemed.storeentity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Cart {
	private int id;
	private int patientId;
	private int totalNoOfItems;
	private float totalCost;
	
	List<Product> products;
	
	public Cart() {
		super();
		products=new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	public Cart(int id, int patientId, int totalNoOfItems, float totalCost, Product product) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.totalNoOfItems = totalNoOfItems;
		this.totalCost = totalCost;
		this.products.add(product);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getTotalNoOfItems() {
		return totalNoOfItems;
	}

	public void setTotalNoOfItems(int totalNoOfItems) {
		this.totalNoOfItems = totalNoOfItems;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	
}
