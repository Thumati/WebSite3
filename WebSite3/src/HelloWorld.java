
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.http.*;




// Extend HttpServlet class
public class HelloWorld extends HttpServlet {
 
  private String message;

  public void init() throws ServletException
  {
      // Do required initialization
      message = "Hello World";
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
  	
		Statement st=null;
		ResultSet rs=null;
		String url="jdbc:mysql://localhost:3306/HotelData";
		String user="root";
		String pass="root";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection(url,user,pass);
			System.out.println("connected");
			st= con.createStatement();
			rs=st.executeQuery("select * from emp");
			System.out.println("query executed");
			while(rs.next())
			{
				System.out.println("I am here...");
				System.out.println(rs.getString(1));
			}
		}
		catch(Exception ex)
		{
	         System.out.print("not db");		
		}
}
}
