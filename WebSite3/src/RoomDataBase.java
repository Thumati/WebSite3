

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;



/**
 * Servlet implementation class RoomDataBase
 */
@WebServlet("/RoomDataBase")
public class RoomDataBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.print("in See");	
		response.setContentType("text/html");
		Gson gson=new Gson();
		List <HotelRoomsDetails> ls= new ArrayList<>();
		Connection con=null;
		Statement st=null;
		ResultSet rs= null;
		PrintWriter out=response.getWriter();
		String url="jdbc:mysql://localhost:3306/HotelData";
		String user="root";
		String pass="root";
		System.out.println("we startinga program!");
		HotelRoomsDetails hotelRoomsDetails=null;
		System.out.println("hello");
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			st=con.createStatement();
			System.out.println("connect to DB");
			rs=st.executeQuery("select * from room");
			System.out.println("execute the query");  
			System.out.println("hi db connected");
			
			while(rs.next())
			{
				System.out.println("to get values");
				hotelRoomsDetails = new HotelRoomsDetails();
				hotelRoomsDetails.Room_id=rs.getInt("Room_id");
				hotelRoomsDetails.Room_type=rs.getString("Room_type");
				hotelRoomsDetails.description=rs.getString("description");
				hotelRoomsDetails.Room_area=rs.getDouble("Room_area");
				hotelRoomsDetails.Price_reg=rs.getDouble("Price_reg");
				hotelRoomsDetails.Price_deal=rs.getDouble("Price_deal");
				hotelRoomsDetails.image=rs.getString("imgUrl");
				//hotelRoomsDetails.image="http://localhost:9999"+rs.getString("imgUrl");
		    	  ls.add(hotelRoomsDetails);     
		    	  System.out.println("added to list");
			}
			System.out.println("calling the json");
			String json=gson.toJson(ls);
		      System.out.println(json);
		      out.print(json);
			}
		catch(Exception ex)
		{
			System.out.println("not connect to db");
		}


	}
	
}
