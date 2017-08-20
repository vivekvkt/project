package com.AddharRegForm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/reg")
public class RegFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		HttpSession hs = request.getSession();
		String fno = request.getParameter("fno");
		if(fno.equals("1"))
		{
			
			String name = request.getParameter("name");
			String fname = request.getParameter("fname");
			String mname = request.getParameter("mname");

			hs.setAttribute("name", name);
			hs.setAttribute("fname", fname);
			hs.setAttribute("mname", mname);
			response.sendRedirect("./form2.html");
			
		}
		if(fno.equals("2"))
		{
			String contact = request.getParameter("contact");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			
			hs.setAttribute("contact", contact);
			hs.setAttribute("email", email);
			hs.setAttribute("address", address);
			response.sendRedirect("./form3.html");
			
		}
		if(fno.equals("3"))
		{
			
			String qual = request.getParameter("qual");
			String per = request.getParameter("per");
			
			String name =(String) hs.getAttribute("name");
			String fname =(String) hs.getAttribute("fname");
			String mname = (String)hs.getAttribute("mname");
			String contact =(String) hs.getAttribute("contact");
			String email =(String) hs.getAttribute("email");
			String address =(String) hs.getAttribute("address");
			
			
			try{
				
				//step1 load the driver class  
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
				PreparedStatement stmt=con.prepareStatement("insert into adharreg values(?,?,?,?,?,?,?,?)");
				
				stmt.setString(1, name);
				stmt.setString(2, fname);
				stmt.setString(3, mname);
				stmt.setString(4, contact);
				
				stmt.setString(5, email);
				stmt.setString(6, address);
				stmt.setString(7, qual);
				stmt.setString(8, per);
				
				int i = stmt.executeUpdate();
				if(i!=0){
					
				out.println("<pre>");	
				out.println("Name:" +name);
				out.println("FatherName:" +fname);
				out.println("MotherName:" +mname);
				out.println("Contact:" +contact);
				out.println("Email:" +email);
				out.println("Address:" +address);
				out.println("Qualification:" +qual);
				out.println("Percentage:" +per);
				out.println("</pre>");

			         // out.println("<font color = 'green'> <h1> SuccessFull Registered </h1></font>");	
				}else{
					 out.println("<font color = 'red'> <h1> Registration Failed Try Again</h1></font>");	
				}
			}catch(Exception e){
				
				out.println("<font color = 'red'><h1>Registration failed:</h1></font>" +e.getMessage() );
				e.printStackTrace();
				
			}
			
		}
		
		
	}

	
}
