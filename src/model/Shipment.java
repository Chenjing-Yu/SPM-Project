package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	String acknowledgeTime;
	String pickupTime;
	String quantity ;
	String deliveryDate;
	String departureDate;
	String arrivalDate;
	Date preferredDeparture;
	Date preferredArrival;
	String address ;//delivery address
	String pickupAddress;
	String message; //message left by customer: CustomerMessage
	String collectorID;
	String customerName; //Fullname
	String shipperName;
	String collectorName;
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
		this.preferredDeparture =departureDate2;
		this.preferredArrival = arrivalDate2;
		this.message = message2;
		
		//this.collectorID = "1";
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
	public String getShipperName() {
		return shipperName;
	}
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}
	public String getCollectorName() {
		return collectorName;
	}
	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
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
	public String getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public Date getPreferredDeparture() {
		return preferredDeparture;
	}
	public void setpreferredDeparture(Date preferredDeparture) {
		this.preferredDeparture = preferredDeparture;
	}
	public Date getPrefferedArrival() {
		return preferredArrival;
	}
	public void setpreferredArrival (Date preferredArrival) {
		this.preferredArrival = preferredArrival;
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
	public void setAcknowledgeTime(String ackTime) {
		this.acknowledgeTime = ackTime;
	}
	public String getAcknowledgeTime() {
		return this.acknowledgeTime;
	}
	public String getHblNumber() {
		return hblNumber;
	}
	public void setHblNumber(String hbl) {
		this.hblNumber = hbl;
	}

	public void readOrder() {
		String sql = "SELECT shipping.CustomerID AS CustomerID, customer.FullName AS CustomerName, CustomerMessage, PreferredDeparture, PreferredArrival, "
				+ "BookingTime, Quantity, Cost, Status, AcknowledgeTime, DepartureDate, ArrivalDate, DeliveryDate, ShipperMessage, PickupTime, HBL, "
				+ "shipper.FullName AS ShipperName, collector.FullName AS CollectorName, DeliveryAddress, customer.Address AS PickupAddress, "
				+ "collector.CollectorID AS CollectorID, shipper.ShipperID AS ShipperID " 
				+ "FROM shipping JOIN customer JOIN shipper JOIN collector " 
				+ "ON shipping.CustomerID=customer.CustomerID and shipping.ShipperID=shipper.ShipperID and shipping.CollectorID=collector.CollectorID " 
				+ "WHERE shippingID=?";
		try {
			PreparedStatement ps = conn.prepare(sql);
			System.out.println("Shipment.readOrder");
			ps.setInt(1, Integer.parseInt(this.orderId));
			ResultSet result = ps.executeQuery();
			if (result.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
                this.customerID = String.valueOf(result.getInt("CustomerID"));
                this.customerName = String.valueOf(result.getString("CustomerName"));
                this.message = result.getString("CustomerMessage");
                this.preferredDeparture = result.getDate("PreferredDeparture");
                System.out.println("Shipment.PreferredDeparture="+preferredDeparture);
                this.preferredArrival = result.getDate("PreferredArrival");
                System.out.println("Shipment.preferredArrival="+preferredArrival);
                Timestamp bookTime = result.getTimestamp("BookingTime");
                this.bookingTime = f.format(bookTime);
                this.quantity = String.valueOf(result.getInt("Quantity"));
                this.cost = String.valueOf(result.getInt("Cost"));
                this.status = result.getString("Status");
                Timestamp ackTime  = result.getTimestamp("AcknowledgeTime");
                if (ackTime != null) {
                	this.acknowledgeTime = f.format(ackTime);
                }
                Timestamp deptTime = result.getTimestamp("DepartureDate");
                if (deptTime != null) {
                	this.departureDate = fd.format(deptTime);
                }
                Timestamp arriveTime = result.getTimestamp("ArrivalDate");
                if (arriveTime != null) {
                	this.arrivalDate = fd.format(arriveTime);
                }
                Timestamp deliveryTime = result.getTimestamp("DeliveryDate");
                if (deliveryDate != null) {
                	this.deliveryDate = fd.format(deliveryTime);
                }
                Timestamp pickTime = result.getTimestamp("PickupTime");
                if (pickTime != null) {
                	this.pickupTime = f.format(pickTime);
                }
                this.hblNumber = result.getString("HBL");
                this.address = result.getString("DeliveryAddress");
                this.pickupAddress = result.getString("PickupAddress");
                //this.shipperId = String.valueOf(result.getInt("ShipperID"));
                this.collectorID = String.valueOf(result.getInt("CollectorID"));
            }
			else {
				System.out.println("The order is not found!");
			}
		} catch (SQLException ex) {
	        ex.printStackTrace();
	    }
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
	        PreparedStatement ps = conn.prepare("INSERT INTO Shipping(CustomerID,Quantity,CustomerMessage"
	        		+ ",PreferredDeparture,PreferredArrival,BookingTime,Cost,ShipperID,Status,CollectorID"
	        		+ ",DeliveryAddress,HBL) "
	        		+ "VALUES ( ?, ?, ?,?, ?, ?,?, ?, ?,?,?,?)");
	        ps.setInt(1, Integer.parseInt(customerID));
	        ps.setInt(2, Integer.parseInt(quantity));
	        ps.setString(3, message);
	        ps.setDate(4, preferredDeparture);
	        ps.setDate(5, preferredArrival );
	        /*
	         * one day has 86400000 milliseconds
	         * multiply 5 for estimate of 5 days
	         */
	        //Date estimatedDate = new Date(new java.util.Date().getTime() + (86400000*5));
	        //ps.setDate(7, estimatedDate);
	        ps.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
	        System.out.println("11111");
	        ps.setBigDecimal(7, getCost());
	        System.out.println("22222");
	        //shipper ID
	        ps.setInt(8, 1);
	        //shipment status
	        ps.setString(9,"To be Approved");
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
	        PreparedStatement ps = conn.prepare("UPDATE shipping SET PickupTime=?, AcknowledgeTime=? WHERE ShippingID=?");
	        ps.setTimestamp(1, pickuptime );
	        ps.setTimestamp(2,new Timestamp(System.currentTimeMillis()));
	        ps.setInt(3, Integer.parseInt(this.orderId));
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
		PreparedStatement ps = null;
		System.out.println("Shipment.updateStatus:"+status);
		try {
			if (status == "Shipped") {
				ps = dbConnection.prepare("UPDATE shipping SET Status=?, DepartureDate=?  WHERE ShippingID=?");
				ps.setString(1, status);
		        ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
				ps.setInt(3, Integer.parseInt(orderId));
			}
			else if (status == "Arrived at Destination") {
				ps = dbConnection.prepare("UPDATE shipping SET Status=?, ArrivalDate=?  WHERE ShippingID=?");
				ps.setString(1, status);
		        ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
				ps.setInt(3, Integer.parseInt(orderId));
			}
			else if (status == "Delivered") {
				ps = dbConnection.prepare("UPDATE shipping SET Status=?, DeliveryDate=?  WHERE ShippingID=?");
				ps.setString(1, status);
		        ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
				ps.setInt(3, Integer.parseInt(orderId));
			}
			else {
				ps = dbConnection.prepare("UPDATE shipping SET Status=? WHERE ShippingID=?");
				ps.setString(1, status);
				ps.setInt(2, Integer.parseInt(orderId));
			}
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
