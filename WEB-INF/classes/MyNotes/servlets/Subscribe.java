package MyNotes.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import MyNotes.servlets.*;
import java.sql.*;
import MyNotes.utils.*;

public class Subscribe extends HttpServlet
{
	public Subscribe(){
		super();
	}

	public void drawHeader(HttpServletRequest req, PrintWriter out)
   {
      out.println("<html>");
      out.println("<head><title>Subscribe to a Board</title></head>");

      out.println("<body>");
      out.println("<p>");
      out.println("<center>");
      out.println("<font face=\"Arial, Helvetica, sans-serif\">");
      out.println("<font color=\"#000066\">");
      out.println("<center>\n<font size=7><strong>MyNotes</strong></font></br>");
      out.println("<font size=4>MyNotes: a UA Project Management Program</font>");

       if (req.getSession().getAttribute("username") != null)
            out.println("<font size=4></br>Hello, " + req.getSession().getAttribute("username") + "!");
         
      out.println("</center>\n<font size=4><hr color=\"#000066\">");
      out.println("<b>Find Boards</b><br><br/></font>");
      out.println("</font>");
   }

   public void drawFooter(HttpServletRequest req, PrintWriter out)
   {
      out.println("<br>");
      out.println("<hr>");
      out.println("<br>");

      out.println("<p>");
      out.println("<form name=\"MainMenu\" action=LoginServlet method=get>");
      out.println("<input type=submit name=\"MainMenu\" value=\"Main Menu\">");
      out.println("</form>");
      out.println("</p>");

      out.println("<p>");
      out.println("<form name=\"logout\" action=/MyNotes/JSP/Logout.jsp>");
      out.println("<input type=submit name=\"logoutMyNotes\" value=\"Logout\">");
      out.println("</form>");
      out.println("</p>");

      out.println("<br><br>");
      out.println("</center>");
      out.println("</p>");
      out.println("</body>");
      out.println("</html>");
   }

   public void drawSubscribe(HttpServletRequest req, PrintWriter out){
   		out.println("<form name=\"boardSearch\" action=Subscribe method=get>");
   		out.println("Enter board name to subscribe to: ");
   		out.println("<input type=text size=64 name=\"boardName\">");
   		out.println("<input type=submit name=\"subscribeBoards\" value=\"Subscribe\">");
   		out.println("</form>");
   }


   public void subscribeQuery(HttpServletRequest req, PrintWriter out, HttpServletResponse response) throws IOException
   {

   		String boardName = req.getParameter("boardName");
   		OracleConnect oracle = new OracleConnect();
   		Connection conn;
   		PreparedStatement query = null;
   		try{
   			try{
   				Class.forName("oracle.jdbc.OracleDriver");
   			}catch(ClassNotFoundException e){
   				System.err.print(e);
   			}

   			conn = DriverManager.getConnection(OracleConnect.connect_string, OracleConnect.user_name, OracleConnect.password);
   			if (conn == null){
   				throw new IOException("getConnection failed");
   			}

   			try{
   				conn.setAutoCommit(true);
   				String newQuery = "INSERT INTO Subscribes(UserEmail, BoardName) VALUES (?, ?)";
   				String userEmail = (String) req.getSession().getAttribute("email");
   				query = conn.prepareStatement(newQuery);
   				query.setString(1, userEmail);
   				query.setString(2, boardName);

   				ResultSet result;
   				result = query.executeQuery();
   				if (result.next() == false){
   					out.println("Subscribe failed, board name is invalid. Please try again");
   					drawSubscribe(req, out);
   				}
   				else{
   					out.println("You are now subscribed to board " + boardName);
   				
   				}
   			}catch(SQLException e){
               out.println("Subscribe failed, board name is invalid. Please try again");
               drawSubscribe(req, out);
   				System.err.print("Catch with executing query");
   				System.err.print("\n" + e);
   			}
   		}catch(SQLException e){
           
   			System.err.println("Large catch");
   			System.err.print(e);
   		}


   }

   public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
   {
   		res.setContentType("text/html");
   		PrintWriter out = res.getWriter();

   		drawHeader(req, out);
   		if (req.getParameter("Subscribe") != null){
            
   			drawSubscribe(req, out);
   		}
   		else{
            
   			subscribeQuery(req, out, res);
   		}
   		drawFooter(req, out);
   }
}