package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
    
    private String host;
    private String port;
    private String user;
    private String pass;
 
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
		@SuppressWarnings("unused")
		RequestDispatcher rd = null;
		String fullname = request.getParameter("fullname");
		String username = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String EmailContent = "Your account has been successfully created. Stay tuned for awesome stuff!!";
		
		User  newUser =new User(fullname,username,address,phone,password);
		
		boolean createRecord = newUser.createRecord();
		if(createRecord) {
		System.out.println("User record has been successfuly created: "+username);
		System.out.println("**********");
		try {
			Mailer.sendEmail(host, port, username, EmailContent);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("**********");
		PrintWriter out = response.getWriter();  
		response.setContentType("text/html");  
		out.println("<script type=\"text/javascript\">");  
		out.println("alert('register success');");  
		out.println("window.location.href = 'index.jsp';");
		out.println("</script>");
		}
		else {rd = request.getRequestDispatcher("/error.jsp");}
		
		
		
		
		
		
		
	}
	
	private static void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
            new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(cmd + " " + line);
        }
      }

      private static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
      }
		
		
	}


