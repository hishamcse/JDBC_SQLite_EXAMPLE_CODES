package com.Hisham;

import java.io.*;
import java.sql.*;

public class BLOBApp {

    private Connection connect() {

        String url = "jdbc:sqlite:test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private byte[] readFile(String file) {
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }

    public void updatePicture(int materialId, String filename) {

        String updateSQL = "UPDATE Cars "
                + "SET CarImage = ? "
                + "WHERE CarReg=?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            // set parameters
            pstmt.setBytes(1, readFile(filename));
            pstmt.setString(2, "MARY");

            pstmt.executeUpdate();
            System.out.println("Stored the file in the BLOB column.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readPicture(int materialId, String filename) {
        // update sql
        String selectSQL = "SELECT picture FROM materials WHERE id=?";
        ResultSet rs = null;
        FileOutputStream fos = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = connect();
            pstmt = conn.prepareStatement(selectSQL);
            pstmt.setInt(1, materialId);
            rs = pstmt.executeQuery();

            // write binary stream into file
            File file = new File(filename);
            fos = new FileOutputStream(file);

            System.out.println("Writing BLOB to file " + file.getAbsolutePath());
            while (rs.next()) {
                InputStream input = rs.getBinaryStream("picture");
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    fos.write(buffer);
                }
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
                if (fos != null) {
                    fos.close();
                }

            } catch (SQLException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BLOBApp app = new BLOBApp();
        app.updatePicture(1, "D:\\tinified\\rafi.jpg");
//        app.readPicture(1, "c:\\temp\\HP_Laptop_BLOB.jpg");

//        Connection connection;
//        try {
//            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM Cars");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("CarReg") + " " + resultSet.getString("CarMake")
//                        + " " + resultSet.getString("CarModel"));
//            }
//            resultSet = statement.executeQuery("SELECT * FROM Manufacturer");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("Name") + " " + resultSet.getString("Password"));
//            }
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }
}
