package model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class OrderList {

	dbConnection conn;
	
	public List<Shipment> getHistory(String userName) {
		List<Shipment> orderlist = new ArrayList<Shipment>();
		try {
			    System.out.println("OrderList.getHistory.start, username="+userName);
	            PreparedStatement sql = conn.prepare("SELECT ShippingID, BookingTime, Quantity, Status, PreferredArrival FROM Shipping NATURAL JOIN Customer WHERE EmailAddress=?");
	            sql.setString(1, userName);
	            ResultSet resultSet = sql.executeQuery();

	            while (resultSet.next()) {
	                Shipment order = new Shipment();
	                order.setOrderId(String.valueOf(resultSet.getInt("ShippingID")));
	                //order.setCustomerID(String.valueOf(customerID));
	                Timestamp bookingTime = resultSet.getTimestamp("BookingTime");
	                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	                order.setBookingTime(f.format(bookingTime));
	                order.setQuantity(String.valueOf(resultSet.getInt("Quantity")));
	                order.setpreferredArrival(resultSet.getDate("PreferredArrival"));
	                order.setStatus(resultSet.getString("Status"));
	                orderlist.add(order);
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
        System.out.println("get orders:"+ orderlist.size());
	    System.out.println("OrderList.getHistory.end");
	    return orderlist;
	        
	}
	
	public List<Shipment> getOrders() {
	    System.out.println("OrderList.getOrders.start");
		List<Shipment> orderlist = new ArrayList<Shipment>();
		try {
	            PreparedStatement sql = conn.prepare("SELECT ShippingID, EmailAddress, FullName, BookingTime, Quantity, PreferredDeparture, PreferredArrival, Address, Status, Cost FROM Shipping NATURAL JOIN Customer");
	            ResultSet resultSet = sql.executeQuery();

	            while (resultSet.next()) {
	                Shipment order = new Shipment();
	                order.setOrderId(String.valueOf(resultSet.getInt("ShippingID")));
	                order.setCustomerID(resultSet.getString("EmailAddress"));
	                order.setCustomerName(resultSet.getString("FullName"));
	                Timestamp bookingTime = resultSet.getTimestamp("BookingTime");
	                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	                order.setBookingTime(f.format(bookingTime));
	                order.setQuantity(String.valueOf(resultSet.getInt("Quantity")));
	                order.setpreferredDeparture(resultSet.getDate("PreferredDeparture"));
	                System.out.println("OrderList.PreferredDeparture="+order.getPreferredDeparture());
	                order.setpreferredArrival(resultSet.getDate("PreferredArrival"));
	                System.out.println("OrderList.PreferredArrival="+order.getPrefferedArrival());
	                order.setPickupAddress(resultSet.getString("Address"));
	                order.setStatus(resultSet.getString("Status"));
	                orderlist.add(order);
	            }
	            System.out.println("Successfully get the orders:" + orderlist.get(0).customerName);
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	    System.out.println("OrderList.getOrders.end");
	        return orderlist;
	}
}
