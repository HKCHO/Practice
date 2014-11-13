package java02.test17.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao02 {
  public ProductDao02() {}

  public Product selectOne(int no) {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    try {
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/studydb", 
          "study", 
          "study");
      stmt = con.createStatement();
      rs = stmt.executeQuery(
          "SELECT PNO,PNAME,QTY,MKNO FROM PRODUCTS"
          + " WHERE PNO=" + no);
      if (rs.next()) {
        Product product = new Product();
        product.setNo(rs.getInt("PNO"));
        product.setName(rs.getString("PNAME"));
        product.setQuantity(rs.getInt("QTY"));
        product.setMakerNo(rs.getInt("MKNO"));
        return product;
      } else {
        return null;
      }
      
    } catch (Exception ex) {
      throw new RuntimeException(ex);
      
    } finally {
      try {rs.close();} catch (Exception ex) {}
      try {stmt.close();} catch (Exception ex) {}
      try {con.close();} catch (Exception ex) {}
    }
  }
  
  
  //hard coding되어있어 유지.보수가 불편하다.
  public void update(Product product) {
    Connection con = null;
    PreparedStatement stmt = null;
    
    try {
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/studydb" + 
            "?useUnicode=true&characterEncoding=utf8", 
          "study",
          "study");
      stmt = con.prepareStatement(
          "UPDATE PRODUCTS SET PNAME=?,QTY=?,MKNO=? WHERE PNO=?");
      stmt.setString(1, product.getName());
      stmt.setInt(2, product.getQuantity());
      stmt.setInt(3, product.getMakerNo());
      stmt.setInt(4, product.getNo());

      stmt.executeUpdate();
      
    } catch (Exception ex) {
      throw new RuntimeException(ex);
      
    } finally {
      try {stmt.close();} catch (Exception ex) {}
      try {con.close();} catch (Exception ex) {}
    }
  }
  
  public void delete(int no) {
    Connection con = null;
    Statement stmt = null;
    
    try {
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/studydb" + 
            "?useUnicode=true&characterEncoding=utf8", 
          "study",
          "study");
      stmt = con.createStatement();
      stmt.executeUpdate("DELETE FROM PRODUCTS"
          + " WHERE PNO=" + no);
      
    } catch (Exception ex) {
      throw new RuntimeException(ex);
      
    } finally {
      try {stmt.close();} catch (Exception ex) {}
      try {con.close();} catch (Exception ex) {}
    }
  }
  
  public List<Product> selectList(int pageNo, int pageSize) {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    try {
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/studydb", 
          "study", 
          "study");
      stmt = con.createStatement();
      
      String sql = "SELECT PNO,PNAME,QTY,MKNO FROM PRODUCTS"; 
      
      if (pageSize > 0) {
        sql += " limit " + ((pageNo - 1) * pageSize) 
            + "," + pageSize;
      }
      
      rs = stmt.executeQuery(sql);
      
      ArrayList<Product> list = new ArrayList<Product>();
      Product product = null;
      
      while (rs.next()) {
        product = new Product();
        product.setNo(rs.getInt("PNO"));
        product.setName(rs.getString("PNAME"));
        product.setQuantity(rs.getInt("QTY"));
        product.setMakerNo(rs.getInt("MKNO"));
        list.add(product);
      }
      
      return list;
      
    } catch (Exception ex) {
      throw new RuntimeException(ex);
      
    } finally {
      try {rs.close();} catch (Exception ex) {}
      try {stmt.close();} catch (Exception ex) {}
      try {con.close();} catch (Exception ex) {}
    }
  }
  
  public void insert(Product product) {
    Connection con = null;
    PreparedStatement stmt = null;
    
    try {
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/studydb" + 
            "?useUnicode=true&characterEncoding=utf8", 
          "study",
          "study");
      stmt = con.prepareStatement(
          "INSERT INTO PRODUCTS(PNAME,QTY,MKNO) VALUES(?,?,?)");
      
      //용어정리: ?를 in-parameter라고 부른다.
      //인파라미터의 인덱스는 1부터 시작한다.
      //순서대로 설정할 필요는 없지만, 
      //프로그래밍의 일관성을 위해 순서대로 입력하라!
      stmt.setString(1, product.getName());
      stmt.setInt(2, product.getQuantity());
      stmt.setInt(3, product.getMakerNo());
      
      stmt.executeUpdate();
      
    } catch (Exception ex) {
      throw new RuntimeException(ex);
      
    } finally {
      try {stmt.close();} catch (Exception ex) {}
      try {con.close();} catch (Exception ex) {}
    }
  }
}


















