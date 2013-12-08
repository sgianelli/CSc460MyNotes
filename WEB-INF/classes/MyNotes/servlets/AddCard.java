package MyNotes.servlets;
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import MyNotes.servlets.*;


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

      if(req.getParameter("Submit") == null)
      {
         drawAddCardInformationMenu(req,out);
      }
      else
      {
         drawUpdateMessage(req,out);
      }

      drawFooter(req,out);
   }
}