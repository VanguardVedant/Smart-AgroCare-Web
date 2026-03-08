package com.smartagrocare.dao;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.smartagrocare.model.User;
import com.smartagrocare.util.DBConnection;

public class UserDAO 
{
    public boolean registerUser(User user) 
    {
        boolean status = false;
        try 
        {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO users(name, email, password, is_verified) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setBoolean(4, user.isVerified());
            int rows = ps.executeUpdate();

            if (rows > 0) 
            {
                status = true;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return status;
    }
    public User loginUser(String email, String password) 
    {

        User user = null;
        try
        {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setVerified(rs.getBoolean("is_verified"));
            }

        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }

        return user;
    }
}
