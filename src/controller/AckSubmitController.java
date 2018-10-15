package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrderList;
import model.Shipment;
import utils.Mailer;

/**
 * Servlet implementation class AckSubmitController
 */
@WebServlet("/AckSubmitController")
public class AckSubmitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
    private String port;
    private OrderList orderManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AckSubmitController() {
        super();
        orderManager = new OrderList();
        // TODO Auto-generated constructor stub
    }
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
       
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Shipment> orderList = orderManager.getOrders();
        request.setAttribute("orders", orderList);
		request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AckSubmitController.doPost");
		String status = request.getParameter("status");
		String pickupdate = request.getParameter("pickupdate");
		String pickuptime = request.getParameter("pickuptime");
		RequestDispatcher rd = null;
		java.sql.Timestamp datetime = null;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
			java.util.Date utilDate = sdf.parse(pickupdate+" "+pickuptime);
			datetime = new java.sql.Timestamp(utilDate.getTime());
		} catch (Exception e) {
			System.out.println("Fail to parse the date and time.");
			rd = request.getRequestDispatcher("/error.jsp");
		}
		String orderId = request.getParameter("orderId");
		System.out.println("AckSubmitController: orderId="+orderId);
		Shipment order = new Shipment(orderId);
		order.ackUserShipment(datetime);
		
		String emailid = order.getOrderEmail();
		String content = "Hi, your order has been acknowledeged.";
		try {
			Mailer.sendEmail(host, port, emailid, content);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
