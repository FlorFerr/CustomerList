package com.flor.springdemo.dao;

import java.util.List;

import com.flor.springdemo.entity.Customer;

public interface CustomerDAO {
	
	public  List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

}