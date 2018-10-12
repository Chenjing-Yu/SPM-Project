package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
	String customerName; //Fullname
	String status;
	String cost;
	String hblNumber;
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
	public void readOrder() {
		String sql = "SELECT * FROM shipping NATUAL JOIN customer WHERE ShippingID=?";
		try {
			PreparedStatement ps = conn.prepare(sql);
			ps.setInt(1, Integer.parseInt(this.orderId));
			ResultSet result = ps.executeQuery();
			if (result.next()) {
                this.customerID = String.valueOf(result.getInt("CustomerID"));
                this.customerName = String.valueOf(result.getString("FullName"));
                this.quantity = String.valueOf(result.getInt("Quantity"));
                this.cost = String.valueOf(result.getInt("Cost"));
                this.status = result.getString("Status");
                this.message = result.getString("ShipperMessage");
                this.hblNumber = result.getString("HBL");
                this.pickupAddress = result.getString("Address");
                //SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                //Timestamp bookTime = result.getTimestamp("BookingTime");
                //this.bookingTime = f.format(bookTime);
            }
			else {
				System.out.println("The order is not found!");
			}
		} catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
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
	public String getHblNumber() {
		return hblNumber;
	}
	public void setHblNumber(String hbl) {
		this.hblNumber = hbl;
	}
	
	public boolean createRecord() throws SQLException {
		
		boolean result= insertUserShipment();
		return result;
	}
	public BigDecimal  getCost() {	return new BigDecimal( Integer.parseInt(quantity)*costOfSmallBox);	}
	
public String getOrderEmail() {
	if (this.orderId.isEmpty()) {
		System.out.println("no information about the customer");
		return "";
	}
	try {
		String sql = "SELECT EmailAddress FROM customer NATUAL JOIN shipping WHERE ShippingID=?";
		PreparedStatement ps = conn.prepare(sql);
		ps.setInt(1, Integer.parseInt(orderId));
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getString(1);
		}
		else {
			return "";
		}
	} catch (SQLException e) {
		//
	}
	return null;
}

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

public boolean ackUserShipment(Timestamp pickuptime) {
    try {
        PreparedStatement ps = conn.prepare("UPDATE shipping SET PickupTime=? WHERE ShippingID=?");
        ps.setTimestamp(1, pickuptime );
        ps.setInt(2, Integer.parseInt(this.orderId));
        int i = ps.executeUpdate();
      if(i > 0) {
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
