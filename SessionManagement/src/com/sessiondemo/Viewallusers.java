package com.sessiondemo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sessiondemo.dao.CustomerDao;
import com.sessiondemo.model.User;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class Viewallusers
 */
@WebServlet("/Viewallusers")
public class Viewallusers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Viewallusers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		CustomerDao objcs=new CustomerDao();
		ArrayList<User> ob=objcs.getAllCustomer();
		out.println("<table>");
		out.println("<th>Customer ID</th><th>Customer Name</th><th>Email</th><th>Update</th><th>Delete</th>");

		for(User uob:ob)
		{
		out.println("<tr>");
		out.println("<td>"+uob.getId()+"</td>");
		out.println("<td>"+uob.getName()+"</td>");
		out.println("<td>"+uob.getEmail()+"</td>");
		out.println("<td><a href='updatecustomer'>Update</a></td>");
		out.println("<td><a href='DeleteCustomer?uid="+uob.getId()+"'>Delete</a></td>");
		out.println("</tr>");
		}
		out.println("</table>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
