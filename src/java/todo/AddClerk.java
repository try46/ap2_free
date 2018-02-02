/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
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
@WebServlet(name = "AddClerk", urlPatterns = {"/todo/AddClerk"})
public class AddClerk extends HttpServlet {

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
      out.println("<title>Servlet AddClerk</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet AddClerk at " + request.getContextPath() + "</h1>");
       HttpSession session=request.getSession(false);
      if (session==null) {
        out.println("このページを閲覧するにはログインが必要です"+"<br>");
        out.println("<p><a href=\"ClerkLogin.html\">ログインページに戻る</a></p>");
      }else{
      /**
       * 54~57 データベース接続処理
       */
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      String driverUrl = "jdbc:derby://localhost:1527/todo";
      con = DriverManager.getConnection(driverUrl, "db", "db");
      stmt = con.createStatement();
      request.setCharacterEncoding("UTF-8");
      /**
       * HTMLから情報を取得
       */
      String clerkid = request.getParameter("Clerk_Id");
      String clerkpass = request.getParameter("Clerk_Pass");
      String chekclerkpass = request.getParameter("CheckClerk_Pass");
      /**
       * パスワードの一致の確認一致していない 若しくはすべての情報が入力されていない場合 店員情報追加ページへのリンクを表示する
       */
      if (clerkid == null || clerkpass == null || chekclerkpass == null) {
        out.println("未入力の個所があります。");
        out.println("<p><a href=\"AddClerk.html\">店員情報追加ページに戻る</a></p>");
      }
      if (!clerkpass.equals(chekclerkpass)) {
        out.println("パスワードが一致しません" + "<br>");
        out.println("<p><a href=\"AddClerk.html\">店員情報追加ページに戻る</a></p>");
      } else {
      
      /**
       * Base64でパスワード(clerkpass)をエンコード
       */
      String sourse = clerkpass;
      Charset charset = StandardCharsets.UTF_8;
      clerkpass = Base64.getEncoder().encodeToString(sourse.getBytes(charset));
      /**
       * sql文(insert)の発行
       */
      String sql2 = "insert into Clerk (Clerk_Name,Clerk_Pass) values (?,?)";
      ps = con.prepareStatement(sql2);
      ps.setString(1, clerkid);
      ps.setString(2, clerkpass);
      int count = ps.executeUpdate();
      /**
       * データベースに挿入した情報を表示する所
       */
      String sql = "select * from Clerk";
      ResultSet rs = stmt.executeQuery(sql);
      ArrayList<Clerk> clistList = new ArrayList<>();
      int id = 0;
      String name = null, pass = null;
      while (rs.next()) {
        Clerk clerk = new Clerk(id, name, clerkpass);
        id = rs.getInt("Clerk_Id");
        name = rs.getString("Clerk_Name");
        pass = rs.getString("Clerk_Pass");
        clistList.add(clerk);
      }
      Clerk clerk = new Clerk(id, name, clerkpass);
      out.println(clerk + "<br>");
      out.println("<p><a href=\"Clerk.html\">戻る</a></p>");
      rs.close();
      }
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
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException es) {
          throw new ServletException(es);
        }
      }
      if (con != null) {
        try {
          con.close();
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
