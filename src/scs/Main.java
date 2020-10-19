/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scs;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author rahul
 */
public class Main {
    static String user, pass;
    static Scanner sc = new Scanner(System.in);
    
    static {
        user = "root";
        pass = "root";
    }
    
    static void initDB() {
        
    }
    
    static void conDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.print("Enter Username : ");
            user = sc.next();
            System.out.print("Enter Password : ");
            pass = sc.next();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306",user,pass);  
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("show databases");
            boolean flag = false;
            while(rs.next()) {
                if(rs.getString(1).equals("scs")) {
                    flag = true;
                }
            }
            if(flag) {
                System.out.println("Database already created!");
            }
            else {
                System.out.println("Creating Database...");
                stmt.execute("create database scs");
                initDB();
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        System.out.println("Student Counselling System");
        conDB();
    }
}
