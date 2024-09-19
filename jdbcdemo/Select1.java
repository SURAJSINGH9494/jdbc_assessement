package jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found");
        }

        
        Connection cn=null;
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost/java1", "root", "");
        } catch (SQLException e) {
            System.out.println("Cannot connect to the database");
     
        }
        String fetch = "select * from empdetail where empid=2";
        Statement smt = null;
     try {
    	  smt = cn.createStatement();
    	  
    	  
     }catch (SQLException e) {
    	 System.out.println("smt not found");
     }
       ResultSet rs= null;
     
          try {
			rs = smt.executeQuery(fetch);
		} catch (SQLException e) {
			System.out.println("rs not found");
		}
          
          try {
          	
        	     while   (rs.next()) {
        	    	 System.out.println(rs.getInt(1)+ " "+rs.getString(2)+ " " +rs.getString(3));
        	     }
        	     } catch(SQLException e) {
        	    	 System.out.println("result display");
        	     }
	}

}
