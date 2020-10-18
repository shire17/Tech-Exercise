

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String name = request.getParameter("name");
	      String title = "Delete";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
	      String header = "Deleted The Following:";
	      out.println(docType +
		            "<html>\n" +
		    		  "<head>\n"
		    		  + "<style>\n" + 
		    		  "header {\n" + 
		    		  "    color:black;\n" + 
		    		  "    text-align:center;\n" + 
		    		  "    padding:5px;\n" + 
		    		  "    font-family: Georgia; \n" + 
		    		  "}\n" + 
		    		  "body{\n" + 
		    		  "	font-family: Georgia;\n" + 
		    		  "}\n" + 
		    		  ".button1 {\n" + 
		    		  "	background-color: #4CAF50;\n" + 
		    		  "	border: 2px solid black;\n" + 
		    		  "	color: white;\n" + 
		    		  "	text-align: center;\n" + 
		    		  "	display: inline-block;\n" + 
		    		  "	font-size: 16px;\n" + 
		    		  "	padding: 16px 80px;\n" + 
		    		  "	align-items: center;\n" + 
		    		  "}\n" + 
		    		  ".button2 {\n" + 
		    		  "	background-color: #008CBA;\n" + 
		    		  "	border: 2px solid black;\n" + 
		    		  "	color: white;\n" + 
		    		  "	text-align: center;\n" + 
		    		  "	display: inline-block;\n" + 
		    		  "	font-size: 16px;\n" + 
		    		  "	padding: 16px 80px;\n" + 
		    		  "	align-items: center;\n" + 
		    		  "}\n" +
		    		  ".button3 {\n" + 
		    		  "	background-color: #F4511E;\n" + 
		    		  "	border: 2px solid black;\n" + 
		    		  "	color: white;\n" + 
		    		  "	text-align: center;\n" + 
		    		  "	display: inline-block;\n" + 
		    		  "	font-size: 16px;\n" + 
		    		  "	padding: 16px 80px;\n" + 
		    		  "	align-items: center;\n" + 
		    		  "}\n" +
		    		  "background-color: #F45113;\n" +
		    		  "border: 2px solid black;\n" +
		    		  "color: white;\n" +
		    		  "text-align: center;\n" +
		    		  "display: inline-block;\n" +
		    		  "font-size: 16px; \n" +
		    		  "padding: 8px 40px;\n" +
		    		  "align-items: center;\n" +
		    		  "}\n" +
		    		  "button:hover {\n" + 
		    		  "	opacity: 0.8;\n" + 
		    		  "}\n" +
		    		  "footer {\n" + 
		    		  "	position: fixed;\n" + 
		    		  "	bottom: 0;\n" + 
		    		  "    text-align:center;\n" + 
		    		  "    padding:5px;\n" + 
		    		  "    display: inline-block;\n" + 
		    		  "    width: 100%;\n" +
		    		  "}\n" + 
		    		  "div {\n" + 
		    		  "	text-align: center;\n" + 
		    		  "}\n" + 
		    		  "</style>\n" +
		    		  "</head>\n" +
		            "<title>" + title + "</title>\n" + //
		            "<body>\n" + //
		            "<header>" +
		            "<p style=\"font-size: 24px;\">" + header + "</p></header>\n<div style=\"text-align: left; margin-left: 300px;\">");
	      Connection connection = null;
	      PreparedStatement preparedStatement = null;
	      try {
	         DBConnection.getDBConnection();
	         connection = DBConnection.connection;
	         String deleteSQL = "DELETE FROM toDoList WHERE NAME = ?";
	         preparedStatement = connection.prepareStatement(deleteSQL);
	         preparedStatement.setString(1, name);
	         preparedStatement.execute();
	         out.println("</div>");
	         out.println("<p style=\"text-align:center\">Deleted \"" + name +  "\" from your to-do list</p><br>");
	         out.println("<div>\n" + 
	         		"	<form action=\"View\" method=\"get\">\n" + 
	         		"	<button class=\"button1 button:hover\" type=\"submit\" formaction=\"/Tech%20Exercise/InsertToDo.html\">Add An Item</button>\n" + 
	         		"	<button class=\"button2 button:hover\" type=\"submit\">View Your Items</button>\n" + 
	         		"	<button class=\"button3 button:hover\" type=\"submit\" formaction=\"/Tech%20Exercise/HomePage.html\">Home</button>\n" +
	         		"	</form>\n" + 
	         		"</div>");
	         out.println("<footer>Copyright © Shannon Hire 2020</footer></body></html>");
	         preparedStatement.close();
	         connection.close();
	      } catch (SQLException se) {
	    	  out.println("<p style=\"text-align:center\">Failed to delete \"" + name + "\" from your to-do list</p><br>");
	    	  out.println("<div>\n" + 
		         		"	<form action=\"View\" method=\"GET\">\n" + 
		         		"	<button class=\"button1 button:hover\" type=\"submit\" formaction=\"/Tech%20Exercise/InsertToDo.html\">Add An Item</button>\n" + 
		         		"	<button class=\"button2 button:hover\" type=\"submit\">View Your Items</button>\n" + 
		         		"	<button class=\"button3 button:hover\" type=\"submit\" formaction=\"/Tech%20Exercise/HomePage.html\">Home</button>\n" +
		         		"	</form>\n" + 
		         		"</div>");
		         out.println("<footer>Copyright © Shannon Hire 2020</footer></body></html>");
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (preparedStatement != null)
	               preparedStatement.close();
	         } catch (SQLException se2) {
	         }
	         try {
	            if (connection != null)
	               connection.close();
	         } catch (SQLException se) {
	            se.printStackTrace();
	         }
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
