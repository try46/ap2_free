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
@WebServlet(name = "PrimarySerchClerk", urlPatterns = {"/todo/PrimarySerchClerk"})
public class PrimarySerchClerk extends HttpServlet {

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
      out.println("<title>Servlet PrimarySerchClerk</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet PrimarySerchClerk at " + request.getContextPath() + "</h1>");

      Class.forName("org.apache.derby.jdbc.ClientDriver");
      String driverUrl = "jdbc:derby://localhost:1527/todo";
      con = DriverManager.getConnection(driverUrl, "db", "db");
      stmt = con.createStatement();
      request.setCharacterEncoding("UTF-8");
      /**
       * HTMLから情報を取得
       */
      String Clerk_Id = request.getParameter("Clerk_Id");
      /**
       * デバック用メッセージ
       */
      if (debug == true) {
        out.println(Clerk_Id);
      }
      /**
       * 未入力の個所がある場合入力画面へのリンクを表示
       */
      if (Clerk_Id == null) {
        out.println("未入力の個所があります。");
        out.println("<p><a href=\"SerchClerk.html\">店員情報検索ページに戻る</a></p>");
      }
      String sql = "select * from Clerk where Clerk_Id=?";
      ps=con.prepareStatement(sql);
      ps.setInt(1, Integer.parseInt(Clerk_Id));
      ResultSet rs=ps.executeQuery();
      List<Clerk> clist = new ArrayList<>();
      while (rs.next()) {        
        Clerk clerk = new Clerk();
        clerk.setClerk_Id(rs.getInt("Clerk_Id"));
        clerk.setClerk_Name(rs.getString("Clerk_Name"));
        clerk.setClerk_Pass(rs.getString("Clerk_Pass"));
        clist.add(clerk);
      }
      rs.close();
      /**
       * JSPへのフォワード処理
       */
      request.setAttribute("clist", clist);
      RequestDispatcher dispatcher=request.getRequestDispatcher("Clerk.jsp");
      dispatcher.forward(request, response);
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
