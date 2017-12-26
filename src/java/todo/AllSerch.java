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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author try
 */
@WebServlet(name = "AllSerch", urlPatterns = {"/todo/AllSerch"})
public class AllSerch extends HttpServlet {

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
    PreparedStatement ps = null;
    Statement stmt = null;
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet AllSerch</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet AllSerch at " + request.getContextPath() + "</h1>");
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      String driverUrl = "jdbc:derby://localhost:1527/todo";
      con = DriverManager.getConnection(driverUrl, "db", "db");
      stmt = con.createStatement();
      /**
       * Clerk customer productのデータを取得するsql文
       */
      String sql1 = "select * from clerk";
      String sql2 = "select * from customer";
      String sql3 = "select * from product";
      ResultSet rs1 = stmt.executeQuery(sql1);
      ResultSet rs2 = stmt.executeQuery(sql2);
      ResultSet rs3 = stmt.executeQuery(sql3);
      
      //Product型のArrayListを作成
      List<Product> plist = new ArrayList<>();
      //Clerk型のArrayListを作成
      List<Clerk> clist = new ArrayList<>();
      //Customer型のArrayListを作成
      List<Customer> culist = new ArrayList<>();
      while (rs1.next()&&rs2.next()&&rs3.next()) {        
        Product p=new Product();
        p.setProduct_Id(rs1.getInt("Product_ID"));
        p.setProduct_Name(rs1.getString("Product_Name"));
        p.setProduct_Price(rs1.getInt("Product_Price"));
        p.setProduct_Count(rs1.getInt("Product_Count"));
        plist.add(p);
        Clerk clerk=new Clerk();
        
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }finally {
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
