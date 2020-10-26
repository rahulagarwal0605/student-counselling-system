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
    
    static {
        user = "root";
        pass = "root";
    }
    
    static void initDB() {
        try {
            Scanner data = new Scanner(Main.class.getResourceAsStream("/initDB.txt"));
            data.useDelimiter("\\Z");
            stmt.execute(data.next());
            data.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    static void createDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306",user,pass);
            stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("show databases");
            boolean flag = false;
            while(rs.next()) {
                if(rs.getString(1).equals("scs")) {
                    flag = true;
                }
            }
            rs.close();
            if(flag) {
                System.out.println("Database already created!");
                conDB();
            }
            else {
                System.out.println("Creating Database...");
                stmt.execute("create database scs");
                conDB();
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
    
    static void conDB() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scs?allowMultiQueries=true",user,pass);
            stmt = con.createStatement();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println("*****Student Counselling System*****\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Username : ");
        user = sc.next();
        System.out.print("Enter Password : ");
        pass = sc.next();
        System.out.println("Connecting to database...");
        createDB();
        //----------------------------------------------------------------------
        Student.addStudent(12345, "Rahul Agarwal", "2001-05-06", 10000, "M", "gen", stmt);
    }
}