package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Shipment;
import utils.Mailer;

/**
 * Servlet implementation class BookController
 */
@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
        super();
        // TODO Auto-generated constructor stub
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
		@SuppressWarnings("unused")
		RequestDispatcher rd = null;
		String quantity = request.getParameter("quantity");
		String address = request.getParameter("address");
		java.sql.Date arrivalDate = null;
		java.sql.Date departureDate= null;
		try {
		String startDate= request.getParameter("departureDate");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date dDate = sdf1.parse(startDate);
		 departureDate = new java.sql.Date(dDate.getTime()); 
		String startDate2= request.getParameter("departureDate");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date aDate = sdf2.parse(startDate2);
		arrivalDate = new java.sql.Date(aDate.getTime()); 
		String message = request.getParameter("message");
		String customerID = "khatnani6@gmail.com";
		String EmailContent = "Hello your shipment request is recived and your order is processing, stay tuned for updates!";
		
		Shipment  shipment =new Shipment(quantity,address,departureDate,arrivalDate,message,customerID);
		
		boolean createRecord = shipment.createRecord();
		if(createRecord) {
		System.out.println("Your shipment record has been successfuly created"+quantity+message);
		Mailer.sendMail(customerID, EmailContent);
		PrintWriter out = response.getWriter();  
		response.setContentType("text/html");  
		out.println("<script type=\"text/javascript\">");  
		out.println("alert('order success');");  
		out.println("window.location = 'index.jsp';");
		out.println("</script>");
		}
		else {rd = request.getRequestDispatcher("/error.jsp");}
		
		
		}
		catch(Exception ex) {
			rd = request.getRequestDispatcher("/error.jsp");
		}
		
	}

}
