package com.bank.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	private int customer_id;
	
	private String customerName; 
	private String customerAddress;
	private String mobileNumber;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.MERGE, orphanRemoval = true)
	@JsonIgnoreProperties( value= {"application", "hibernateLazyInitializer"})
	@JsonIgnore
	private Account account;
	
	public Customer() {}
	public Customer(int customer_id, String customerNmae, String customerAddress, String mobileNumber) {
		this.customer_id = customer_id;
		this.customerName = customerNmae;
		this.customerAddress = customerAddress;
		this.mobileNumber = mobileNumber;  
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	

}
