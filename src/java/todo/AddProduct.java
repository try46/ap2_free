/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author try
 */
@WebServlet(name = "AddProduct", urlPatterns = {"/todo/AddProduct"})
public class AddProduct extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    Connection con = null;
    Statement stmt = null;
    PreparedStatement ps = null;
    boolean debug = false;
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet AddProduct</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet AddProduct at " + request.getContextPath() + "</h1>");
      HttpSession session=request.getSession(false);
      if (session==null) {
        out.println("このページを閲覧するにはログインが必要です"+"<br>");
        out.println("<p><a href=\"ClerkLogin.html\">ログインページに戻る</a></p>");
      }else{
      /**
       * データベース接続処理
       */
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      String driverUrl = "jdbc:derby://localhost:1527/todo";
      con = DriverManager.getConnection(driverUrl, "db", "db");
      stmt = con.createStatement();
      request.setCharacterEncoding("UTF-8");
      /**
       * HTMLから情報を取得
       */
      String ProductName = request.getParameter("Product_Name");
      int ProductPrice = Integer.parseInt(request.getParameter("Product_Price"));
      int ProductCount = Integer.parseInt(request.getParameter("Product_Count"));
      /**
       * デバック用メッセージ
       */
      if (debug == true) {
        out.println(ProductName);
        out.println(ProductPrice);
        out.println(ProductCount);
      }
      if (ProductName == null || ProductCount == 0 || ProductPrice == 0) {
        out.println("未入力の個所があるか数が不正です。");
        out.println("<p><a href=\"AddProduct.html\">商品情報追加ページに戻る</a></p>");
      }
      String sql2 = "insert into Product (Product_Name,Product_Price,Product_Count) values (?,?,?)";
      ps = con.prepareStatement(sql2);
      ps.setString(1, ProductName);
      ps.setInt(2, ProductPrice);
      ps.setInt(3, ProductCount);
      int count = ps.executeUpdate();
      /**
       * データベースに挿入した情報を表示する所
       */
      String sql1 = "select * from Product";
      ResultSet rs = stmt.executeQuery(sql1);
      int id = 0;
      String name = null;
      int Productcount = 0;
      int stock = 0;
      ArrayList<Product> plist = new ArrayList<>();
      while (rs.next()) {
        Product product = new Product(id, name, Productcount, stock);
        id = rs.getInt("Product_Id");
        name = rs.getString("Product_Name");
        Productcount = rs.getInt("Product_Price");
        stock = rs.getInt("Product_Count");
        plist.add(product);
      }
      rs.close();
      Product product = new Product(id, name, Productcount, stock);
      out.println(product + "<br>");
      out.println("<p><a href=\"AddProduct.html\">戻る</a></p>");
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
