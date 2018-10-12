package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
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
 * Servlet implementation class UpdateStatusController
 */
@WebServlet("/UpdateStatusController")
public class UpdateStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderList orderManager;
	private String host;
    private String port;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStatusController() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("updateStatusController:doPost");
		String status = request.getParameter("status");
		String orderId = request.getParameter("orderId");
		System.out.println("status:"+status+";orderId:"+orderId);
		Shipment order = new Shipment(orderId);
		order.updateStatus(status, orderId);
		String emailid = order.getOrderEmail();
		String content = "Hi, we have update! your order status:"+status;
		try {
			Mailer.sendEmail(host, port, emailid, content);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Shipment> orderList = orderManager.getOrders();
        request.setAttribute("orders", orderList);
		request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
	}

}
