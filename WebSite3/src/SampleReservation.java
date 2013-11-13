

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;



@WebServlet("/SampleReservation")
public class SampleReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   public SampleReservation() {
        super();
        // TODO Auto-generated constructor stub
    }


	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		System.out.println("hello");
			
		response.setContentType("text/html");
		ResultSet rs=null;
		Gson gson=new Gson();
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String str;
		PrintWriter out=response.getWriter();
		while ((str = reader.readLine()) != null) {
			System.out.println(str);
			sb.append(str);
		}
		System.out.println(sb.toString());
		ReservationDetails details = new Gson().fromJson(sb.toString(),
				ReservationDetails.class);
		  System.out.println("After Gson");
		   Connection conn = null;
		try {
			 Date startDateWeb = new SimpleDateFormat("MM/dd/yyyy").parse(details.CheckIn);
 			 Date endDateWeb = new SimpleDateFormat("MM/dd/yyyy").parse(details.CheckOut);
			 String startDateWebStr = new SimpleDateFormat("yyyy-MM-dd").format(startDateWeb);
			 String endDateWebStr = new SimpleDateFormat("yyyy-MM-dd").format(endDateWeb);
 			System.out.println(startDateWebStr);
 			System.out.println(details.Room_type);
 			
 			if(details.Room_type.equals("single"))
 			{
 				details.Room_id=1;
 				System.out.println(details.Room_id);
 			
 			}
 			else if(details.Room_type.equals("double"))
 			{
 				details.Room_id=8;
 				System.out.println(details.Room_id);
 			}
 			else if(details.Room_type.equals("TripleRoom"))
 			{
 				details.Room_id=9;
 				System.out.println(details.Room_id);
 			}
 			else
 			{
 				//details.Room_id;
 			}
		// Step 1: Allocate a database Connection object
			System.out.println("Making the DataBase Connection");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/HotelData", "root", "root");
			System.out.println("Executing the SQL Query");
			// Step 3: Execute a SQL SELECT query
			
			System.out.println("Before the PrePared Statement");
			
			
			Statement st = conn.createStatement();
            String query="insert into reservation(checkindate,checkoutdate,roomnumber,name) values('"+startDateWebStr+"','"+endDateWebStr+"','"+details.Room_type+"','"+details.email+"')";
			st.executeUpdate(query);
			System.out.println("query executed");
		//	UserDetails xobj=new UserDetails();
			//while(rs.next())
		//{
			//	xobj.email=rs.getString("email");
			//}
			
		
			System.out.println("Before Second SQL Statement");
			String sql = "insert into roomtable(roomnumber,typeid) values ('"+details.Room_type+"',"+details.Room_id+")";
			st.executeUpdate(sql);
			System.out.println("After Executing the Query");
			
			String sql1="select * from reservation where checkindate='"+startDateWebStr+"'"+"AND checkoutdate='"+endDateWebStr+"'";
			rs=st.executeQuery(sql1);
			ReservationDetails ResDtl=new ReservationDetails();
			if(rs.next())
			{
				ResDtl.Reserv_id=rs.getInt("rid");
			}
			System.out.println(ResDtl.Reserv_id);
			String json=gson.toJson(ResDtl);
	 		   System.out.println(json);
	 		      out.print(json);
	 			
	 		     st.close();
			   out.close();
			   
				    String[] to={details.email};
			    	String[] cc={details.email};
			    	String[] bcc={details.email};
			        //This is for google
			    	
			    	String userName="archana6036@gmail.com";
			    	String passWord="parvathitt";
			    	String host="smtp.gmail.com";
			    	String port="465";
			    	String starttls="true";
			    	String auth="true";
			    	boolean debug=true;
			    	String socketFactoryClass="javax.net.ssl.SSLSocketFactory";
			    	String fallback="false";
			    	String subject="hi, Test Mail";
			    String text="Reservation Completed....\nFollowing are the User Conformation Details....\nFor Futher Assistence Please contact Administration...\nUserDetails:\n"+"Email id:"+details.email+"\nReservation id"+ResDtl.Reserv_id+"\nTotal Price:"+details.total+"\nCheckIn:"+startDateWebStr+"\nCheckOut:"+endDateWebStr;
			    	
			        Properties props = new Properties();
			        //Properties props=System.getProperties();
			        props.put("mail.smtp.user", userName);
			        props.put("mail.smtp.host", host);
			        if(!"".equals(port))
			        	props.put("mail.smtp.port", port);
			        if(!"".equals(starttls))
			        	props.put("mail.smtp.starttls.enable",starttls);
			        props.put("mail.smtp.auth", auth);
			        if(debug)
			        {
			                props.put("mail.smtp.debug", "true");
			        }
			        else
			        {
			                props.put("mail.smtp.debug", "false");         
			        }
			        if(!"".equals(port))
			        	props.put("mail.smtp.socketFactory.port", port);
			        if(!"".equals(socketFactoryClass))
			        	props.put("mail.smtp.socketFactory.class",socketFactoryClass);
			        if(!"".equals(fallback))
			        	props.put("mail.smtp.socketFactory.fallback", fallback);
			 
			        try
			        {
			            Session session = Session.getDefaultInstance(props, null);
			            session.setDebug(debug);
			            MimeMessage msg = new MimeMessage(session);
			            msg.setText(text);
			            msg.setSubject(subject);
			            msg.setFrom(new InternetAddress("archana6036@gmail.com"));
			            for(int i=0;i<to.length;i++)
			            {
			            	msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			            }
			            for(int i=0;i<cc.length;i++)
			            {
			            	msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
			            }
			            for(int i=0;i<bcc.length;i++)
			            {
			            	msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
			            }
			            msg.saveChanges();
			            Transport transport = session.getTransport("smtp");
			            transport.connect(host, userName, passWord);
			            transport.sendMessage(msg, msg.getAllRecipients());
			            transport.close();
			            
			        }
			        catch (Exception mex)
			        {
			            mex.printStackTrace();
			              
				}
			        Mail.sendMail("archana6036@gmail.com","parvathitt","smtp.gmail.com","465","true","true",true,
			        	"javax.net.ssl.SSLSocketFactory","false",to,cc,bcc,
			        	"hi, Test Mail",
			        		text); 
			


				

			
		} catch (Exception E) {
			System.out.println("The error is=" + E.getMessage());
			E.printStackTrace();

		}


	}

}
