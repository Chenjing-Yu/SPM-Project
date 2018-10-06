package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AckSubmitController
 */
@WebServlet("/AckSubmitController")
public class AckSubmitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AckSubmitController() {
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
			rd = request.getRequestDispatcher("/error.jsp");
		}
	}

}
