/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scs;
import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author rahul
 */
public class Institute {
    int instituteId;
    String instituteName;
    String city;
    int stateId;
    
    Institute() {
        
    }
    
    Institute(String name, String city) {
        this.instituteName = name;
        this.city = city;
    }
    
    Institute(Institute i) {
        this.instituteName = i.instituteName;
        this.city = i.city;
    }
    
    void createInstitute(Statement stmt) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter institution name: ");
        instituteName = sc.nextLine();
        System.out.print("Enter city: ");
        city = sc.nextLine();
        try {
            System.out.println();
            ResultSet rs = stmt.executeQuery("select * from state");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        System.out.print("\nEnter stateID: ");
        stateId = sc.nextInt();
    }
    
    void addInstitute(Statement stmt) {
        createInstitute(stmt);
        try {
            stmt.execute("insert into institute (institute_name, city, state_id) values ('" + instituteName + "', '" + city + "', " + stateId + ");");
            System.out.println("\nInstitute added successfully!");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    static void showInstitute(Statement stmt) {
        System.out.println("\n********************Institutes*********************");
        try {
            ResultSet rs = stmt.executeQuery("select iid, institute_name, city, state from institute, state where institute.state_id = state.id order by iid");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4));
            }
            System.out.println();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    void updateInstitute(Statement stmt) {
        showInstitute(stmt);
        System.out.print("Select institute ID: ");
        Scanner sc = new Scanner(System.in);
        instituteId = sc.nextInt();
        System.out.println("\nEnter new details");
        createInstitute(stmt);
        try {
            stmt.execute("update institute set institute_name = '" + instituteName + "', city = '" + city + "', state_id = " + stateId + " where iid = " + instituteId + ";");
            System.out.println("\nInstitute updated successfully!");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    void removeInstitute(Statement stmt) {
        showInstitute(stmt);
        System.out.print("Select institute ID: ");
        Scanner sc = new Scanner(System.in);
        instituteId = sc.nextInt();
        try {
            stmt.execute("delete from josaa where iid = " + instituteId + ";");
            stmt.execute("delete from institute where iid = " + instituteId + ";");
            System.out.println("\nInstitute removed successfully!");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
