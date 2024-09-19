package jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class demo {

	public static void main(String[] args) throws SQLException {
		
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

        // SQL insert query with correct placeholders
        String insert = "INSERT INTO empdetail (empname, empaddress) VALUES (?, ?)";
        
        // Inserting data into the database
        try {
        	 PreparedStatement ps = cn.prepareStatement(insert);
            ps.setString(1, "springboot");
            ps.setString(2, "ahmd");
            int c = ps.executeUpdate();

            if (c > 0) {
                System.out.println("Data inserted successfully");
            } 
        } catch (SQLException e) {
            System.out.println("Data not inserted");
           
        } 
        
        //data deleted
        String delete ="delete from empdetail where empid=?";
       
        try {
        	PreparedStatement ps = cn.prepareStatement(delete);
        	ps.setInt(1, 3);
        	
        	int c = ps.executeUpdate();
        	if(c>0) {
        		System.out.println("data deleted");
        	}
        } catch (SQLException e) {
        	System.out.println("data not deleted");
        	
        }
        
        //data update 
        
        
        String update ="update empdetail set empname=?, empaddress=? where empid=?";
 
        
        try {
        	PreparedStatement ps = cn.prepareStatement(update);
        	ps.setString(1,"jsp");
        	ps.setString(2, "surat");
        	ps.setInt(3, 2);
        	
        	int c = ps.executeUpdate();
        	if(c>0) {
        		System.out.println("data update");
        	}
        } catch (SQLException e) {
        	System.out.println("data not update");
        	
        }
        
        //data select
        
        String fetch = "select * from empdetail where empid=2";
        Statement smt = null;
     try {
    	  smt = cn.createStatement();
    	  
    	  
     }catch (SQLException e) {
    	 System.out.println("smt not found");
     }
       ResultSet rs= null;
     
          rs = smt.executeQuery(fetch);      
    
    
     try {
    	
     while   (rs.next()) {
    	 System.out.println(rs.getInt(1)+ " "+rs.getString(2)+ " " +rs.getString(3));
     }
     } catch(SQLException e) {
    	 System.out.println("result display");
     }
	} 
	}
	

