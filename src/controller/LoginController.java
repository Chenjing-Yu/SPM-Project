package controller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Authenticator;
//import model.PrintWriter;
import model.User;
import utils.Mailer;

import javax.servlet.http.Cookie;



public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
    private String port;
	public LoginController() {
		super();
	}
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
      
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
			String emailid = username;
			String content = "Hi, you have successfully logged in. If you didnot do login please contact customer care.";
			try {
				Mailer.sendEmail(host, port, emailid, content);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(fullname);
			String role = authenticator.getRole();
			request.getSession().setAttribute("loginusername", username);
			request.getSession().setAttribute("loginfullname", fullname);
			request.getSession().setAttribute("userRole", role);
			System.out.println(request.getSession().getAttribute("loginfullname"));
			if		(role.equalsIgnoreCase("customer")) {rd = request.getRequestDispatcher("book.jsp");		}
			else if (role.equalsIgnoreCase("shipper")) {rd = request.getRequestDispatcher("OrderListController");		}
			else if (role.equalsIgnoreCase("collector")) {rd = request.getRequestDispatcher("OrderListController");		}
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
