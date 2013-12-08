package MyNotes.servlets;
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import MyNotes.servlets.*;
import MyNotes.utils.*;


public class AddProfileInformation extends HttpServlet
{
    public AddProfileInformation()
    {
        super();
    }

    private boolean emailInUse(String email) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(OracleConnect.connect_string, OracleConnect.user_name, OracleConnect.password);

            if (conn == null)
                throw new Exception("getConnection failed");

            try {
                conn.setAutoCommit(true);

                PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE UserEmail=?");
                stmt.setString(1, email);

                ResultSet rs = stmt.executeQuery();

                rs.next();
                Integer emailCount = rs.getInt(1);

                return emailCount == 1;
            } finally {
                if (conn != null)
                    conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return false;
    }

    private void insertNewUser(String email, String username) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(OracleConnect.connect_string, OracleConnect.user_name, OracleConnect.password);

            if (conn == null)
                throw new Exception("getConnection failed");

            try {
                conn.setAutoCommit(true);

                PreparedStatement stmt = conn.prepareStatement("INSERT INTO Users(UserEmail, UserName) VALUES (?, ?)");
                stmt.setString(1, email);
                stmt.setString(2, username);

                stmt.executeUpdate();
            } finally {
                if (conn != null)
                    conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void storeCredentials(HttpServletRequest req, String email, String username) {
        HttpSession session = req.getSession();

        session.setAttribute("email", email);
        session.setAttribute("username", username);
    }

    public void drawUpdateMessage(HttpServletRequest req, PrintWriter out)
    {
        String email = req.getParameter("email");
        String username = req.getParameter("user");

        boolean exists = emailInUse(email);

        if (!exists) {
            insertNewUser(email, username);

            storeCredentials(req, email, username);

            out.println("<p><b>New user added!</b></p>");

            out.println("<br>");

            out.println("<form name=\"MainMenu\" action=LoginServlet>");
            out.println("<input type=submit name=\"MainMenu\" value=\"MainMenu\">");
            out.println("</form>");

            out.println("<br>");

            out.println("<form name=\"logout\" action=/MyNotes/JSP/Logout.jsp>");
            out.println("<input type=submit name=\"logoutMyNotes\" value=\"Logout\">");
            out.println("</form>");
        } else {
            out.println("<p><b>Error: User already exists!</b></p>");

            out.println("<br>");

            out.println("<form name=\"goback\" action=AddProfileInformation>");
            out.println("<input type=submit name=\"addProfileInformation\" value=\"Go Back\">");
            out.println("</form>");
        }
    }


    public void drawHeader(HttpServletRequest req, PrintWriter out) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>User Addition</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<p>");
        out.println("<center>");
        out.println("<font size=7 face=\"Arial, Helvetica, sans-serif\" color=\"#000066\">");
        out.println("<center>\n<strong>MyNotes</strong></br></font>");
        out.println("<font size=4>MyNotes: a UA Project Management Program");
        out.println("</center>\n<hr color=\"#000066\">");
        out.println("Add new user </b><br></font>");

        out.println("<hr>");
    }


    public void drawFooter(HttpServletRequest req, PrintWriter out)
    {
        out.println("</center>");
        out.println("</p>");
        out.println("</body>");
        out.println("</html>");
    }


    public void drawAddProfileInformationMenu(HttpServletRequest req, PrintWriter out)
    {
        out.println("<form name=\"AddProfileInformation\" action=AddProfileInformation method=get>");
        out.println("<font size=3 face=\"Arial, Helvetica, sans-serif\" color=\"#000066\">");
        out.println("<p>");
        out.println("<b>Email Address:</b>");
        out.println("<input type=text name=\"email\">");
        out.println("<br>");
        out.println("</p>");

        out.println("<p>");
        out.println("<b>User: </b>");
        out.println("<input type=text name=\"user\">");
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
        out.println("<form name=\"Cancel\" action=index.html method=get>");
        out.println("<input type=submit name=\"Cancel\" value=\"Cancel\">&nbsp&nbsp");
        out.println("</form>");
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
            drawAddProfileInformationMenu(req,out);
        }
        else
        {
            drawUpdateMessage(req,out);
        }

        drawFooter(req,out);
    }
}
