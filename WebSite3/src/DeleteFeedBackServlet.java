

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet("/DeleteFeedBackServlet")
public class DeleteFeedBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteFeedBackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		 ResultSet rs=null;
		         
		         Gson gson=new Gson();
		         StringBuilder sb = new StringBuilder();
		         BufferedReader reader = request.getReader();
		         String str;
		         PrintWriter out=response.getWriter();
		         System.out.println("hello i am in deletefeedback servlet");
		        
		         while ((str = reader.readLine()) != null) {
		             sb.append(str);
		             System.out.println(str);
		         }
		             UserDetails details = new Gson().fromJson(sb.toString(),UserDetails.class);
		             System.out.println(sb.toString());
		                     //System.out.println("After Gson");
		                     Connection conn = null;
		                 try{    
		                     // Step 1: Allocate a database Connection object
		                     DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		                     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelData", "root", "root");
		                     
		                     // Step 3: Execute a SQL SELECT query
		                     Statement st = conn.createStatement();
		                     System.out.println("before my query");
		                     System.out.println(details.email);
		                     System.out.println(details.comment);
		                     String query="delete from comment where email='"+details.email+"' and mycomment='"+details.comment+"'";
		                     System.out.println("Hello");
		                     System.out.println(query);
		                      st.executeUpdate(query);
		                     System.out.println("..................");
                     
	                     String json=gson.toJson(details);
                     out.print(json);
		     }//closing for try
		     catch (Exception E) {
		         System.out.println("The error is=" + E.getMessage());
		         E.printStackTrace();
		     }
		  
	}

}
