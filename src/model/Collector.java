package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Collector {

	
	public String username;
	private String password;
	private String fullname;
	private String phone;
	private String address;
	dbConnection conn;
 
	
 
	public Collector(String fullname, String username2, String address, String phone, String password2) {
		// TODO Auto-generated constructor stub
	
		this.username = username2;
		this.fullname =fullname;
		this.phone = phone;
		this.address = address;
		this.password = password2;
	
	}

	
	public Collector(String username2, String password2) {
		// TODO Auto-generated constructor stub
		this.username = username2;
		this.password = password2;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getUsername() {
		return username;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean createRecord() {
		// TODO Auto-generated method stub
		return insertUser();
	}
	
	public boolean insertUser() {
	    
	    try {
	
	    	
	    	String[] names =fullname.split("\\s+");
//	        PreparedStatement ps = conn.prepare("INSERT INTO CustomerID,LargeBoxQuantity,MediumBoxQuantity,SmallBoxQuantity,"
//	        		+ ",CustomerMessage,PreferredDeparture,EstimateArrival,BookingTime,Cost,ShipperID,Status"
//	        		+ ",HBL"
//	        		+ "VALUES ( ?, ?, ?,?, ?, ?,?, ?, ?,?, ?,?)");
//	        ps.setString(1, username);
	    	PreparedStatement ps = conn.prepare("INSERT INTO customer (FullName, EmailAddress, Address, PhoneNUM, Password)"
	    			+ " VALUES ('" + fullname + "','" + username + "','" + address + "','" + phone + "','" + password + "');");
 
//	        //shipper ID
//	        ps.setInt(10, 1);
//	        //shipment status
//	        ps.setInt(11,1);
//	        ps.setString(12, "HBLGH234LJWEI224K42424243DKNKCKFF56");
	        int i = ps.executeUpdate();
	        //readDB();
	      if(i == 1) {
	        return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}

	public boolean queryUser(){
		boolean res = false;
		try {
			String sql = "SELECT CollectorID, EmailAddress, Password FROM Collector where EmailAddress='"
		+username+"' and Password='"+password + "'";
			PreparedStatement ps = conn.prepare(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				fullname = "Alexendra";//rs.getString(1);
				address = "123 Queen Street, Jakarta";
				phone = "0882222223";
				res = true;
			}
			else {
				//user doesn't exist
			}
			rs.close();
			//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				}
		return res;
	}
	
	private void readDB() throws SQLException {
		// TODO Auto-generated method stub
		@SuppressWarnings("static-access")
		PreparedStatement ps = conn.prepare("SELECT * FROM customer where EmailAddress='"+username+"' and Password='"+password);
		ResultSet rs = ps.executeQuery();
		
		System.out.println(rs);
	}
}
