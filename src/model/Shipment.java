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
	
	
	public static int costOfSmallBox =35;
	public static int costOfMedBox =55;
	public static int costOfLrgBox =85;
	
	/*
	 * Table fields for Shipment table in database
	 */
	String orderId;
	String customerID;
	String bookingTime;
	String quantity ;
	Date departureDate;
	Date arrivalDate;
	String address ;//delivery address
	String pickupAddress;
	String message;
	String collectorID;
	String customerName;
	String status;
	String cost;
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
		
		this.collectorID = "1";
		this.customerID = CustomerID;
	}
	
	public Shipment(String orderId) {
		this.orderId = orderId;
	}
	
	public Shipment() {}
	
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
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
		this.cost = String.valueOf(Integer.parseInt(quantity)*costOfSmallBox);
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPickupAddress() {
		return address;
	}
	public void setPickupAddress(String address) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}
	public String getBookingTime() {
		return this.bookingTime;
	}
	
	public boolean createRecord() throws SQLException {
		
		boolean result= insertUserShipment();
		return result;
	}
	public BigDecimal  getCost() {	return new BigDecimal( Integer.parseInt(quantity)*costOfSmallBox);	}
	
	

public boolean insertUserShipment() {
    
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
        PreparedStatement ps = conn.prepare("INSERT INTO Shipping(CustomerID,Quantity,CustomerMessage"
        		+ ",PreferredDeparture,PreferredArrival,BookingTime,Cost,ShipperID,Status,CollectorID"
        		+ ",DeliveryAddress,HBL) "
        		+ "VALUES ( ?, ?, ?,?, ?, ?,?, ?, ?,?,?,?)");
        ps.setInt(1, 1);
        ps.setInt(2, Integer.parseInt(quantity));
        ps.setString(3, message);
        ps.setDate(4, departureDate );
        ps.setDate(5, arrivalDate );
        /*
         * one day has 86400000 milliseconds
         * multiply 5 for estimate of 5 days
         */
        //Date estimatedDate = new Date(new java.util.Date().getTime() + (86400000*5));
        //ps.setDate(7, estimatedDate);
        ps.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
        ps.setBigDecimal(7, getCost());
        //shipper ID
        ps.setInt(8, 1);
        //shipment status
        ps.setInt(9,1);
        //Collector ID
        ps.setInt(10, 1);
        //Delivery address
        ps.setString(11, address);
        //HBL
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

public boolean ackUserShipment() {
    
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
        PreparedStatement ps = conn.prepare("UPDATE Shipping(CustomerID,Quantity,CustomerMessage"
        		+ ",PreferredDeparture,PreferredArrival,BookingTime,Cost,ShipperID,CollectorID"
        		+ ",DeliveryAddress,HBL) "
        		+ "VALUES ( ?, ?, ?,?, ?, ?,?, ?, ?,?,?,?)");
        ps.setInt(1, 1);
        ps.setInt(2, Integer.parseInt(quantity));
        ps.setString(3, message);
        ps.setDate(4, departureDate );
        ps.setDate(5, arrivalDate );
        /*
         * one day has 86400000 milliseconds
         * multiply 5 for estimate of 5 days
         */
        //Date estimatedDate = new Date(new java.util.Date().getTime() + (86400000*5));
        //ps.setDate(7, estimatedDate);
        ps.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
        ps.setBigDecimal(7, getCost());
        //shipper ID
        ps.setInt(8, 1);
        //Collector ID
        ps.setInt(9, 1);
        //Delivery address
        ps.setString(10, address);
        //HBL
        ps.setString(11, "HBLGH234LJWEI224K42424243DKNKCKFF56");
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

public void updateStatus(String status, String orderId) {
	String sql = "UPDATE shipping SET Status=? WHERE ShippingID=?";
	try {
		PreparedStatement ps = conn.prepare(sql);
		ps.setString(1, status);
		ps.setInt(2, Integer.parseInt(orderId));
		int result = ps.executeUpdate();
	} catch (SQLException ex) {
        ex.printStackTrace();
    }
}

private void readDB() throws SQLException {
	// TODO Auto-generated method stub
	@SuppressWarnings("static-access")
	PreparedStatement ps = conn.prepare("SELECT * FROM Shipping");
	ResultSet rs = ps.executeQuery();
	
	System.out.println(rs);
}
}
