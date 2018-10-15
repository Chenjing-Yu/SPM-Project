package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.out.println("BookController.doGet");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/book.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		HttpSession httpSession = request.getSession();
		System.out.println("BookController.doPost");
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
		String startDate2= request.getParameter("arrivalDate");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date aDate = sdf2.parse(startDate2);
		arrivalDate = new java.sql.Date(aDate.getTime()); 
		String message = request.getParameter("message");
		String userName = (String) httpSession.getAttribute("loginusername");
		System.out.println("EmailAddress:"+userName);
		String EmailContent = "Hello your shipment request is recived and your order is processing, stay tuned for updates!";
		
		Shipment  shipment =new Shipment(quantity,address,departureDate,arrivalDate,message,userName);
		
		boolean createRecord = shipment.createRecord();
		if(createRecord) {
		System.out.println("Your shipment record has been successfuly created"+quantity+message);
		 //Mailer.sendMail(host, port, user, pass,customerID, EmailContent);
		Mailer.sendEmail(host, port, userName, EmailContent);
		PrintWriter out = response.getWriter();  
		response.setContentType("text/html");  
		out.println("<script type=\"text/javascript\">");  
		out.println("alert('order success');");  
		out.println("window.location.href = 'book.jsp';");
		out.println("</script>");
		System.out.print("not alerted");
		//doGet(request, response);
		}
		else {rd = request.getRequestDispatcher("/error.jsp");}
		
		
		}
		catch(Exception ex) {
			rd = request.getRequestDispatcher("/error.jsp");
		}
		
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
