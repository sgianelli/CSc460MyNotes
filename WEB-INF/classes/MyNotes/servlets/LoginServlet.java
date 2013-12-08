package MyNotes.servlets;
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import MyNotes.servlets.*;
import MyNotes.utils.*;
import java.sql.*;

public class LoginServlet extends HttpServlet
{
    public LoginServlet()
    {
        super();
    }

    public void drawHeader(HttpServletRequest req, PrintWriter out)
    {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>MyNotes logged in</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<p>");
        out.println("<center>");
        out.println("<font size=7 face=\"Arial, Helvetica, sans-serif\" color=\"#000066\">");
        out.println("<center>\n<strong>MyNotes</strong></br>");
        out.println("<font size=4>MyNotes: a UA Project Management Program");

        if (req.getSession().getAttribute("username") != null)
            out.println("<font size=4></br>Hello, " + req.getSession().getAttribute("username") + "!");

        out.println("</center>\n<hr color=\"#000066\">");
        out.println("<br><br>");

    }

    public void drawFooter(HttpServletRequest req, PrintWriter out)
    {
        out.println("</center>");
        out.println("</p>");
        out.println("</body>");
        out.println("</html>");
    }


    private void drawActiveOptions(HttpServletRequest req, PrintWriter out)
    {

        out.println("<br>");

        out.println("<form name=\"AddCard\" action=AddCard method=get>");
        out.println("<input type=submit name=\"AddCard\" value=\"Add a Card\">");
        out.println("</form>");

        out.println("<br>");

        out.println("<form name=\"findBoards\" action=FindBoards method=get>");
        out.println("<input type=submit name=\"findBoard\" value=\"Find boards with at least a number of subscribers.\">");
        out.println("</form>");

        out.println("<br>");

        out.println("<form name=\"CardShare\" action=./JSP/SharedAssignment.jsp>");
        out.println("<input type=submit name=\"SharedAssignment\" value=\"Which users are assigned to the same card?\">");
        out.println("</form>");

        out.println("<br>");

        out.println("<form name=\"logout\" action=/MyNotes/JSP/Logout.jsp>");
        out.println("<input type=submit name=\"logoutMyNotes\" value=\"Log out\">");
        out.println("</form>");
    }

    private void drawFailOptions(HttpServletRequest req, PrintWriter out, boolean correctEmail)
    {
        out.println("<font size=5 face=\"Arial,Helvetica\">");

        if (correctEmail)
            out.println("<b>Error: Enter the correct username.</b></br>");
        else
            out.println("<b>Error: e-mail does not exist.</b></br>");

        out.println("<font size=4>");
        out.println("<b>MyNotes: a UA Project Management Program</b><br></font>");
        out.println("</font>");

        out.println("<hr");
        out.println("<br><br>");

        out.println("<form name=\"logout\" action=index.html>");
        out.println("<input type=submit name=\"home\" value=\"Return to Main Menu\">");
        out.println("</form>");

        out.println("<br>");
    }

    public void drawLoginSuccess(HttpServletRequest req, PrintWriter out)
    {
        drawHeader(req,out);
        drawActiveOptions(req,out);
        drawFooter(req,out);
    }

    public void drawLoginFail(HttpServletRequest req, PrintWriter out, boolean correctEmail)
    {
        drawHeader(req,out);
        drawFailOptions(req,out,correctEmail);
        drawFooter(req,out);
    }

    private boolean checkEmail(String email) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(OracleConnect.connect_string, OracleConnect.user_name, OracleConnect.password);

            if (conn == null)
                throw new Exception("getConnection failed");

            try {
                conn.setAutoCommit(true);

                PreparedStatement emailCheck = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE UserEmail=?");
                emailCheck.setString(1, email);

                ResultSet rs = emailCheck.executeQuery();

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

    private boolean checkUsername(String email, String username) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(OracleConnect.connect_string, OracleConnect.user_name, OracleConnect.password);

            if (conn == null)
                throw new Exception("getConnection failed");

            try {
                conn.setAutoCommit(true);

                PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE UserEmail=? AND UserName=?");
                stmt.setString(1, email);
                stmt.setString(2, username);

                ResultSet rs = stmt.executeQuery();

                rs.next();
                Integer count = rs.getInt(1);

                return count == 1;
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

    private void storeCredentials(HttpServletRequest req, String email, String username) {
        HttpSession session = req.getSession();

        session.setAttribute("email", email);
        session.setAttribute("username", username);
    }

    private void redirectOutsiders(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession();

        System.out.println("--- Session: " + session.getAttribute("email") + " -- " + session.getAttribute("username"));

        if (session.getAttribute("email") == null || session.getAttribute("username") == null) {
            session.setAttribute("email", null);
            session.setAttribute("username", null);

            res.setStatus(HttpServletResponse.SC_FOUND);
            res.setHeader("Location", "/MyNotes");
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        if (req.getParameter("MainMenu") != null) {
            redirectOutsiders(req, res);

            drawLoginSuccess(req,out);
        } else {
            String username = req.getParameter("password");
            String email = req.getParameter("email");

            if (username == null || email == null) {
                res.setStatus(HttpServletResponse.SC_FOUND);
                res.setHeader("Location", "/MyNotes");
            } else {
                if (checkEmail(email)) {
                    if (checkUsername(email, username)) {
                        storeCredentials(req, email, username);

                        drawLoginSuccess(req,out);
                    } else
                        drawLoginFail(req,out,true);
                } else
                    drawLoginFail(req,out,false);
            }
        }
    }
}


