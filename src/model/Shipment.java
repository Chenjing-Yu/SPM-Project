package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Shipment {

	/*
	 * prices of boxes
	 */
	
	
	public static int costOfSmallBox =5;
	public static int costOfMedBox =15;
	public static int costOfLrgBox =25;
	String quantity ;
	String address ;
	Date departureDate;
	Date arrivalDate ;
	String message;
	String orderId;
	String collectorID;
	String shipmentID;
	String customerID;
	int medBoxes;
	int smallBoxes;
	int largeBoxes;
	dbConnection conn;
	
	
	public Shipment(String quantity2, String address2, Date departureDate2, Date arrivalDate2, String message2, String CustomerID) {
		// TODO Auto-generated constructor stub
		this.quantity = quantity2;
		this.address = address2;
		this.departureDate =departureDate2;
		this.arrivalDate = arrivalDate2;
		this.message = message2;
		
		this.collectorID = "C1";
		this.customerID = CustomerID;
	}
	
	/**
	 * 
	 * Model class for Shipper table in database
	 * 
	 * @return
	 */
	
	
	public String getCollectorID() {
		return collectorID;
	}
	public void setCollectorID(String collectorID) {
		this.collectorID = collectorID;
	}
	public String getShipmentID() {
		return shipmentID;
	}
	public void setShipmentID(String shipmentID) {
		this.shipmentID = shipmentID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public int getMedBoxes() {
		return medBoxes;
	}
	public void setMedBoxes(int medBoxes) {
		this.medBoxes = medBoxes;
	}
	public int getSmallBoxes() {
		return smallBoxes;
	}
	public void setSmallBoxes(int smallBoxes) {
		this.smallBoxes = smallBoxes;
	}
	public int getLargeBoxes() {
		return largeBoxes;
	}
	public void setLargeBoxes(int largeBoxes) {
		this.largeBoxes = largeBoxes;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public boolean createRecord() throws SQLException {
		
		boolean result= insertUser();
		return result;
	}
	public BigDecimal  getCost() {	return new BigDecimal( Integer.parseInt(quantity)*costOfSmallBox);	}
	
	

public boolean insertUser() {
    
    try {
    	/*
    	 * 	String quantity ;
			String address ;
			String departureDate;
			String arrivalDate ;
			String message;
			String orderId;
			String collectorID;
			String shipmentID;
			String customerID;
			int medBoxes;
			int smallBoxes;
			int largeBoxes;
			dbConnection conn;
    	 */
        PreparedStatement ps = conn.prepare("INSERT INTO user VALUES (NULL, ?, ?, ?)");
        ps.setString(2, customerID);
        ps.setInt(3, largeBoxes);
        ps.setInt(4, medBoxes);
        ps.setInt(5, smallBoxes );
        ps.setString(6, message);
        ps.setDate(7, departureDate);
        /*
         * one day has 86400000 milliseconds
         * multiply 5 for estimate of 5 days
         */
        Date estimatedDate = new Date(new java.util.Date().getTime() + (86400000*5));
        ps.setDate(8, estimatedDate);
        ps.setTimestamp(9,new Timestamp(System.currentTimeMillis()));
        ps.setBigDecimal(10, getCost());
        ps.setInt(11, 1);
        ps.setInt(12,1);
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
	PreparedStatement ps = conn.prepare("SELECT*FROM Shipping");
	ResultSet rs = ps.executeQuery();
	
	System.out.println(rs);
}
}