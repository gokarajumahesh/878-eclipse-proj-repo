package com.sessiondemo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sessiondemo.dao.CustomerDao;

import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class Checklogin
 */
@WebServlet("/Checklogin")
public class Checklogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Checklogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String username=request.getParameter("txtuser");
		String password=request.getParameter("txtpass");
		//connect with database and check if user is present and return the userid
		CustomerDao cust=new CustomerDao();
		int usid=cust.checkLogin(username, password);
		System.out.println("userid="+usid);
		/*if(username.equals("admin") && password.equals("admin123")){
			String userid="101";
			HttpSession session=request.getSession();
			session.setAttribute("uid",userid);
			RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
			rd.forward(request, response);
		}*/
		if(usid!=0){
			//session is started
			HttpSession session=request.getSession();
			session.setAttribute("uid",usid);
			//set the name of user also in session
			RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
			rd.forward(request, response);
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
