

import java.io.BufferedReader;
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

import com.google.gson.Gson;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
response.setContentType("text/html");
    	
    	StringBuilder sb = new StringBuilder();
    	BufferedReader reader = request.getReader();
    	String str;
    	while((str = reader.readLine()) != null) {
    		System.out.println(str);
    		sb.append(str);
    	}
    	UserDetails details = new Gson().fromJson(sb.toString(), UserDetails.class);
    	System.out.println("After Gson");
	   Connection conn = null;
	   try {
	      // Step 1: Allocate a database Connection object
		  DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
	      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelData", "root", "root");

	      // Step 3: Execute a SQL SELECT query
	      String sql = "update userDetail into set(username,email,pass,age,address,ccn)=(?,?,?,?,?) where email=?";
	      
	      PreparedStatement prep =  conn.prepareStatement(sql); 
	   
	      // Setting the values which we got from JSP form
	    		 
	    		  prep.setString(1, details.name);
	    		  prep.setString(2,details.email);
	    		  prep.setString(3, details.pass);
	    		  prep.setInt(4, details.age);
	    		  prep.setString(5, details.address);
	    		  prep.setInt(6, details.ccn);
	    		  prep.executeUpdate();
	    		  prep.close();
	    	System.out.println("executed success");
	    	PrintWriter out = response.getWriter();
	    	out.println("{}");
	    	out.close();
	    		    }
	   catch(Exception E){
	    		System.out.println("The error is="+E.getMessage());
	    		    	 
	   }
	   
    }
	}


