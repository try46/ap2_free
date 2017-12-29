<%-- 
    Document   : Customer
    Created on : 2017/12/30, 1:18:55
    Author     : try
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="todo.Customer"%>
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
        <td>顧客ID</td>
        <td>顧客名</td>
        <td>顧客用パスワード</td>
        <td>顧客年代</td>
        <td>顧客住所</td>
      </tr>
      <%
        List<Customer> culist
                = (ArrayList<Customer>) request.getAttribute("culist");
        for (Customer customer : culist) {
      %>
      <tr>
        <td><%=customer.getCustomer_Id()%></td>
        <td><%=customer.getCustomer_Name()%></td>
        <td><%=customer.getCustomer_Pass()%></td>
        <td><%=customer.getCustomer_Age()%></td>
        <td><%=customer.getCustomer_Address()%></td>
      </tr>
      <%
        }
      %>
    </table>
     <p><a href="Clerk.html">店員用ページに戻る</a></p>
  </body>
</html>
