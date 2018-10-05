package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class User {
	 
	public String username;
	private String password;
	private String fullname;
	private String phone;
	private String address;
	dbConnection conn;
 
	
 
	public User(String fullname, String username2, String address, String phone, String password2) {
		// TODO Auto-generated constructor stub
	
		this.username = username2;
		this.fullname =fullname;
		this.phone = phone;
		this.address = address;
		this.password = password2;
	
	}

	
	public User(String username2, String password2) {
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
	        PreparedStatement ps = conn.prepare("INSERT INTO CustomerID,LargeBoxQuantity,MediumBoxQuantity,SmallBoxQuantity,"
	        		+ ",CustomerMessage,PreferredDeparture,EstimateArrival,BookingTime,Cost,ShipperID,Status"
	        		+ ",HBL"
	        		+ "VALUES ( ?, ?, ?,?, ?, ?,?, ?, ?,?, ?,?)");
	        ps.setString(1, username);
	 
	        //shipper ID
	        ps.setInt(10, 1);
	        //shipment status
	        ps.setInt(11,1);
	        ps.setString(12, "HBLGH234LJWEI224K42424243DKNKCKFF56");
	        int i = ps.executeUpdate();
	        
	        readDB();
	      if(i == 1) {
	        return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}

	private void readDB() throws SQLException {
		// TODO Auto-generated method stub
		@SuppressWarnings("static-access")
		PreparedStatement ps = conn.prepare("SELECT*FROM User");
		ResultSet rs = ps.executeQuery();
		
		System.out.println(rs);
	}
 
}