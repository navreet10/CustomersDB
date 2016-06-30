package customersDB;

import java.util.List;
import java.util.Scanner;

public class CustomerApp {
	public static void main(String[] args) {
		CustomerDao dao = new CustomerDao();
		Scanner in = new Scanner(System.in);
		String response= "";
		while(!response.equals("Q")) {
			System.out.println("Please enter last name to search or enter total or enter Q to exit:");
			response = in.next();
			if (response.equals("total")) {
				System.out.println(dao.getTotal());
				in.nextLine();
				continue;
			}
			in.nextLine();
			List<Customer> list =dao.getRecordLastName(response);
			if (list.size() ==0) {
				System.out.println("User doesn't exist. Please enter details to add");
				Customer c = promptAdd(in);
				int rs = dao.addCustomer(c);
				if (rs ==1)
					System.out.println("Record Updated");
				else 
					System.out.println("No Records Updated");
				continue;
			}
			for (Customer c: list) {
				System.out.println(c.toString());
			}	
			System.out.println("Enter the id to be edited or (-1) to continue:");
			int id = in.nextInt();
			in.nextLine();
			if (id == -1)
				continue;
			if (!dao.ids.contains(id)) {
				System.out.println("This ID does not exist in the current list");
				continue;
			} else {
				System.out.println("Enter the new street Address");
				String address = in.nextLine();
				int rs = dao.updateAddress(id,address);
				if (rs ==1)
					System.out.println("Record Updated");
				else 
					System.out.println("No Records Updated");
			}
			
		}
		in.close();
		
	}

	private static Customer promptAdd(Scanner in) {
		Customer c = new Customer();
		System.out.println("Enter Title: ");
		c.setTitle(in.nextLine());
		System.out.println("Enter First Name: ");
		c.setFirstName(in.nextLine());
		System.out.println("Enter Last name: ");
		c.setLastName(in.nextLine());
		System.out.println("Enter Street Address: ");
		c.setStreetAddress(in.nextLine());
		System.out.println("Enter City: ");
		c.setCity(in.nextLine());
		System.out.println("Enter State: ");
		c.setState(in.nextLine());
		System.out.println("Enter Zip: ");
		c.setZip(in.nextLine());		
		System.out.println("Enter Email: ");
		c.setEmail(in.nextLine());
		System.out.println("Enter Company: ");
		c.setCompany(in.nextLine());
		System.out.println("Enter Position: ");
		c.setPosition(in.nextLine());
		
		
		return c;
	}

}
