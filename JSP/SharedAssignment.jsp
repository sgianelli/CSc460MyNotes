<%@ page language="java" contentType="text/html" %>

<html>

    <head><title>MyNotes: cards shared by multiple users</title></head>
    <body link=#f0f0ff alink vlink=#f0f0ff>
        <p>
            <center>
            <font size=7 face="Arial, Helvetica, sans-serif" color="#000066">
            <b>MyNotes</b><br>
            <font size=4>
                MyNotes: a UA Project Management Program</br>
            </font>
            </font>
            <hr>
            <br>

            <b>Users who share the same card:</b>
            <br>
        <%
                String name1 = "Jean-Pierre";
                String name2 = "Rick Snodgrass";
		String card1 = "Learn French";
                String name3 = "Bill Gates";
                String name4 = "Steve Jobs";
		String card2 = "Make the Big Bucks";
        %>

	    <table border="1">
	      <tr> 
	      <td> <b> Card Task Name </b> </td>
	      <td> <b> User Name </b> </td>
        <%
	      // Card 1
	      out.println("<tr>");
	      out.println("<td> " + card1 + "</td>");
	      out.println("<td> " + name1 + " </td>");

	      out.println("<tr>");
	      out.println("<td> </td>");
	      out.println("<td> " + name2 + " </td>");

	      // Card 2
	      out.println("<tr>");
	      out.println("<td> " + card2 + "</td>");
	      out.println("<td> " + name3 + " </td>");

	      out.println("<tr>");
	      out.println("<td> </td>");
	      out.println("<td> " + name4 + " </td>");
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
                <form name="logout" action=../index.html>
                <input type=submit name="logoutMyNotes" value="Logout">
                </form>
                </td>
                </tr>
                </table>


            </center>
        </p>
    </body>
</html>


