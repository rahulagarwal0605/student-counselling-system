/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scs;
import java.sql.*;
import java.util.Scanner;
import java.nio.file.*;
import java.io.*;

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
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get("src/scs/initDB.txt")));
            System.out.println(data);
            stmt.execute(data);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        Branch.initDB(stmt, rs);
    }
    
    static void conDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scs?allowMultiQueries=true","root","r.agarwal");
                stmt = con.createStatement();  
            }
            else {
                System.out.println("Creating Database...");
                stmt.execute("create database scs");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scs?allowMultiQueries=true","root","r.agarwal");
                stmt = con.createStatement();  
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
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Student Counselling System");
        System.out.print("Enter Username : ");
        user = sc.next();
        System.out.print("Enter Password : ");
        pass = sc.next();
        System.out.println("Connecting to database...");
        conDB();
        sc.close();
    }
}
