

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBConnection
 */
@WebServlet("/DBConnection")
public class DBConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBConnection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Hello World!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	static Connection connection = null;

	static void getDBConnection() {
		System.out.println("-------- MySQL JDBC Connection Testing ------------");
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	         System.out.println("Where is your MySQL JDBC Driver?");
	         e.printStackTrace();
	         return;
	      }
	    System.out.println("MySQL JDBC Driver Registered!");

	    connection = null;
	    try {
	        UtilProp.loadProperty();
	        connection = DriverManager.getConnection(getURL(), getUserName(), getPassword());
	    } catch (Exception e) {
	         System.out.println("Connection Failed! Check output console");
	         e.printStackTrace();
	         return;
	    }

	    if (connection != null) {
	       System.out.println("You made it, take control your database now!");
	    } else {
	       System.out.println("Failed to make connection!");
	    }
	}
	   
	
	static String getURL() {
		String url = UtilProp.getProp("url");
		System.out.println("[DBG] URL: " + url);
		return url;
	}

	static String getUserName() {
		String usr = UtilProp.getProp("user");
		System.out.println("[DBG] Username: " + usr);
		return usr;
	}

	static String getPassword() {
		String pwd = UtilProp.getProp("password");
		System.out.println("[DBG] Password: " + pwd);
		return pwd;
   }

}
