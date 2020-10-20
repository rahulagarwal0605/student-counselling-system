/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scs;
import java.sql.*;

/**
 *
 * @author rahul
 */
public class Institute {
    int instituteId;
    String instituteName;
    String city;
    
    static void showAllInstitute(Statement stmt) {
        System.out.println("*****Institutes*****");
        try {
            ResultSet rs = stmt.executeQuery("select * from institute");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
            System.out.println();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
