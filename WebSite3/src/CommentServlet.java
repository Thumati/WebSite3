

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet("/UpdateServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("hello dopost");
        System.out.print("in See");	
  	  
		
		response.setContentType("text/html");
		Gson gson=new Gson();
		List <UserDetails> ls= new ArrayList<>();
		Connection con=null;
		Statement st=null;
		ResultSet rs= null;
		PrintWriter out=response.getWriter();
		String url="jdbc:mysql://localhost:3306/HotelData";
		String user="root";
		String pass="root";
		System.out.println("we startinga program!");
		UserDetails usrDtl=null;
		//StringBuilder sb = new StringBuilder();
      //  BufferedReader reader = request.getReader();
        //String str;
        System.out.println("hello i am in feedback servlet");
/*        while ((str = reader.readLine()) != null) {
            sb.append(str);
            System.out.println(str);
        }

        UserDetails details = new Gson().fromJson(sb.toString(),UserDetails.class);
        System.out.println(sb.toString());
        System.out.println(details.name);
	*/	
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection(url,user,pass);
		st=con.createStatement();
		System.out.println("connect to DB");
		System.out.println("execute the query");  
		System.out.println("hi db connected");
		rs=st.executeQuery("select * from comment");
		while(rs.next())
		{
			usrDtl = new UserDetails();
			usrDtl.email=rs.getString("email");
			usrDtl.name=rs.getString("name");
			usrDtl.comment=rs.getString("mycomment");
			usrDtl.date=rs.getString("mydate");
	    	 ls.add(usrDtl);
		}
		//System.out.println("calling"+usrDtl.email+""+usrDtl.name);
	String json=gson.toJson(ls);
	   System.out.println(json);
	      out.print(json);
		
		}
	catch (Exception mex)
    {
        mex.printStackTrace();
          
     }

	}

}
