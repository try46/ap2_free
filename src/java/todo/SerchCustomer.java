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
import javax.servlet.http.HttpSession;

/**
 *
 * @author try
 */
@WebServlet(name = "SerchCustomer", urlPatterns = {"/todo/SerchCustomer"})
public class SerchCustomer extends HttpServlet {

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
    boolean debug = true;
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet SerchCustomer</title>");
      out.println("</head>");
      out.println("<body>");
      // out.println("<h1>Servlet SerchCustomer at " + request.getContextPath() + "</h1>");
      out.println("<h3>" + "あなたの情報です" + "</h3>");
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      String driverUrl = "jdbc:derby://localhost:1527/todo";
      con = DriverManager.getConnection(driverUrl, "db", "db");
      stmt = con.createStatement();
      request.setCharacterEncoding("UTF-8");
      HttpSession session = request.getSession(false);
      List<Customer> culist = new ArrayList<>();
      if (session == null) {
        out.println("<p>セッションがありません</p>");
        out.println("<p><a href=\"Customer.html>ログインページに戻る</a></p>");
      } else {
        if (debug == true) {
          out.println(session.getAttribute("cname") + "<br>");
          out.println(session.getAttribute("cpass") + "<br>");
        }
        String sql = "select * from Customer where Customer_Name = ? and Customer_Pass=?";
        ps = con.prepareStatement(sql);
        ps.setString(1, (String) session.getAttribute("cname"));
        ps.setString(2, (String) session.getAttribute("cpass"));
        ResultSet rs = ps.executeQuery();
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<td>" + "顧客ID" + "</td>");
        out.println("<td>" + "顧客名" + "</td>");
        out.println("<td>" + "顧客用パスワード" + "</td>");
        out.println("<td>" + "顧客年代" + "</td>");
        out.println("<td>" + "顧客住所" + "</td>");
        out.println("</tr>");
        while (rs.next()) {
          Customer customer = new Customer();
          customer.setCustomer_Id(rs.getInt("Customer_ID"));
          customer.setCustomer_Name(rs.getString("Customer_Name"));
          customer.setCustomer_Pass(rs.getString("Customer_Pass"));
          customer.setCustomer_Age(rs.getString("Customer_Age"));
          customer.setCustomer_Address(rs.getString("Customer_Address"));
          culist.add(customer);
        }
        rs.close();
        for (Customer customer : culist) {
          out.println("<tr>");
          out.println("<td>" + customer.getCustomer_Id() + "</td>");
          out.println("<td>" + customer.getCustomer_Name() + "</td>");
          out.println("<td>" +customer.getCustomer_Pass() + "</td>");
          out.println("<td>" +customer.getCustomer_Age() + "</td>");
          out.println("<td>" + customer.getCustomer_Address()+ "</td>");
          out.println("</tr>");
        }
        out.println("</table>");
      }

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
