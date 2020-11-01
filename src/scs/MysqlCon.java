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
public class MysqlCon {
    String user, pass;
    Connection con;
    Statement stmt;
    
    MysqlCon() {
        user = "root";
        pass = "root";
        con = null;
        stmt = null;
    }
    
    MysqlCon(String user, String pass) {
        this.user = user;
        this.pass = pass;
        con = null;
        stmt = null;
    }
    
    void initDB() {
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
    
    void createDB() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Username (root): ");
        user = sc.next();
        System.out.print("Enter Password : ");
        pass = sc.next();
        System.out.println("\nConnecting to database...\n");
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
            this.createDB();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    void conDB() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scs?allowMultiQueries=true",user,pass);
            stmt = con.createStatement();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
