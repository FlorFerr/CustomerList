package com.flor.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flor.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	//Inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//Get the hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//Create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class) ;
		
		//Execute query and get results
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		currentSession.delete(theCustomer);
		
	}

	@Override
	public List<Customer> searchCustomer(String searchName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = null;
		
		if(searchName != null && searchName.trim().length() > 0) {
			 theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
	         theQuery.setParameter("theName", "%" + searchName.toLowerCase() + "%");
		}else {
			theQuery =currentSession.createQuery("from Customer", Customer.class);          
		}
		
		List<Customer> customers = theQuery.getResultList();	
		
		return customers;
	}
}
