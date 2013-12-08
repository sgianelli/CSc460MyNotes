package MyNotes.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import MyNotes.servlets.*;
import java.sql.*;

public class FindBoards extends HttpServlet
{

   public FindBoards()
   {
      super();
   }

   public void drawHeader(HttpServletRequest req, PrintWriter out)
   {
      out.println("<html>");
      out.println("<head><title>Find Boards</title></head>");

      out.println("<body>");
      out.println("<p>");
      out.println("<center>");
      out.println("<font face=\"Arial, Helvetica, sans-serif\">");
      out.println("<font color=\"#000066\">");
      out.println("<center>\n<font size=7><strong>MyNotes</strong></font></br>");
      out.println("<font size=4>MyNotes: a UA Project Management Program</font>");
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
      out.println("<form name=\"logout\" action=index.html>");
      out.println("<input type=submit name=\"logoutMyNotes\" value=\"Logout\">");
      out.println("</form>");
      out.println("</p>");

      out.println("<br><br>");
      out.println("</center>");
      out.println("</p>");
      out.println("</body>");
      out.println("</html>");
   }


   public void drawGetUser(HttpServletRequest req, PrintWriter out)
   {

      System.out.println("CSC460: in drawGetUser___");

      out.println("<form name=\"userSearch\" action=FindBoards method=get>");
      out.println("Enter max number of subscribers: ");
      out.println("<input type=text size=30 name=\"numUsers\">");
      out.println("<input type=submit name=\"findBoards\" value=\"Find\" >");
      out.println("</form>");

   }



   public void drawShowInfo(HttpServletRequest req, PrintWriter out)
   {
      String numUsers = req.getParameter("numUsers");

      out.println("<p><b>Boards that have at least " + numUsers + " users:</b>");
      /* TODO: Execute the query and print out the results rather than hard coding the results */
      out.println("<p> CS460 TODOs </p>");
      out.println("<p> CS473 TODOs </p>");

   }


   public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
   {
      res.setContentType("text/html");
      PrintWriter out = res.getWriter();

      drawHeader(req,out);

      if(req.getParameter("findBoards") == null)
      {
         drawGetUser(req,out);
      }
      else
      {
         drawShowInfo(req,out);
         System.out.println("CSC460: inside doGet FindCommonPlaces____" + req.getParameter("numUsers") +"___");
      }

      drawFooter(req,out);
   }
}



