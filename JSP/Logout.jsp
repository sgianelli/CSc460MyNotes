<%@ page language="java" contentType="text/html" %>
<%@ page import="MyNotes.utils.*,java.sql.*,java.utils.*" %>

<%
    System.out.println("Logging out");

    session.setAttribute("email", null);
    session.setAttribute("username", null);

    response.setStatus(HttpServletResponse.SC_FOUND);
    response.setHeader("Location", "/MyNotes");
%>
