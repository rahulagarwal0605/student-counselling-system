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
        sc.close();
        System.out.println("Connecting to database...");
        createDB();
        //----------------------------------------------------------------------
        try {
            ResultSet rs = stmt.executeQuery("SELECT id,institute_name,branch_name,quota,seat_pool,gen_or,gen_cr,gen_ews_or,gen_ews_cr,obc_or,obc_cr,sc_or,sc_cr,st_or,st_cr FROM ((josaa INNER JOIN institute ON josaa.iid = institute.iid) INNER JOIN branch ON josaa.bid = branch.bid) order by id;");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getInt(8) + " " + rs.getInt(9) + " " + rs.getInt(10) + " " + rs.getInt(11) + " " + rs.getInt(12) + " " + rs.getInt(13) + " " + rs.getInt(14) + " " + rs.getInt(15));
            }
            System.out.println();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
