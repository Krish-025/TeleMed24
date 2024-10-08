package com.telemed.storeentity;

public class Medicine implements Product {
	private int id;
	private float price;
	private String about;
	public Medicine(int id, float price, String about) {
		super();
		this.id = id;
		this.price = price;
		this.about = about;
	}
	public Medicine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	@Override
	public String toString() {
		return "Medicine [id=" + id + ", price=" + price + ", about=" + about + "]";
	}
	
	
}
