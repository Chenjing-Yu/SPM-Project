package controller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Authenticator;
//import model.PrintWriter;
import model.User;

import javax.servlet.http.Cookie;



public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username:"+username+"; password:"+password);
		RequestDispatcher rd = null;
		
		Authenticator authenticator = new Authenticator();
		String result = authenticator.authenticate(username, password);
		
		if (result.equals("success")) {
			System.out.println("authentication success");
			String fullname = authenticator.getFullname();
			String role = authenticator.getRole();
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("fullname", fullname);
			request.getSession().setAttribute("userRole", role);
			if		(role.equalsIgnoreCase("customer")) {rd = request.getRequestDispatcher("book.jsp");		}
			else if (role.equalsIgnoreCase("shipper")) {rd = request.getRequestDispatcher("orderlist.html");		}
			else if (role.equalsIgnoreCase("collector")) {rd = request.getRequestDispatcher("book.jsp");		}
			rd.forward(request, response);
			
		} else {
			//rd = request.getRequestDispatcher("/index.jsp");
			PrintWriter out = response.getWriter();  
			response.setContentType("text/html");  
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Username or password is not correct.');");  
			out.println("window.location.href = 'index.jsp';");
			out.println("</script>");
		}
	}

}