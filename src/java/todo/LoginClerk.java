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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Statement;
import javax.servlet.http.HttpSession;

/**
 *
 * @author try
 */
@WebServlet(name = "LoginClerk", urlPatterns = {"/todo/LoginClerk"})
public class LoginClerk extends HttpServlet {

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

    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>LoginClerk</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet LoginClerk at " + request.getContextPath() + "</h1>");
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      String driverUrl = "jdbc:derby://localhost:1527/todo";
      con = DriverManager.getConnection(driverUrl, "db", "db");
      stmt = con.createStatement();

      request.setCharacterEncoding("UTF-8");
      String cid = request.getParameter("Clerk_id");
      String cpass = request.getParameter("Clerk_pass");
      /*
             *sqlの発行　 
       */
      String sql = "select * from Clerk where Clerk_name=? and Clerk_pass =?";
      ps = con.prepareStatement(sql);
      ps.setString(1, cid);
      ps.setString(2, cpass);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        out.println("ログインしました" + "<br>");
        HttpSession session = request.getSession(false);
        if (session == null) {
          session = request.getSession(true);
          session.setAttribute("cid", cid);
        } else {

        }
        out.println(Base());
        out.println("<p><a href=\"Clerk.html\">管理者用ページへ</a></p>");
      } else {
        out.println("ID又はパスワードのどちらか又は両方が間違っています。" + "<br>");
        out.println("<p><a href=\"ClerkLogin.html\">ログインページに戻る</a></p>");
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

  private boolean Base() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

  }

}
