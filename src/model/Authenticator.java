package model;

public class Authenticator {

	
	String role;
	String inputUsername;
	String inputPassword;
	String fullname;
	
	public String authenticate(String username, String password) {
		String result = "failure";
		String success = "success";
		this.inputPassword = password;
		this.inputUsername = username;
		
	
		boolean validUser= false;
		
		
	
		
		if( checkDetailsOfShipper()) {validUser =true; this.role ="shipper";return success;}		
		else if( checkDetailsOfCollector()) {validUser =true; this.role ="collector";return success;}
		else if( checkDetailsOfUser()) {validUser =true; this.role ="customer";return success;}
		else {return result;}
		
	}


	public String getFullname() {
		// TODO Auto-generated method stub
		return fullname;
	}
	
	public String getRole() {
		return role;
	}
	
	private boolean checkDetailsOfUser() {
		boolean userValid =false;
		User newLoginUser = new User(inputUsername, inputPassword);
		userValid = newLoginUser.queryUser();
		System.out.println("query in authenticator"+userValid);
		this.fullname = newLoginUser.getFullname();
		return userValid;
		
	}
	private boolean checkDetailsOfShipper() {
		boolean userValid =false;
		Shipper newLoginUser = new Shipper(inputUsername, inputPassword);
		userValid = newLoginUser.queryUser();
		this.fullname = newLoginUser.getFullname();
		return userValid;
		
	}
	private boolean checkDetailsOfCollector() {
		boolean userValid =false;
		Collector newLoginUser = new Collector(inputUsername, inputPassword);
		userValid = newLoginUser.queryUser();
		this.fullname = newLoginUser.getFullname();
		return userValid;
		
	}
}