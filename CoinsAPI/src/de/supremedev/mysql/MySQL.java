package de.supremedev.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
  public static Connection con;
  
  public static void connect() {
    if (!isConnected()) {
      try {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/YOURDATABASE?autoReconnect=true", "USERNAME", "YOURPASSWORD");
        
        System.out.println("MySQL verbunden!");
        
      
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    }
  }
  
  public static void disconnect() {
    if (isConnected()) {
      try {
        con.close();
        System.out.println("MySQL Verbindung getrennt!");
        
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    }
  }

  
  public static boolean isConnected() { return (con != null); }

  
  public static void createTable() {
    try {
      con.prepareStatement("CREATE TABLE `Coins` (\r\n" + 
      		"  `UUID` varchar(100) DEFAULT NULL,\r\n" + 
      		"  `Coins` int(16) DEFAULT '0'\r\n" + 
      		") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;").executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
}