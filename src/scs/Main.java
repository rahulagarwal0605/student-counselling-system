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
    
    static {
        user = "root";
        pass = "root";
    }
    
    static void initDB() {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get("src/scs/initDB.txt")));
            stmt.execute(data);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    static void conDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306?allowMultiQueries=true",user,pass);
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
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scs?allowMultiQueries=true",user,pass);
                stmt = con.createStatement();
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
    }
    public static void main(String[] args) {
        System.out.println("*****Student Counselling System*****\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Username : ");
        user = sc.next();
        System.out.print("Enter Password : ");
        pass = sc.next();
        System.out.println("Connecting to database...");
        conDB();
        sc.close();
        //----------------------------------------------------------------------
        Branch.showAllBranch(stmt);
        Institute.showAllInstitute(stmt);
    }
}
