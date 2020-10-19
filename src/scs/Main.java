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
    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    
    static {
        user = "root";
        pass = "root";
    }
    
    static void initDB() {
        try {
            stmt.execute("use scs");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        Branch.initDB(stmt, rs);
    }
    
    static void conDB() {
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.print("Enter Username : ");
            user = sc.next();
            System.out.print("Enter Password : ");
            pass = sc.next();
            System.out.println("Connecting to database...");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","r.agarwal"); // update
            stmt = con.createStatement();  
            rs = stmt.executeQuery("show databases");
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
                System.out.println("Database created successfully...");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            sc.close();
        }
    }
    public static void main(String[] args) {
        System.out.println("Student Counselling System");
        conDB();
    }
}
