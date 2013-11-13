
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

//@WebServlet("/RegistrationPage")
public class RegistrationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationPage() {
		super();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String str;
		while ((str = reader.readLine()) != null) {
			System.out.println(str);
			sb.append(str);
		}
		System.out.println(sb.toString());
		UserDetails details = new Gson().fromJson(sb.toString(),
				UserDetails.class);
		System.out.println("After Gson");
		Connection conn = null;
		try {
			// Step 1: Allocate a database Connection object
			System.out.println("Making the DataBase Connection");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/HotelData", "root", "root");
			System.out.println("Executing the SQL Query");
			// Step 3: Execute a SQL SELECT query
			String sql = "insert into userDetail(name,email,pass,age,address,ccnnumber) values (?,?,?,?,?,?)";
			System.out.println("Before the PrePared Statement");
			PreparedStatement prep = conn.prepareStatement(sql);
			System.out.println("After the prepared Statement");
			// Setting the values which we got from JSP form
			System.out.println("first to add name");
			prep.setString(1, details.name);
			System.out.println("after to  name");
			prep.setString(2, details.email);
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
		} catch (Exception E) {
			System.out.println("The error is=" + E.getMessage());
			E.printStackTrace();

		}

	}

}
