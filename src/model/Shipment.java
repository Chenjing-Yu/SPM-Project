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
import java.util.Calendar;

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
	String customerID;//customer email address
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
	
	
	public Shipment(String quantity2, String address2, Date departureDate2, Date arrivalDate2, String message2, String UserEmail) {
		// TODO Auto-generated constructor stub
		this.quantity = quantity2;
		this.address = address2;
		this.preferredDeparture =departureDate2;
		this.preferredArrival = arrivalDate2;
		this.message = message2;
		
		//this.collectorID = "1";
		this.customerID = UserEmail;
		this.hblNumber="HBL21342j3nfejwhfw23kkfw";
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
		String sql = "SELECT customer.EmailAddress AS email, customer.FullName AS CustomerName, CustomerMessage, PreferredDeparture, PreferredArrival, "
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
                this.customerID = result.getString("email");
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
		System.out.println("Shipment.createRecord.result="+result);
		return result;
	}
	public BigDecimal  getCost() {	return new BigDecimal( Integer.parseInt(this.quantity)*costOfSmallBox);	}
	
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
        System.out.println("Shipment.insertUserShipment");
	    try {
	        PreparedStatement ps = conn.prepare("INSERT INTO Shipping(CustomerID,Quantity,CustomerMessage"
	        		+ ",PreferredDeparture,PreferredArrival,BookingTime,Cost,ShipperID,Status,CollectorID"
	        		+ ",DeliveryAddress,HBL) "
	        		+ "SELECT  CustomerID, ?, ?,?, ?, ?,?, ?, ?,?,?,? FROM customer WHERE customer.EmailAddress=?");
	        System.out.println("email address="+customerID);
	        ps.setString(12, customerID);
	        ps.setInt(1, Integer.parseInt(quantity));
	        ps.setString(2, message);
	        ps.setDate(3, preferredDeparture);
	        ps.setDate(4, preferredArrival );
	        /*
	         * one day has 86400000 milliseconds
	         * multiply 5 for estimate of 5 days
	         */
	        //Date estimatedDate = new Date(new java.util.Date().getTime() + (86400000*5));
	        //ps.setDate(7, estimatedDate);
	        ps.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
	        ps.setBigDecimal(6, getCost());
	        //shipper ID
	        ps.setInt(7, 1);
	        //shipment status
	        ps.setString(8,"To be Approved");
	        //Collector ID
	        ps.setInt(9, 1);
	        //Delivery address
	        ps.setString(10, address);
	        //HBL
	        ps.setString(11, "1234567");
	        PreparedStatement ps2 = conn.prepare("SELECT  CustomerID FROM customer WHERE customer.EmailAddress="+"'"+this.customerID+"'"+";");
	        System.out.println("SELECT  CustomerID FROM customer WHERE customer.EmailAddress="+this.customerID+";");
	        ResultSet r1 =ps2.executeQuery();
	        int cID = 0;
	        while(r1.next())
	        {
	         cID= r1.getInt("CustomerID");
	        }
	      
	       
	        int dummycost = Integer.parseInt(this.quantity)*25;
	        java.sql.Date currentTime = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        //PreparedStatement ps = conn.prepare("INSERT INTO customer (FullName, EmailAddress, Address, PhoneNUM, Password)"
			//+ " VALUES ('" + fullname + "','" + username + "','" + address + "','" + phone + "','" + password + "');");
	        
	        ps = conn.prepare("INSERT INTO Shipping(CustomerID,Quantity,CustomerMessage,PreferredDeparture,PreferredArrival,BookingTime,Cost,ShipperID,Status,CollectorID,DeliveryAddress,HBL) " + 
	        		" VALUES ('" + cID + "','" + this.quantity + "','" + this.message + "','" + this.preferredDeparture+ "','" + this.preferredArrival+ "','" + currentTime+ "','" + dummycost+ "','" + 1+ "','" + 1+ "','"+ 1 + "','" + this.address+ "','" + this.hblNumber + "');");
	        String intQuery = ("INSERT INTO Shipping(CustomerID,Quantity,CustomerMessage,PreferredDeparture,PreferredArrival,BookingTime,Cost,ShipperID,Status,CollectorID,DeliveryAddress,HBL) " + 
	        		" VALUES ('" + cID + "','" + this.quantity + "','" + this.message + "','" + this.preferredDeparture+ "','" + this.preferredArrival+ "','" + currentTime+ "','" + dummycost+ "','" + 1+ "','" + 1+ "','"+ 1 + "','" + this.address+ "','" + this.hblNumber + "');");
	        System.out.println(intQuery);
	        int i = ps.executeUpdate();
	        readDB();
	        System.out.println("sql Executed:"+i);
	      if(i != 0) {
		    System.out.println("11111");
	        return true;
	      }
	    } catch (SQLException ex) {
		    System.out.println("22222");
	        ex.printStackTrace();
	    }
	    return false;
	}//query fix
	
	public boolean ackUserShipment(Timestamp pickuptime) {
	    try {
	        PreparedStatement ps = conn.prepare("UPDATE shipping SET PickupTime=?, AcknowledgeTime=?, Status='Request Accepted' WHERE ShippingID=?");
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
			if (status.equals("Shipped")) {
				System.out.println("Shipped.");
				ps = conn.prepare("UPDATE shipping SET Status=?, DepartureDate=?  WHERE ShippingID=?");
				ps.setString(1, status);
				Timestamp deptDate = new Timestamp(System.currentTimeMillis());
				System.out.println("depareturedate:"+deptDate);
		        ps.setTimestamp(2, deptDate);
				ps.setInt(3, Integer.parseInt(orderId));
			}
			else if (status.equals("Arrived at Destination")) {
				ps = conn.prepare("UPDATE shipping SET Status=?, ArrivalDate=?  WHERE ShippingID=?");
				ps.setString(1, status);
		        ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
				ps.setInt(3, Integer.parseInt(orderId));
			}
			else if (status.equals("Delivered")) {
				ps = conn.prepare("UPDATE shipping SET Status=?, DeliveryDate=?  WHERE ShippingID=?");
				ps.setString(1, status);
		        ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
				ps.setInt(3, Integer.parseInt(orderId));
			}
			else {
				ps = conn.prepare("UPDATE shipping SET Status=? WHERE ShippingID=?");
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
