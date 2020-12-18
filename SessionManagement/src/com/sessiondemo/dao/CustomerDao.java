package com.sessiondemo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sessiondemo.model.User;

public class CustomerDao {
	//if userid=0 that mean there is not user entered username and password
	//if userid is not 0 that mean there is user present wiht entered userid and password
	//Dao data access object all the database related operation will be
	//in this class
		public int checkLogin(String username,String password)
		{
			System.out.println("username="+username);
			System.out.println("password="+password);
			Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
			int userid = 0;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
				stmt=con.createStatement();
				String sqlquery="select custid from customer2 where username='"+username+"'and password='"+password+"'";
				//String sqlquery="select * from customer2";
				rs=stmt.executeQuery(sqlquery);
				System.out.println("outside while");
				while(rs.next())
				{
									
					System.out.println("inside while");
					userid=rs.getInt("custid");
					System.out.println("cusomterid="+userid);
					//jghghgh
					System.out.println("Bonjour");
				}
						
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			return userid;
		}
		
		
		
		public User getProfile(int custid)
		{
			Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
			User uobj=new User();
			String name = null,email = null;
			int userid = 0;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Varma123");
				stmt=con.createStatement();
				String sqlquery="select name,email from customer2 where custid="+custid;
				rs=stmt.executeQuery(sqlquery);
				while(rs.next())
				{
					name=rs.getString("name");
					email=rs.getString("email");
				}				
				uobj.setName(name);
				uobj.setEmail(email);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return uobj;
		}
		
		public ArrayList<User> getAllCustomer()
		{
				Connection con=null;
				Statement stmt=null;
				ResultSet rs=null;
				ArrayList<User> uobj=new ArrayList<User>();
				String name = null,email = null;
				int uid = 0;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Varma123");
					stmt=con.createStatement();
					String sqlquery="select * from customer2";
					rs=stmt.executeQuery(sqlquery);			
					while(rs.next()){
						uid=rs.getInt("custid");
						name=rs.getString("name");
						email=rs.getString("email");
						uobj.add(new User(uid,name,email));
					}		
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return uobj;
			
		}
		
		public boolean DeleteCustomer(int custid){
			boolean status=false;
			Connection con=null;
			Statement stmt=null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Varma123");
				stmt=con.createStatement();
				String sqlquery="delete from customer2 where custid="+custid;
				int rows=stmt.executeUpdate(sqlquery);
				if(rows>0)
				{
				status=true;
				}
				
					
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {

				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return status;
	
	

}
}
