package model;

public class Authenticator {

	public String authenticate(String username, String password) {
		if (("spmuser".equalsIgnoreCase(username))
				&& ("password".equals(password))) {
			return "success";
		} else {
			return "failure";
		}
	}
}