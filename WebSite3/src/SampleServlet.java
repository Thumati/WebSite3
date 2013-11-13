

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class SampleServlet
 */
@WebServlet("/SampleServlet")
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void SampleServlet() {
	
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


