package com.smartagrocare.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection 
{
    private static final String URL = "jdbc:mysql://localhost:3306/smart_agrocare";
    private static final String USER = "root";
    private static final String PASSWORD = "vedantcoc@@77";

    public static Connection getConnection() 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } 
        catch (Exception e) 
        {
            System.out.println("Database error:");
            e.printStackTrace();
            return null;
        }
    }
}