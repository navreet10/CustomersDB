package customersDB;

import java.util.List;
import java.util.Scanner;

public class CustomerApp {
	public static void main(String[] args) {
		CustomerDao dao = new CustomerDao();
		Scanner in = new Scanner(System.in);
		String response= "";
		while(!response.equals("Q")) {
			System.out.println("Please enter last name to search or press Q to exit:");
			response = in.next();
			List<Customer> list =dao.getRecordLastName(response);
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

}
