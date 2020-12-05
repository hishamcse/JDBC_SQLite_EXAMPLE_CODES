package com.company;

import java.sql.*;

public class AnotherWay {
    public static final String DB_TEST="testjava.db";
    public static final String CONNECTION_STRING="jdbc:sqlite:F:\\[FreeCourseSite.com] Udemy - Java Programming Masterclass for Software Developers\\JAVA MASTERCLASS PERSONAL CODES\\SECTION19\\TestDB\\"+DB_TEST;

    public static final String TABLE_NAME="contacts";

    public static final String COLUMN_NAME="name";
    public static final String COLUMN_PHONE="phone";
    public static final String COLUMN_EMAIL="email";

    public static void main(String[] args) {

        try{
            Connection connection= DriverManager.getConnection(CONNECTION_STRING);
            Statement statement=connection.createStatement();
//            statement.execute("DROP TABLE IF EXISTS "+TABLE_NAME);
//
//            statement.execute("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+
//                               " ("+COLUMN_NAME+" TEXT,"+COLUMN_PHONE+" INTEGER,"+COLUMN_EMAIL+" TEXT"+")");
//            statement.execute("INSERT INTO "+TABLE_NAME+" ("+COLUMN_NAME+","+COLUMN_PHONE+","+COLUMN_EMAIL+")"+
//                                  " VALUES('Hisham',123456,'syedjarullah@gmail.com')");
//            statement.execute("INSERT INTO "+TABLE_NAME+" ("+COLUMN_NAME+","+COLUMN_PHONE+","+COLUMN_EMAIL+")"+
//                    " VALUES('Mihad',567890,'tim@gmail.com')");
//            statement.execute("INSERT INTO "+TABLE_NAME+" ("+COLUMN_NAME+","+COLUMN_PHONE+","+COLUMN_EMAIL+")"+
//                    " VALUES('Tim',019354,'Tim@gmail.com')");
//            statement.execute("INSERT INTO "+TABLE_NAME+" ("+COLUMN_NAME+","+COLUMN_PHONE+","+COLUMN_EMAIL+")"+
//                    " VALUES('kelo',3454,'kelosf@gmail.com')");
//            statement.execute("INSERT INTO "+TABLE_NAME+" ("+COLUMN_NAME+","+COLUMN_PHONE+","+COLUMN_EMAIL+")"+
//                    " VALUES('Joe',98756,'joethe826@gmail.com')");
//
//            statement.execute("UPDATE "+TABLE_NAME+" SET "+COLUMN_EMAIL+"='mihad@email.com' WHERE "+COLUMN_NAME+" ='Mihad'");
//
//            statement.execute("DELETE FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME+"='kelo'");
//            insertcontact(statement,"Shuaib",345678,"shuaib@hotmail.com");

            ResultSet resultSet=statement.executeQuery("SELECT * FROM "+TABLE_NAME);
            while(resultSet.next()){
                System.out.println(resultSet.getString(COLUMN_NAME)+" "+resultSet.getString(COLUMN_PHONE)+" "+resultSet.getString(COLUMN_EMAIL));
            }
            resultSet.close();

            statement.close();
            connection.close();

        }catch (SQLException e){
            System.out.println("something went wrong: "+e.getMessage());
            e.printStackTrace();  //for understanding where is the problem
        }
    }
    private static void insertcontact(Statement statement,String name,int phone,String email) throws SQLException{
        statement.execute("INSERT INTO "+TABLE_NAME+" ("+COLUMN_NAME+","+COLUMN_PHONE+","+COLUMN_EMAIL+") "+
                "VALUES('" + name + "', " + phone + ", '" + email + "')");
    }
}
