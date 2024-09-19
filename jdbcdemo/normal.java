package jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class normal {

    public static void main(String[] args) {
        // Load the MySQL JDBC Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found");
        }

        // Establish the connection to the database
        Connection cn = null;
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
            } else {
                System.out.println("Data insertion failed");
            }
        } catch (SQLException e) {
            System.out.println("Data not inserted");
            
            
            //data update
            String update = "update empdetail set empname=?,empaddress=? where empid=?";
            try {
            	PreparedStatement ps = cn.prepareStatement(update);
            	 ps.setString(1, "jsp");
                 ps.setString(2, "surat");
                 ps.setInt(2,2);
            	int c= ps.executeUpdate();
            	if(c>0) {
            		System.out.println("data deleted");
        } catch(SQLException e) {
        	System.out.println("data not inserted");
        }
            	
            }
        
    }
    
    }

