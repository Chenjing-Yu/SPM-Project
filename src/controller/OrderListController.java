package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.OrderList;
import model.Shipment;
/**
 * Servlet implementation class OrderListController
 */
@WebServlet("/OrderListController")
public class OrderListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderList orderManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderListController() {
        super();
        orderManager = new OrderList();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("OrderListController:doGet");
		String role = String.valueOf(request.getSession().getAttribute("userRole"));
		String username = String.valueOf(request.getSession().getAttribute("loginusername"));
		System.out.println("OrderListController.doPost:role="+role);
		List<Shipment> orderList;
		if (role.equalsIgnoreCase("customer")) {
			System.out.println("this is a customer:"+username);
			orderList = orderManager.getHistory(username);
	        request.setAttribute("orders", orderList); // Will be available as ${orders} in JSP
	        System.out.println("setAttribute:orderList with size="+orderList.size());
	        request.getRequestDispatcher("/bookhistory.jsp").forward(request, response);
		}
		else {
			orderList = orderManager.getOrders();
	        request.setAttribute("orders", orderList); // Will be available as ${orders} in JSP
	        request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
		}
//        request.setAttribute("orders", orderList); // Will be available as ${orders} in JSP
//        request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
