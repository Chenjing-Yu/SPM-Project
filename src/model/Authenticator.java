package model;

public class Authenticator {

	public String authenticate(String username, String password) {
		if (("spmuser@gmail.com".equalsIgnoreCase(username))
				&& ("password".equals(password))) {
			return "success";
		} else {
			return "failure";
		}
	}
}