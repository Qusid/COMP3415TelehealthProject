/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp3415.telehealth;
//import com.mysql.jdbc.MySQLConnection;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Gautam
 */
public class login {
    public static boolean isLogin(String username, String password, String userType, JFrame frame){
        try{
        Connection sqlConnection = MySQLConnections.getConnection();                                    //connecting to database
        String query = "SELECT UserID, UName, UType FROM login WHERE username = '" +                    // query to verify login credentials
                username + "' AND password = '" + password +
                "' AND UType = '" + userType + "'";
        PreparedStatement prepS = sqlConnection.prepareStatement(query);
        ResultSet rSet = prepS.executeQuery();                                                          // executing query
        
        while(rSet.next()){
        logInfo.UserID = rSet.getInt("UserID");                                                         // storing user information
        logInfo.UName = rSet.getString("UName");
        logInfo.UType = rSet.getString("UType");
        
        return true;
        }
        }
        catch(Exception e){                                                                             //error while connecting to database
        JOptionPane.showMessageDialog(frame, "Connection error: " + e.getMessage());
        
        }
        
        return false;
    }
}
