package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import customersDB.Customer;
import customersDB.CustomerDao;

public class CustomerDBTest {

	@Test
	public void testInsert() {
		CustomerDao dao =  new CustomerDao();
		List <Customer> list = dao.getRecordLastName("Kaur");
		assertTrue(list.get(0).getLastName().equals("Kaur"));
	}
	
	@Test
	public void testEdit() {
		CustomerDao dao =  new CustomerDao();
		int res = dao.updateAddress(4068, "Halloween Town");
		assertTrue(res == 1);
		List <Customer> list = dao.getRecordLastName("Kaur");
		assertTrue(list.get(0).getStreetAddress().equals("Halloween Town"));
		
	}
	
	@Test
	public void testRetrieve() {
		CustomerDao dao =  new CustomerDao();
		Customer c = new Customer(4069, "Mr", "Hullu", "Vullu", "Halluvi rd", 
				"Pink", "TX", "89864", "vullu@email.com", "Santa House", "Chief");
		int res = dao.addCustomer(c);
		assertTrue(res == 1);
		List <Customer> list = dao.getRecordLastName("Vullu");
		assertTrue(list.get(0).getStreetAddress().equals("Halluvi rd"));
		
	}

}
