package model;

public class Authenticator {

	
	String role;
	String inputUsername;
	String inputPassword;
	
	public String authenticate(String username, String password) {
		String result = "failure";
		String success = "success";
		this.inputPassword = password;
		this.inputUsername = username;
		
		boolean validUser= false;
		boolean validShipper = false;
		boolean valideCollector = false;
		
	
		
		if(validUser = checkDetailsOfShipper()) {this.role ="shipper";return success;}		
		else if(validUser = checkDetailsOfCollector()) {this.role ="collector";return success;}
		else if(validUser = checkDetailsOfUser()) {this.role ="customer";return success;}
		else {return result;}
		
	}

	private boolean checkDetailsOfCollector() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean checkDetailsOfShipper() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getFullname() {
		// TODO Auto-generated method stub
		String fullname ="sharukh";
		return fullname;
	}
	
	public String getRole() {
		return role;
	}
	
	private boolean checkDetailsOfUser() {
		boolean userValid =false;
		User newLoginUser = new User(inputUsername, inputPassword);
		userValid = newLoginUser.queryUser();
		if(userValid) {	}
		return userValid;
		
	}
}