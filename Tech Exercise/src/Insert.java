

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.PrintWriter;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String name = request.getParameter("name");
	      String description = request.getParameter("description");
	      String dueDate = request.getParameter("dueDate");

	      Connection connection = null;
	      String insertSql = " INSERT INTO toDoList (id, NAME, DESCRIPTION, DUEDATE) values (default, ?, ?, ?)";

	      try {
	         DBConnection.getDBConnection();
	         connection = DBConnection.connection;
	         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
	         preparedStmt.setString(1, name);
	         preparedStmt.setString(2, description);
	         preparedStmt.setString(3, dueDate);
	         preparedStmt.execute();
	         connection.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      // Set response content type
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Insert";
	      String header;
	      if (name != "" && description != "" && dueDate != "" 
	    		  && name != null && description != null && dueDate != null) header = "Inserted the following into your to-do list:";
	      else header = "Unable to add item; please make sure all fields are filled in.";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
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
	    		  "background-color: #F45113;\n" +
	    		  "border: 2px solid black;\n" +
	    		  "color: white;\n" +
	    		  "text-align: center;\n" +
	    		  "display: inline-block;\n" +
	    		  "font-size: 16px; \n" +
	    		  "padding: 16px 80px;\n" +
	    		  "align-items: center;\n" +
	    		  "}\n" +
	    		  "button:hover {\n" + 
	    		  "	opacity: 0.8;\n" + 
	    		  "}\n" + 
	    		  "footer {\n" + 
	    		  "	position: fixed\n" + 
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
	            "<title>" + title + "</title>\n" +
	            "<body>\n" + //
	            "<header><p style=\"font-size: 24px;\">" + header + "</p></header>\n");
	      if (name != "" && description != "" && dueDate != ""
	    		  && name != null && description != null && dueDate != null) out.println(
	            "<div>" +
	        	"  <li><b>Name</b>: " + name + "\n" + //
	            "  <br><li><b>Description</b>: " + description + "\n" +
	            "  <br><li><b>Due Date</b>: " + dueDate + "<br><br>\n" +
	            "</div>");
	      out.println("<div><form action=\"View\" method=\"GET\">" +
	        	"<button class=\"button1 button:hover\" type=\"submit\" formaction=\"/Tech%20Exercise/HomePage.html\">Home</button>" +
	        	"&nbsp;<button class=\"button2 button:hover\" type=\"submit\" formaction=\"/Tech%20Exercise/InsertToDo.html\">Add Another Item</button>" +
	        	"&nbsp;<button class=\"button3 button:hover\" type=\"submit\">View Your List</button>" +
	            "</div>\n" +
	            "<footer>\n" +
	            "Copyright © Shannon Hire 2020" +
	            "</footer>\n" +
	        	"</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
