package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Shipment;
import model.User;
import utils.Mailer;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
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
		String fullname = request.getParameter("fullname");
		String username = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String EmailContent = "Hello your ID has been created successfully , stay tuned for updates!";
		
		User  user =new User(fullname,username,address,phone,password);
		
		boolean createRecord = user.createRecord();
		if(createRecord) {
		System.out.println("User record has been successfuly created"+username);
		Mailer.sendMail(username, EmailContent);
		PrintWriter out = response.getWriter();  
		response.setContentType("text/html");  
		out.println("<script type=\"text/javascript\">");  
		out.println("alert('order success');");  
		out.println("window.location = 'index.jsp';");
		out.println("</script>");
		}
		else {rd = request.getRequestDispatcher("/error.jsp");}
		
		
		
		
		
	}
		
		
	}


