package com.gp.doctorreview.Models;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseDriver {

    private Connection conn;

    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException e) {
            System.out.println("Exception :change:");
        }
    }

    // Patient Section
    public void createNewUser(String name, String email, String password, LocalDate date) {
        Statement statement;

        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "User (name, role, email, password, dateOfBirth)" +
                    "VALUES ('"+name+"', 'PATIENT', '"+email+"', '"+password+"', '"+date.toString()+"');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNewUser(String name, String role, String email, String password, LocalDate date, String address, String postcode){

        String sql = "INSERT INTO User (name, role, email, password, dateOfBirth, addressLine1, postcode) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, role);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setDate(5, java.sql.Date.valueOf(date));
            pstmt.setString(6, address);
            pstmt.setString(7, postcode);

            pstmt.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet getDoctorFeedbacks(int doctorID) {
        Statement statement;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Feedback WHERE doctorID='"+doctorID+"';";

        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Exception");
        }

        return resultSet;
    }

    // Admin Section

    // General Section
    public ResultSet getUserData(String email, String password) {
        Statement statement;
        ResultSet resultSet = null;
        String query = "SELECT * FROM User WHERE email='"+email+"' AND password='"+password+"';";

        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("SQL Exception");
        }
        return resultSet;
    }

    public ResultSet userExist(String email) {
        Statement statement;
        ResultSet resultSet = null;
        String query = "SELECT * FROM User WHERE email='"+email+"';";

        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("SQL Exception");
        }
        return resultSet;
    }

    public ResultSet getAllDoctorsData() {
        Statement statement;
        ResultSet resultSet;
        String query = "SELECT * FROM Doctor";

        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    // Specialization
    public ResultSet getDistinctSpec() {
        Statement statement;
        ResultSet resultSet = null;
        String query = "SELECT DISTINCT specialization FROM Doctor;";
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("E");
        }

        return resultSet;
    }

    // Send Message
    public void sendMessage(int senderID, String senderName, String senderEmail, String messageTitle, String message, LocalDate date) {
        Statement statement;

        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "Message (senderID, senderName, senderEmail, messageTitle, message, dateSent)" +
                    "VALUES ('"+senderID+"', '"+senderName+"', '"+senderEmail+"', '"+messageTitle+"', '"+message+"', '"+date.toString()+"');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Get Messages
    public ResultSet getAllMessages() {
        Statement statement;
        ResultSet resultSet = null;

        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Message");
        } catch (SQLException e) {
            System.out.println("SQL Exception");
        }

        return resultSet;
    }

    // Add New Review
    public void addReview(Feedback feedback) {
        Statement statement;

        int senderID = feedback.senderIDProperty().get();
        String userName = feedback.senderNameProperty().get();
        int doctorID = feedback.doctorIDProperty().get();
        String doctorName = feedback.doctorNameProperty().get();
        LocalDate dateSent = feedback.dateSentProperty().get();
        String feedbackTitle = feedback.feedbackTitleProperty().get();
        String message = feedback.feedbackMessageProperty().get();
        double revPoint = feedback.reviewPointProperty().get();

        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "Feedback (senderId, userName, doctorID, doctorName, dateSent, feedbackTitle, feedbackMessage, newReviewPoint)" +
                    "VALUES ('"+senderID+"', '"+userName+"', '"+doctorID+"', '"+doctorName+"', '"+dateSent+"', '"+feedbackTitle+"', '"+message+"', '"+revPoint+"')");
        } catch (SQLException e) {
            System.out.println("Add a new Review" + e.getMessage());
        }
    }

    // Update doctor
    public void updateDoctor(int doctorID, double newReview, int newTotalView) {
        PreparedStatement statement;

        try {
            statement = conn.prepareStatement("UPDATE Doctor SET reviewRating=?, totalReviews=? WHERE doctorID=?");
            statement.setDouble(1, newReview);
            statement.setInt(2, newTotalView);
            statement.setInt(3, doctorID);

            System.out.println("Doctor Updated!");
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Exception in update doctor!" + e.getMessage());
        }
    }

    public void deleteUserByEmail(int id) {
        String sql = "DELETE FROM User WHERE userID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("No user found with the specified email.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDoctor(String name, String specialization, double review, int totalViewer) {
        String sql = "INSERT INTO Doctor (doctorName, specialization, reviewRating, totalReviews) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, specialization);
            pstmt.setDouble(3, review);
            pstmt.setInt(4, totalViewer);

            pstmt.executeUpdate();
            System.out.println("Doctor added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDoctor(int id) {
        String sql = "DELETE FROM Doctor WHERE doctorID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Doctor deleted successfully.");
            } else {
                System.out.println("No doctor found with the specified email.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeUserPassword(String userEmail, String newPassword) {
        String sql = "UPDATE User SET password = ? WHERE email = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, userEmail);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Password updated successfully for user: " + userEmail);
            } else {
                System.out.println("No user found with email: " + userEmail);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
