package jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class jdbc_assessment {

    private Connection cn;

    public static void main(String[] args) {
        jdbc_assessment j = new jdbc_assessment();
        j.course();
    }

    public void course() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            return;
        }

        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost/coursemanagement", "root", "");
        } catch (SQLException e) {
            System.out.println("Connection not found");
            return;
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n-----Course Management System-----\n");
            System.out.println("1. Add Course");
            System.out.println("2. View Courses");
            System.out.println("3. Search Course");
            System.out.println("4. Update Course");
            System.out.println("5. Delete Course");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    view();
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    delete();
                    break;
                case 6:
                    closeConnection();
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    public void add() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter course ID: ");
        int course_id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter course name: ");
        String course_name = scanner.nextLine();

        System.out.print("Enter course fees: ");
        double course_fees = scanner.nextDouble();

        System.out.print("Enter course duration (in years): ");
        double course_duration = scanner.nextDouble();

        String insert = "INSERT INTO courses(course_id, course_name, course_fees, course_duration) VALUES(?, ?, ?, ?)";

        try (PreparedStatement p) {
            p.setInt(1, course_id);
            p.setString(2, course_name);
            p.setDouble(3, course_fees);
            p.setDouble(4, course_duration);

            int c = p.executeUpdate();

            if (c > 0) {
                System.out.println("Data inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Data not inserted");
            e.printStackTrace();
        }
    }

    public void view() {
        String query = "SELECT * FROM courses";

        try (Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("ID\tName\tFees\tDuration");
            while (rs.next()) {
                int id = rs.getInt("course_id");
                String name = rs.getString("course_name");
                double fees = rs.getDouble("course_fees");
                double duration = rs.getDouble("course_duration");

                System.out.println(id + "\t" + name + "\t" + fees + "\t" + duration);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving data");
            e.printStackTrace();
        }
    }

    public void search() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter course ID to search: ");
        int course_id = scanner.nextInt();

        String query = "SELECT * FROM courses WHERE course_id = ?";

        try (PreparedStatement p = cn.prepareStatement(query)) {
            p.setInt(1, course_id);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                String name = rs.getString("course_name");
                double fees = rs.getDouble("course_fees");
                double duration = rs.getDouble("course_duration");

                System.out.println("ID: " + course_id);
                System.out.println("Name: " + name);
                System.out.println("Fees: " + fees);
                System.out.println("Duration: " + duration);
            } else {
                System.out.println("Course not found");
            }
        } catch (SQLException e) {
            System.out.println("Error searching data");
            e.printStackTrace();
        }
    }

    public void update() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter course ID to update: ");
        int course_id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter new course name: ");
        String course_name = scanner.nextLine();

        System.out.print("Enter new course fees: ");
        double course_fees = scanner.nextDouble();

        System.out.print("Enter new course duration (in years): ");
        double course_duration = scanner.nextDouble();

        String update = "UPDATE courses SET course_name = ?, course_fees = ?, course_duration = ? WHERE course_id = ?";

        try (PreparedStatement p = cn.prepareStatement(update)) {
            p.setString(1, course_name);
            p.setDouble(2, course_fees);
            p.setDouble(3, course_duration);
            p.setInt(4, course_id);

            int c = p.executeUpdate();

            if (c > 0) {
                System.out.println("Data updated successfully!");
            } else {
                System.out.println("Course not found");
            }
        } catch (SQLException e) {
            System.out.println("Error updating data");
            e.printStackTrace();
        }
    }

    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter course ID to delete: ");
        int course_id = scanner.nextInt();

        String delete = "DELETE FROM courses WHERE course_id = ?";

        try (PreparedStatement p = cn.prepareStatement(delete)) {
            p.setInt(1, course_id);

            int c = p.executeUpdate();

            if (c > 0) {
                System.out.println("Course deleted successfully!");
            } else {
                System.out.println("Course not found");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting data");
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            if (cn != null && !cn.isClosed()) {
                cn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing the connection");
            e.printStackTrace();
        }
    }
}