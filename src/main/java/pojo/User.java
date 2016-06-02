package pojo;

public class User {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	
	private class address{
		private String street;
		private String city;
		private int zip;
		private String state;
		private String country;
		
	}
	
	private address address = new address();
	
	private String dateCreated;
	private class company{
		private String name;
		private String website;
	}
	
	private company company = new company();
	
	private String profilePic;
	
	public String getId(){
		return this.id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getDateCreated(){
		return this.dateCreated;
	}
	
	public void setDateCreated(String dateCreated){
		this.dateCreated = dateCreated;
	}
	
	public String getProfilePic(){
		return this.profilePic;
	}
	
	public void setProfilePic(String profilePic){
		this.profilePic = profilePic;
	}
	
	public address getAddress(){
		return this.address;
	}
	
	public void setAddress(address address){
		this.address = address;
	}
	
	public void setAddress(String street, String city, int zip, String state, String country){
		this.address.street = street;
		this.address.city = city;
		this.address.zip = zip;
		this.address.state = state;
		this.address.country = country;
	}
	
	public company getCompany(){
		return this.company;
	}
	
	public void setCompany(company company){
		this.company = company;
	}
	
	public void setCompany(String name, String website){
		this.company.name = name;
		this.company.website = website;
	}

}
