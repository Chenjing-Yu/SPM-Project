package model;



import java.sql.*;

public class dbConnection {
    
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/spm?useSSL=false";
   
    private static final String DB_USER = "root";

    private static final String DB_PASSWORD = "root";
    
    
    public static PreparedStatement prepare(String stm) throws SQLException {
         
        PreparedStatement preparedStatement = null;
        try {    
    
                Connection dbConnection = getDBConnection();
                System.out.println("Db COnnection successful");
                
            preparedStatement = dbConnection.prepareStatement(stm);
            
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        
        }

        return preparedStatement;
    }
    public static Connection getDBConnection() {



        try {
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
           
            Connection dbConnection = DriverManager.getConnection(
                            DB_CONNECTION, DB_USER,DB_PASSWORD);
            System.out.println("Db COnnection successful");
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
        System.out.println("Connection problem");
        return null;

    }

}