

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.*;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MailServlet() {
        super();
        
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.print("in See");	
  	  
		
		response.setContentType("text/html");
		Gson gson=new Gson();
		
		Connection con=null;
		Statement st=null;
		ResultSet rs= null;
		PrintWriter out=response.getWriter();
		String url="jdbc:mysql://localhost:3306/HotelData";
		String user="root";
		String pass="root";
		System.out.println("we startinga program!");
		UserDetails usrDtl=null;
	String emailValue=request.getReader().readLine();
	UserDetails usrdtl=new Gson().fromJson(emailValue,UserDetails.class);
	System.out.println(usrdtl.email);
		
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection(url,user,pass);
		st=con.createStatement();
		System.out.println("connect to DB");
	rs=st.executeQuery("select * from UserInfo where email='"+usrdtl.email+"'"+" and "+"pass='"+usrdtl.pass+"'");
		//rs=st.executeQuery("select * from userInfo where email='archana6036@gmail.com'");
		System.out.println("execute the query");  
		System.out.println("hi db connected");
		usrDtl = new UserDetails();
		while(rs.next())
		{
			
			usrDtl.email=rs.getString("email");
			usrDtl.pass=rs.getString("pass");
	    	 
		}
		System.out.println("calling"+usrDtl.email+""+usrDtl.pass);
	String json=gson.toJson(usrDtl);
	   System.out.println(json);
	      out.print(json);
		 String[] to={usrDtl.email};
	    	String[] cc={usrDtl.email};
	    	String[] bcc={usrDtl.email};
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
	    String text="This is my reply";
	    	
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
	        		"This is my style...of reply"); 
	}
	catch(Exception ex)
	{
		System.out.println("not connect to db");
	}

		
	}
}

/*	public synchronized static boolean sendMail(String userName,String passWord,String host,String port,String starttls,String auth,boolean debug,String socketFactoryClass,String fallback,String[] to,String[] cc,String[] bcc,String subject,String text)
    {
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
            return true;
        }
        catch (Exception mex)
        {
            mex.printStackTrace();
                        return false;
        }
    }
 
	}


*/