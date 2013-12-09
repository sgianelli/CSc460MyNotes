package MyNotes.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import MyNotes.servlets.*;
import java.sql.*;
import MyNotes.utils.*;

public class AddBoard extends HttpServlet{
	public AddBoard(){
		super();
	}

	public void drawHeader(HttpServletRequest req, PrintWriter out)
   {
      out.println("<html>");
      out.println("<head><title>Add a new Board</title></head>");

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

   public void drawAddBoard(HttpServletRequest req, PrintWriter out){
   		out.println("<form name=\"addBoard\" action=AddBoard method=get>");
   		out.println("Enter a new board name: ");
   		out.println("<input type=text size=64 name=\"boardName\">");
   		out.println("<input type=submit name=\"addBoards\" value=\"Add Board\">");
   		out.println("</form>");
   }

   // public void addBoardQuery(HttpServletRequest req, PrintWriter out, HttpServletResponse response) throws IOException
   // {
   // 		String boardName = req.getParameter("boardName");
   // 		OracleConnect oracle = new OracleConnect();
   // 		Connection conn;
   // 		PreparedStatement query = null;

   // 		try{
   // 			try{
   // 				Class.forName("oracle.jdbc.OracleDriver");
   // 			}catch(ClassNotFoundException e){
   // 				System.err.print(e);
   // 			}

   // 			conn = DriverManager.getConnection(OracleConnect.connect_string, OracleConnect.user_name, OracleConnect.password);
   // 			if (conn == null){
   // 				throw new IOException("getConnection failed");
   // 			}

   // 			try{
   // 				int creationID = 1;
   // 				conn.setAutoCommit(true);
   // 				String maxIDQuery = "SELECT MAX(CreationID) FROM Creation";
   // 				query = conn.prepareStatement(maxIDQuery);
   // 				ResultSet result;

   // 				result = query.executeQuery();
   // 				if (result.next() == false){
   // 					creationID =1;
   // 				}
   // 				else{
   // 					creationID = result.getInt(1) + 1;
   // 				}

   // 				String userEmail = (String) req.getSession().getAttribute("email");

   // 				String creationQuery = "INSERT INTO Creation (UserEmail, CreationID) VALUES (?, ?)";
   // 				query = conn.prepareStatement(creationQuery);
   // 				query.setString(1, userEmail);
   // 				query.setInt(2, creationID);
   // 				result = query.executeQuery();
   // 				if (result.next() == false){
   // 					System.err.println("issue with creation");
   // 					drawAddBoard(req, out);
   // 				}
   // 				else{
   // 					try{
   // 						String boardCreation = "INSERT INTO Board(BoardName, CreationID) VALUES (?, ?)";
   // 						query = conn.prepareStatement(boardCreation);
   // 						query.setString(1, boardName);
   // 						query.setInt(2, creationID);
   // 						result = query.executeQuery();
   // 						if (result.next() == false){
   // 							out.println("AddBoard failed, board name is not valid. Please try again");
   // 							drawAddBoard(req, out);
   // 						}
   // 						else{
   // 							out.println("Your board " + boardName + " has now been created!");
   // 						}
   // 					}catch(SQLException e){
   // 						out.println("AddBoard failed, board name is not valid. Please try again");
   // 						drawAddBoard(req, out);
   // 						System.err.print(e);
   // 					}
   					
   // 				}

   // 			}catch{
   // 				System.err.println("Query catch");
   // 			System.err.println(e);
   // 			}

   // 		}catch(SQLException e){
   // 			System.err.println("Large catch");
   // 			System.err.println(e);
   // 		}

   // }
   // public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
   // {
   // 		res.setContentType("text/html");
   // 		PrintWriter out = res.getWriter();

   // 		drawHeader(req, out);
   // 		if (req.getParameter("AddBoard") != null){
            
   // 			drawAddBoard(req, out);
   // 		}
   // 		else{
            
   // 			addBoardQuery(req, out, res);
   // 		}
   // 		drawFooter(req, out);
   // }

}