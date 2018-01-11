<%-- 
    Document   : AllSerch
    Created on : 2017/12/26, 18:51:57
    Author     : try
--%>

<%@page import="todo.Product"%>
<%@page import="todo.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="todo.Clerk"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>全レコード情報</title>
  </head>
  <body>
    <h3>店員情報一覧</h3>
    <table border="5" >
      <tr>
        <td>店員ID</td>
        <td>店員名</td>
        <td>店員用パスワード</td>
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
    <h3>顧客情報一覧</h3>
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

    <h3>商品情報一覧</h3>
    <table border="5" >
      <tr>
        <td>商品ID</td>
        <td>商品名</td>
        <td>商品価格</td>
        <td>在庫数</td>
      </tr>
      <%
        List<Product> plist
                = (ArrayList<Product>) request.getAttribute("plist");
        for (Product product : plist) {
      %>
      <tr>
        <td><%=product.getProduct_Id()%></td>
        <td><%=product.getProduct_Name()%></td>
        <td><%=product.getProduct_Price()%></td>
        <td><%=product.getProduct_Count()%></td>
      </tr>
      <%
        }
      %>
    </table>
    <p><a href="Clerk.html">店員用ページに戻る</a></p>
  </body>
</html>
