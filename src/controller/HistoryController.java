package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrderList;
import model.Shipment;

/**
 * Servlet implementation class HistoryController
 */
@WebServlet("/HistoryController")
public class HistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderList orderManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryController() {
        super();
        orderManager = new OrderList();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HistoryController.doGet");
		String role = String.valueOf(request.getSession().getAttribute("userRole"));
		String fullname = String.valueOf(request.getSession().getAttribute("loginfullname"));
		System.out.println("OrderListController.doPost:role="+role);
		List<Shipment> orderList;
		if (role.equalsIgnoreCase("customer")) {
			orderList = orderManager.getHistory(fullname);
		}
		else {
			orderList = orderManager.getOrders();
		}
        request.setAttribute("orders", orderList); // Will be available as ${orders} in JSP
        request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
