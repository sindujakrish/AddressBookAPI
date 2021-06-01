package com.reecetech.AddressBook.model;

import java.util.Objects;

public class Contact {

	private String id;
	
	private String name;
	private long phoneNumber;
	
	public Contact(String name, long phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public Contact() {
		
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
        if (obj == null || getClass() != obj.getClass()) {
        	return false;
        }
        
		Contact ct = (Contact)obj;
		System.out.println(ct.getName());
		System.out.println(this.getName());
		System.out.println(ct.getPhoneNumber());
		System.out.println(this.getPhoneNumber());
		if((ct.getName().equals(this.getName())) && (ct.getPhoneNumber() == this.getPhoneNumber())) {
			return true;
		}
		return false;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber);
    }
	
}
