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
@WebServlet(name = "AddCustomer", urlPatterns = {"/todo/AddCustomer"})
public class AddCustomer extends HttpServlet {

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
      out.println("<title>Servlet AddCustomer</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet AddCustomer at " + request.getContextPath() + "</h1>");
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
      String cname = request.getParameter("Customer_Name");
      String cpass = request.getParameter("Customer_Pass");
      String chekcpass = request.getParameter("ChrckCustomer_Pass");
      String cage = request.getParameter("radio");
      String caddress = request.getParameter("address");
      /**
       * パスワードの一致の確認一致していない 若しくはすべての情報が入力されていない場合 顧客情報追加ページへのリンクを表示する
       */
      if (cname == null || cpass == null || chekcpass == null || cage == null || caddress == null) {
        out.println("未入力の個所があります。");
        out.println("<p><a href=\"AddCustomer.html\">顧客情報追加ページに戻る</a></p>");
      } else {
        /**
         * デバック用メッセージ
         */
        if (debug == true) {
          out.println(true);
        }
      }
      if (!cpass.equals(chekcpass)) {
         out.println("パスワードが一致しません" + "<br>");
        out.println("<p><a href=\"AddCustomer.html\">顧客情報追加ページに戻る</a></p>");
        /**
         * デバック用メッセージ
         */
        if (debug == true) {
          out.println(true);
        }
      } else {
        
      
      String source = cpass;
      Charset charset = StandardCharsets.UTF_8;
      cpass = Base64.getEncoder().encodeToString(source.getBytes(charset));
      /**
       * sql文(insert)の発行
       */
      String sql2 = "insert into Customer (Customer_Name,Customer_Pass,Customer_Age,Customer_Address) values (?,?,?,?)";
      ps = con.prepareStatement(sql2);
      ps.setString(1, cname);
      ps.setString(2, cpass);
      ps.setString(3, cage);
      ps.setString(4, caddress);
      int count = ps.executeUpdate();
      /**
       * データベースに挿入した情報を表示する所
       */
      String sql1 = "select * from Customer";
      ResultSet rs = stmt.executeQuery(sql1);
      int id = 0;
      String name;
      String pass = null;
      String age = null;
      String address = null;
      ArrayList<Customer> culist = new ArrayList<>();
      while (rs.next()) {
        Customer customer = new Customer(id, cname, pass, age, address);
        id = rs.getInt("Customer_Id");
        name = rs.getString("Customer_Name");
        pass = rs.getString("Customer_Pass");
        age = rs.getString("Customer_Age");
        address = rs.getString("Customer_Address");
        culist.add(customer);
      }
      rs.close();
      Customer customer = new Customer(id, cname, pass, age, address);
      out.println(customer + "<br>");
      out.println("<p><a href=\"AddCustomer.html\">戻る</a></p>");
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
