package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
//        try(Connection connection= DriverManager.getConnection("jdbc:sqlite:F:\\[FreeCourseSite.com] Udemy - Java Programming Masterclass for Software Developers\\JAVA MASTERCLASS PERSONAL CODES\\SECTION19\\TestDB\\testjava.db");
//            Statement statement=connection.createStatement()){
//            statement.execute("CREATE TABLE contacts (name TEXT,phone INTEGER ,email TEXT)");
        try{
            Connection connection= DriverManager.getConnection("jdbc:sqlite:F:\\[FreeCourseSite.com] Udemy - Java Programming Masterclass for Software Developers\\JAVA MASTERCLASS PERSONAL CODES\\SECTION19\\TestDB\\testjava.db");
//            connection.setAutoCommit(false);  //now if we change the values inside the VALUES no change will happen at database.so,without this,we can just change only one line everytime and automatically new record will be created
            Statement statement=connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS contacts (name TEXT,phone INTEGER ,email TEXT)");
//            statement.execute("INSERT INTO contacts (name,phone,email) VALUES('Hisham',85346856,'syedjarullah@gmail.com')");
//            statement.execute("INSERT INTO contacts (name,phone,email) VALUES('Tim',85346826,'Tim@gmail.com')");
//            statement.execute("INSERT INTO contacts (name,phone,email) VALUES('Mihad',80963752,'syhmihad@hotmail.com')");
//            statement.execute("INSERT INTO contacts (name,phone,email) VALUES('lekare',0781233562,'lek@everywhere.com')");

//            statement.execute("UPDATE contacts SET phone=4567 WHERE name='Tim'");
//            statement.execute("DELETE FROM contacts WHERE name='lekare'");

//            statement.execute("SELECT * FROM contacts");
//            ResultSet resultSet=statement.getResultSet();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM contacts");
            while(resultSet.next()){
                System.out.println(resultSet.getString("name")+" "+resultSet.getString("phone")
                        +" "+resultSet.getString("email"));
            }

            resultSet.close();

            statement.close();
            connection.close();

        }catch (SQLException e){
            System.out.println("something wrong:"+e.getMessage());
        }
    }
}
