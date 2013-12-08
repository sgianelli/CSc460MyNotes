<%@ page language="java" contentType="text/html" %>
<%@ page import="MyNotes.utils.*,java.sql.*,java.utils.*" %>

<%
    if (session.getAttribute("email") == null || session.getAttribute("username") == null) {
        session.setAttribute("email", null);
        session.setAttribute("username", null);

        response.setStatus(HttpServletResponse.SC_FOUND);
        response.setHeader("Location", "/MyNotes");
    }
%>

<html>

    <head><title>MyNotes: cards shared by multiple users</title></head>
    <body link=#f0f0ff alink vlink=#f0f0ff>
        <p>
            <center>
            <font size=7 face="Arial, Helvetica, sans-serif" color="#000066">
            <b>MyNotes</b><br>
            <font size=4>
                MyNotes: a UA Project Management Program</br>
                Hello, <% out.println(session.getAttribute("username"));%>!</br>
            </font>
            </font>
            <hr>
            <br>

            <b>Users who share the same card:</b>
            <br>
        <%
                String html = "";

                try {
                    Class.forName("oracle.jdbc.OracleDriver");
                    Connection conn = DriverManager.getConnection(OracleConnect.connect_string, OracleConnect.user_name, OracleConnect.password);

                    if (conn == null)
                        throw new Exception("getConnection failed");

                    try {
                        conn.setAutoCommit(true);

                        String query =  "SELECT DISTINCT c.BoardName, c.TaskName, a.UserEmail, u.UserName " +
                                        "FROM Card c " +
                                            "JOIN AssignedTo a " +
                                                "ON c.BoardName=a.BoardName AND c.TaskName=a.TaskName " +
                                            "JOIN Users u " +
                                                "ON u.UserEmail=a.UserEmail " +
                                        "ORDER BY c.BoardName, c.TaskName, u.UserName";

                        PreparedStatement stmt = conn.prepareStatement(query);

                        ResultSet rs = stmt.executeQuery();

                        String currentTask = "";

                        while (rs.next()) {
                            String task = rs.getString(2);

                            if (task.equals(currentTask))
                                task = "";
                            else
                                currentTask = task;

                            String nHtml = "<tr><td>" + task + "</td><td>" + rs.getString(4) + "</td></tr>";
                            html += nHtml;
                        }
                    } finally {
                        if (conn != null)
                            conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
        %>

	    <table border="1">
	      <tr> 
	      <td> <b> Card Task Name </b> </td>
	      <td> <b> User Name </b> </td>
        <%
          out.println(html);
        %>
	      </table>	

                <hr>
                <br><br>

                <table>
                <tr>
                <td>
                <form name="mainmenu" action=../LoginServlet method=get>
                <input type=submit name="MainMenu" value="Main Menu">
                </form>
                </td>
                </tr>
                <tr>
                <td>
                <form name="logout" action=Logout.jsp>
                <input type=submit name="logoutMyNotes" value="Logout">
                </form>
                </td>
                </tr>
                </table>


            </center>
        </p>
    </body>
</html>


