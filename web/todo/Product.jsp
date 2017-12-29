<%-- 
    Document   : Product
    Created on : 2017/12/30, 1:23:45
    Author     : try
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="todo.Product"%>
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
