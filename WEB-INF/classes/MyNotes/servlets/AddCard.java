
package MyNotes.servlets;
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import MyNotes.servlets.*;
import MyNotes.utils.*;


public class AddCard extends HttpServlet
{
    public AddCard()
    {
        super();
    }


    public void drawUpdateMessage(HttpServletRequest req, PrintWriter out)
    {
        String board_name  = "CS460";
        String task_name = "Complete assignment 8";
        int creationID = 1;
        int day = 10;
        String month  = "December";
        int year = 2013;
        String description = "Create a really cool program. It's so cool we've started it the day we got the assignment and will finish a week early.";

        out.println("<p><b>Board Name:</b>  " + board_name + "</p>");
        out.println("<p><b>Task Name:</b>  " + task_name + "</p>");
        out.println("<p><b>CreationID:</b>  " + creationID + "</p>");
        out.println("<p><b>Description:</b>  " + description + "</p>");
        out.println("<p><b>Day:</b>  " + day + "</p>");
        out.println("<p><b>Month:</b>  " + month + "</p>");
        out.println("<p><b>Year:</b> " + year + "</p>");

        out.println("<br>");

        out.println("<form name=\"MainMenu\" action=LoginServlet>");
        out.println("<input type=submit name=\"MainMenu\" value=\"MainMenu\">");
        out.println("</form>");

        out.println("<br>");

        out.println("<form name=\"logout\" action=index.html>");
        out.println("<input type=submit name=\"logoutMyNotes\" value=\"Logout\">");
        out.println("</form>");
    }


    public void drawHeader(HttpServletRequest req, PrintWriter out) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Activity Addition</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<p>");
        out.println("<center>");
        out.println("<font face=\"Arial, Helvetica, sans-serif\" >");
        out.println("<font color=\"#000066\">");
        out.println("<center>\n<font size=7><strong>MyNotes</strong></font></br>");
        out.println("<font size=4>MyNotes: a UA Project Management Program</font>");
        out.println("</center>\n<font size=4><hr color=\"#000066\">");
        out.println("Add new card </b><br></font>");
        out.println("</font>");

        out.println("<hr>");
    }


    public void drawFooter(HttpServletRequest req, PrintWriter out)
    {
        out.println("</center>");
        out.println("</p>");
        out.println("</body>");
        out.println("</html>");
    }


    public void drawAddCardInformationMenu(HttpServletRequest req, PrintWriter out)
    {
        out.println("<form name=\"AddCard\" action=AddCard method=get>");
        out.println("<font size=3 face=\"Arial, Helvetica, sans-serif\" color=\"#000066\">");
        out.println("<p>");
        out.println("<b>Board Name:</b>");
        out.println("<input type=text name=\"boardname\">");
        out.println("<br>");
        out.println("</p>");

        out.println("<p>");
        out.println("<b>Task Name: </b>");
        out.println("<input type=text name=\"taskname\">");
        out.println("<br>");
        out.println("</p>");

        out.println("<p>");
        out.println("<b>Description: </b>");
        out.println("<input type=text name=\"description\">");
        out.println("<br>");
        out.println("</p>");

        out.println("<p>");
        out.println("<b>Day:</b>");
        out.println("<input type=int name=\"day\">");
        out.println("<br>");
        out.println("</p>");

        out.println("<p>");
        out.println("<b>Month:</b>");
        out.println("<input type=text name=\"month\">");
        out.println("<br>");
        out.println("</p>");

        out.println("<p>");
        out.println("<b>Year:</b>");
        out.println("<input type=int name=\"year\">");
        out.println("<br>");
        out.println("</p>");


        out.println("<table>");
        out.println("<tr>");
        out.println("<td>");
        out.println("<input type=submit name=\"Submit\" value=\"Insert\">&nbsp&nbsp");
        out.println("</td>");
        out.println("</tr>");

        out.println("</form>");

        out.println("<tr>");
        out.println("<td>");
        out.println("<form name=\"Cancel\" action=AddCard method=get>");
        out.println("<input type=submit name=\"Cancel\" value=\"Cancel\">&nbsp&nbsp");
        out.println("</form>");
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>");
        out.println("<form name=\"MainMenu\" action=LoginServlet>");
        out.println("<input type=submit name=\"MainMenu\" value=\"Return to Main Menu\">");
        out.println("</form>");
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>");
        out.println("<form name=\"logout\" action=index.html>");
        out.println("<input type=submit name=\"logoutMyNotes\" value=\"Logout\">");
        out.println("</form>");
        out.println("</p>");
        out.println("</td>");
        out.println("</tr>");

        out.println("</table>");
        out.println("<br><br><br>");
    }


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        drawHeader(req,out);

        if(req.getParameter("Submit") != null)
        {
            drawUpdateMessage(req,out);

            String boardName ="";
            String taskName = "";
            String description = "";
            int day = 0;
            int month = 0;
            int year = 0;
            String newQuery = "";
            int creationID = -1;

            boardName = req.getParameterValues("boardname")[0];
            taskName = req.getParameterValues("taskname")[0];
            description = req.getParameterValues("description")[0];
            day = Integer.parseInt(req.getParameterValues("day")[0]);
            month = Integer.parseInt(req.getParameterValues("month")[0]);
            year = Integer.parseInt(req.getParameterValues("year")[0]);

            OracleConnect oracle = new OracleConnect();
            Connection conn;
            PreparedStatement query = null;
            try{
                try{
                    Class.forName("oracle.jdbc.OracleDriver");
                }catch(ClassNotFoundException e){
                    System.err.print(e);
                }
                conn = DriverManager.getConnection(oracle.connect_string, oracle.user_name, oracle.password);
                if (conn == null)
                    throw new IOException("getConnection failed");
                try{
                    conn.setAutoCommit(true);
                    //need to get creation ID from Board
                    out.println("Connection Made");
                    newQuery = "SELECT CreationID FROM Board WHERE BoardName = ?";

                    query = conn.prepareStatement(newQuery);
                    ResultSet result;
                    result = creationQuery.executeQuery();
                    if (result.next() == false){
                        out.println("Board does not exist");
                    }
                    else{
                        creationID = result.getInt(1);
                        out.println("Board exists");
                    }
                }catch(SQLException excep){
                    System.err.print("CreationID catch");
                    System.err.print(excep);
                }
                //need to insert into board
                if (creationID != -1){
                    String insertCard = "INSERT INTO Card (BoardName, TaskName, CreationID, Description, DeadlineDay, DeadlineMonth, DeadlineYear) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try{
                        

                        query = conn.prepareStatement(insertCard);
                        query.setString(1, boardName);
                        query.setString(2, taskName);
                        query.setInt(3, creationID);
                        query.setString(4, description);
                        query.setInt(5, day);
                        query.setInt(6, month);
                        query.setInt(7, year);
                        query.executeUpdate();
                        conn.commit();
                        out.println("Card added!");
                    } catch(SQLException e){
                        if (e.getSQLState().equals("23000")){
                            out.println("Card already exists!");

                        }
                        else if (conn != null){
                            try{
                                System.err.print("Transaction is being rolled back\n");
                                conn.rollback();
                            }catch(SQLException excep){
                                System.err.print("ERR");
                            }
                        }

                    }
                }
            }catch(SQLException excep){
                System.err.print("ERR ON LARGE CATCH");
            }

        }
        else
        {
            drawAddCardInformationMenu(req,out);
        }

        drawFooter(req,out);



    }
}
