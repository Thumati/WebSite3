

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		 System.out.print("in See");	
 		response.setContentType("text/html");
 		Gson gson=new Gson();
 		//List <ReservationDetails> ls= new ArrayList<>();
 		Connection con=null;
 		Statement st=null;
 		ResultSet rs= null;
 		PrintWriter out=response.getWriter();
 		String url="jdbc:mysql://localhost:3306/HotelData";
 		String user="root";
 		String pass="root";
 		System.out.println("we startinga program!");
 	    StringBuilder sb=new StringBuilder();
 		String str=null;
 		
 		System.out.println("After string buffer initilization");
     	while((str=request.getReader().readLine())!=null)
 	    {
 		sb.append(str);
     	}
     	System.out.println("hi after appending");
 ReservationDetails reservdtl=new Gson().fromJson(sb.toString(),ReservationDetails.class);
 	System.out.println(reservdtl.CheckIn);
 	System.out.println(reservdtl.CheckOut);
 	System.out.println(reservdtl.Room_type);
 		  try{
 			 Date startDateWeb = new SimpleDateFormat("MM/dd/yyyy").parse(reservdtl.CheckIn);
 			 Date endDateWeb = new SimpleDateFormat("MM/dd/yyyy").parse(reservdtl.CheckOut);
 			 String startDateWebStr = new SimpleDateFormat("yyyy-MM-dd").format(startDateWeb);
 			 String endDateWebStr = new SimpleDateFormat("yyyy-MM-dd").format(endDateWeb);
  			
 			 System.out.println(startDateWebStr); 
 			System.out.println(endDateWebStr);
 			
 			Class.forName("com.mysql.jdbc.Driver");
 			con=DriverManager.getConnection(url,user,pass);
 			st=con.createStatement();
 			System.out.println("connect to DB");
 			rs=st.executeQuery("select * from roomdetails as rd JOIN roomtable as rt ON rd.roomid=rt.typeid LEFT JOIN(select count(rt.roomnumber) as total,rt.typeid from reservation as r JOIN roomtable as rt ON r.roomnumber=rt.roomnumber WHERE checkindate BETWEEN '"+startDateWebStr+"' AND '"+endDateWebStr+"' GROUP BY rt.typeid)as rot ON rd.roomid=rot.typeid WHERE (rot.total IS NULL OR rot.total<rd.sameroom) AND rd.roomtype='"+reservdtl.Room_type+"' GROUP BY rt.typeid");
 			System.out.println("execute the query");  
 			System.out.println("hi db connected");
 			ReservationDetails resDtl = new ReservationDetails();
 			while(rs.next())
 			{
 				
 				
 			    //resDtl.CheckIn=rs.get("checkindate");
 				//resDtl.CheckOut=rs.getString("checkoutdate");
 				//Date startDateDB = new SimpleDateFormat("yyyy-MM-dd").parse(resDtl.CheckIn);
 				//Date endDateDB = new SimpleDateFormat("yyyy-MM-dd").parse(resDtl.CheckOut);
 				//resDtl.Room_type=rs.getString("roomtype");
 			    resDtl.Room_number=rs.getString("roomnumber");
 				resDtl.totalOccupied=rs.getInt("total");
 				resDtl.sameRoom=rs.getInt("sameroom");
 				System.out.println(rs.getString(10));
 				System.out.println(rs.getInt(6));
 				 resDtl.availableRoom=resDtl.sameRoom-resDtl.totalOccupied;
 				//System.out.println(rs.getString(11));
 				//System.out.println(rs.getString(2));
 				//System.out.println(rs.getString(3));
 				//System.out.println(rs.getString(4));
 				//System.out.println(rs.getString(5));
 				//System.out.println(rs.getString(6));
 				//System.out.println(rs.getString(7));
 				//System.out.println(rs.getString(8));
 				//System.out.println(rs.getString(9));
				
 				//usrDtl.pass=rs.getString("pass");
 			//	ls.add(resDtl);
 				
 				//System.out.println(resDtl.CheckIn);
 		    	 
 			}
 			
 			System.out.println("hi");
 		//	System.out.println("calling"+ls);
 		String json=gson.toJson(resDtl);
 		   System.out.println(json);
 		      out.print(json);
 			}
 		catch(Exception ex)
 		{
 			System.out.println("not connect to db");
 			ex.printStackTrace();
 		}


	}

}
