package jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class example1 {


		public static void main(String[] args) throws SQLException {
			// TODO Auto-generated method stub
			try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            System.out.println("Driver class not found");
	        }

	        
	        Connection cn=null;
	        try {
	            cn = DriverManager.getConnection("jdbc:mysql://localhost/ebookshop", "root", "");
	        } catch (SQLException e) {
	            System.out.println("Cannot conect Database");
	     
	        }


	  String insert = "INSERT INTO books (title, author, price,qty) VALUES (?, ?,?,?)";
	        
	        // Inserting data into the database
	        try {
	        	 PreparedStatement ps = cn.prepareStatement(insert);
	            ps.setString(1, "asp.net");
	            ps.setString(2, "communication skills");
	            ps.setFloat(3,600);
	            ps.setInt(4,60);
	            int c = ps.executeUpdate();

	            if (c > 0) {
	                System.out.println("Data inserted ");
	            } 
	        } catch (SQLException e) {
	            System.out.println("Data not inserted");
	           
	        } 
	        
	        
	        
	        
	        String delete ="delete from books where id=?";
	        
	        try {
	        	PreparedStatement ps = cn.prepareStatement(delete);
	        	ps.setInt(1, 2);
	        	
	        	int c = ps.executeUpdate();
	        	if(c>0) {
	        		System.out.println("data deleted");
	        	}
	        } catch (SQLException e) {
	        	System.out.println("data not deleted");
	        	
	        }
	        
	        
	        
	String update ="update books set title=?, author=?,price=?,qty=? where id=?";
	 
	        
	        try {
	        	PreparedStatement ps = cn.prepareStatement(update);
	        	ps.setString(1,"jvm");
	        	ps.setString(2, "ebook");
	        	ps.setFloat(3, 550);
	        	ps.setInt(4, 55);
	        	ps.setInt(5, 5);
	        	
	        	int c = ps.executeUpdate();
	        	if(c>0) {
	        		System.out.println("data update");
	        	}
	        } catch (SQLException e) {
	        	System.out.println("data not update");
	        	
	        }
	        
	        String fetch = "select * from books where id=2";
	        Statement smt = null;
	     try {
	    	  smt = cn.createStatement();
	    	  
	    	  
	     }catch (SQLException e) {
	    	 System.out.println("smt is not found");
	     }
	       ResultSet rs= null;
	     
	          rs = smt.executeQuery(fetch);      
	    
	    
	     try {
	    	
	     while   (rs.next()) {
	    	 System.out.println(rs.getInt(1)+ " "+rs.getString(2)+ " " +rs.getString(3)+" "+rs.getFloat(4)+" "+rs.getInt(5));
	     }
	     } catch(SQLException e) {
	    	 System.out.println("result display");
	     }
		} 
		
	

	       
		


	}


