<%-- 
    Document   : Clerk
    Created on : 2017/12/30, 0:53:03
    Author     : try
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="todo.Clerk"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>検索結果</title>
  </head>
  <body>
    <h3>対象の検索結果です</h3>
     <table border="5" >
      <tr>
        <td>店員ID</td>
        <td>店員名</td>
        <td>店員用pass</td>
      </tr>
      <%
        List<Clerk> clist
                = (ArrayList< Clerk>) request.getAttribute("clist");
        for (Clerk clerk : clist) {
      %>
      <tr>
        <td><%=clerk.getClerk_Id()%></td>
        <td><%=clerk.getClerk_Name()%></td>
        <td><%=clerk.getClerk_Pass()%></td>
      </tr>
      <%
        }
      %>
    </table>
     <p><a href="Clerk.html">店員用ページに戻る</a></p>
  </body>
</html>
