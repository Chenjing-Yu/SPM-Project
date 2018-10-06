package model;

public class Authenticator {

	public String authenticate(String username, String password) {
		if (("syedm1@student.unimelb.edu.au".equalsIgnoreCase(username))
				&& ("password".equals(password))) {
			return "success";
		} else {
			return "failure";
		}
	}

	public String getFullname() {
		// TODO Auto-generated method stub
		String fullname ="sharukh";
		return fullname;
	}
}