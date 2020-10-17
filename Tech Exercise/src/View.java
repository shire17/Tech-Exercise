

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class View
 */
@WebServlet("/View")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "View";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
	      String header = "Your To-Do Items";
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
	         String selectSQL = "SELECT * FROM toDoList";
	         preparedStatement = connection.prepareStatement(selectSQL);
	         ResultSet rs = preparedStatement.executeQuery();
	         if (rs.next() == false) {
	        	 out.println("<p style=\"text-align:left; margin-left: 300px;\"><b>No To-Do Items!</b></p>");
	         } else {
	        	 do {
	 	            String name = rs.getString("name").trim();
		            String description = rs.getString("description").trim();
		            String dueDate = rs.getString("dueDate").trim();
		            out.print("<b>Name: </b>" + name + " &nbsp;");
		            out.print("<b>Description: </b>" + description + " &nbsp;");
		            out.print("<b>Due Date: </b>" + dueDate + "&nbsp;");
		            out.print("<button type=\"submit\" onclick=\"window.location.href='/Tech_Exercise/Delete?name="
		            		+ name
		            		+"'\">&times;</button><br><br>");
	        	 } while (rs.next());
	         }
	         out.println("</div>");
	         out.println("<div>");
	         out.println("<button class=\"button1 button:hover\" type=\"submit\" onclick=\"window.location.href='/Tech_Exercise/HomePage.html'\">Home</button>");
	         out.println("<button class=\"button2 button:hover\" type=\"submit\" onclick=\"window.location.href='/Tech_Exercise/InsertToDo.html'\">Add An Item</button>");
	         out.println("</div>");
	         out.println("<footer>Copyright © Shannon Hire 2020</footer></body></html>");
	         rs.close();
	         preparedStatement.close();
	         connection.close();
	      } catch (SQLException se) {
	         se.printStackTrace();
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
