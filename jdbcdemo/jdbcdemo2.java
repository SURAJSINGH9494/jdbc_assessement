package jdbcdemo;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.sql.SQLException;



public class jdbcdemo2 {
	

	
	    public static void main(String[] args) {
	        

	         try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         } catch (ClassNotFoundException e) {
	         System.out.println("Class not found: " + e);
	         }
	         
	         Connection con;
			PreparedStatement create = con.prepareStatement("INSERT INTO USER(USER_NAME, USER_AGE, USER_NUMBER, USER_MAIL) VALUES (?, ?, ?, ?)");
	        
	         try {

	        	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java2", "root", "");

	         create.setString(1, "Ram");
	         create.setInt(2, 33);
	         create.setInt(3, 123458);
	         create.setString(4, "ram@gmail.com");

	         if (create.executeUpdate() == 1) {
	         System.out.println("Created");
	         }

	         PreparedStatement read = con.prepareStatement("SELECT * FROM USER");
	         ResultSet data = read.executeQuery();

	         while (data.next()) {
	         System.out.println(data.getInt(1) + " : " + data.getString(2) + " : " +data.getInt(3) + " : "+ data.getInt(4) + " : " + data.getString(5));
	         }

	        PreparedStatement update = con.prepareStatement("UPDATE USER SET USER_NAME = (?) WHERE USER_ID = (?)");

	        update.setString(1, "Rambo");
	        update.setInt(2, 6);

	        if (update.executeUpdate() == 1) {
	        System.out.println("Updated");
	        }

	         PreparedStatement delete = con.prepareStatement("DELETE FROM USER WHERE USER_ID = (?)");

	        delete.setInt(1, 4);

	         if (delete.executeUpdate() == 1) {
	         System.out.println("Deleted");
	         }

	         } catch (SQLException e) {
	         e.printStackTrace();
	         }

	       
	        try (
	                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java1", "root",
	                        "suraj@123singh")) {

	            PreparedStatement create = con.prepareStatement("INSERT INTO FLIGHT (FLIGHT_TO, FLIGHT_FROM, FLIGHT_DAY) VALUES (?, ?, ?)");

	             HashMap<Integer, String> from = new HashMap<>();

	            from.put(1, "Mumbai");
	             from.put(2, "Toronto");
	             from.put(3, "Kolkata");
	             from.put(4, "Surat");
	             from.put(5, "Tokyo");

	             HashMap<Integer, String> day = new HashMap<>();

	             day.put(1, "Monday");
	             day.put(2, "Tuesday");
	             day.put(3, "Wednesday");
	             day.put(4, "Friday");
	             day.put(5, "Sunday");

	             HashMap<Integer, String> to = new HashMap<>();

	             to.put(1, "Delhi");
	             to.put(2, "New York");
	             to.put(3, "Dubai");
	             to.put(4, "Hong Kong");
	             to.put(5, "Kyoto");

	             for (int i = 1; i < 6; i++) {
	             create.setString(1, to.get(i));
	             create.setString(2, from.get(i));
	             create.setString(3, day.get(i));

	             if (create.executeUpdate() == 1) {
	             System.out.println("Inserted");
	             }
	             }

	            PreparedStatement read = con.prepareStatement("SELECT * FROM FLIGHT");

	            ResultSet data = read.executeQuery();

	            ArrayList<String> to1 = new ArrayList<>();

	            while (data.next()) {
	                to1.add(data.getString(2));
	            }

	            try (Scanner in = new Scanner(System.in)) {
	                String userTo = null;
	                System.out.print("Enter your destination: ");
	                userTo = in.nextLine();

	                for (String a : to1) {
	                    if (a.equalsIgnoreCase(userTo)) {
	                        PreparedStatement update = con
	                                .prepareStatement("UPDATE FLIGHT SET BOOK_CANCEL = (?) WHERE FLIGHT_TO = (?)");
	                        update.setString(1, "Booked");
	                        update.setString(2, a);

	                        update.executeUpdate();

	                        System.out.println("Your flight has been booked");
	                    }
	                }

	            }

	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	    }
	}