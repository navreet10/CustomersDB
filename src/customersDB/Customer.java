package customersDB;

public class Customer {
	private int id;
	private String title;
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String email;
	private String company;
	private String position;
	public Customer() {
		
	}
	
	public Customer(int id, String title, String firstName, String lastName,
			String streetAddress, String city, String state, String zip,
			String email, String company, String position) {
		this.id =id;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.email = email;
		this.company = company;
		this.position = position;
		
	}
	
	//setters and getters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return (" ID: " + this.id+ " - " + this.title + " " + this.firstName 
				+ " " + this.lastName + "\n"
				+ this.streetAddress + ",\n"
				+ this.city + ", "+ this.state + "-" +this.zip
				+ "\n" + this.position + " at " + this.company+ "\n");
		
	}
	
	

}
