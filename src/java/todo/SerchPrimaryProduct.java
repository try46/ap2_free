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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author try
 */
@WebServlet(name = "SerchPrimaryProduct", urlPatterns = {"/todo/SerchPrimaryProduct"})
public class SerchPrimaryProduct extends HttpServlet {

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
      out.println("<title>Servlet SerchPrimaryProduct</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet SerchPrimaryProduct at " + request.getContextPath() + "</h1>");
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      String driverUrl = "jdbc:derby://localhost:1527/todo";
      con = DriverManager.getConnection(driverUrl, "db", "db");
      stmt = con.createStatement();
      request.setCharacterEncoding("UTF-8");
      /**
       * HTMLから情報を取得
       */
      String Product_Id = request.getParameter("Product_Id");
      /**
       * デバック用メッセージ
       */
      if (debug == true) {
        out.println(Product_Id);
      }
      String sql = "select * from Product where Product_Id=?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, Integer.parseInt(Product_Id));
      ResultSet rs = ps.executeQuery();
      List<Product> plist = new ArrayList<>();
      while (rs.next()) {
        Product product = new Product();
        product.setProduct_Id(rs.getInt("Product_ID"));
        product.setProduct_Name(rs.getString("Product_Name"));
        product.setProduct_Price(rs.getInt("Product_Price"));
        product.setProduct_Count(rs.getInt("Product_Count"));
        plist.add(product);
      }
      rs.close();
      request.setAttribute("plist", plist);
      RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");
      dispatcher.forward(request, response);
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          throw new ServletException(e);
        }
      }
      if (con != null) {
        try {
          con.close();
        } catch (SQLException e) {
          throw new ServletException(e);
        }
      }
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          throw new ServletException(e);
        }
      }
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
