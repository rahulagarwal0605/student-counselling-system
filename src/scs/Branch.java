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
public class Branch {
    int branchId;
    String branchName;
    int duration;
    String degree;
    
    Branch() {
        
    }
    
    Branch(String name, int dur, String deg) {
        this.branchName = name;
        this.duration = dur;
        this.degree = deg;
    }
    
    Branch(Branch b) {
        this.branchName = b.branchName;
        this.duration = b.duration;
        this.degree = b.degree;
    }
    
    void createBranch(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter branch name: ");
        branchName = sc.nextLine();
        System.out.print("Enter branch duration: ");
        duration = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter degree: ");
        degree = sc.nextLine();
    }
    
    void addBranch(Statement stmt) {
        createBranch();
        try {
            stmt.execute("insert into branch (branch_name, duration, degree) values ('" + branchName + "', " + duration + ", '" + degree + "')");
            System.out.println("\nBranch added successfully!");
        }
        catch(SQLException e) {
            System.out.println("\nError!");
        }
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    static void showBranch(Statement stmt) {
        System.out.println("\n*********************Branches*********************");
        try {
            ResultSet rs = stmt.executeQuery("select * from branch");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4));
            }
            System.out.println();
            rs.close();
        }
        catch(SQLException e){
            System.out.println("\nError!");
        }
    }
    
    void updateBranch(Statement stmt) {
        showBranch(stmt);
        System.out.print("Select branch ID: ");
        Scanner sc = new Scanner(System.in);
        branchId = sc.nextInt();
        System.out.println("Enter new details");
        createBranch();
        try {
            stmt.execute("update branch set branch_name = '" + branchName + "', duration = " + duration + ", degree = '" + degree + "' where bid = " + branchId + ";");
            System.out.println("\nBranch updated successfully!");
        }
        catch(SQLException e) {
            System.out.println("\nError!");
        }
    }
    
    void removeBranch(Statement stmt) {
        showBranch(stmt);
        System.out.println("Select branch ID: ");
        Scanner sc = new Scanner(System.in);
        branchId = sc.nextInt();
        try {
            stmt.execute("delete from josaa where bid = " + branchId + ";");
            stmt.execute("delete from choice where bid = " + branchId + ";");
            stmt.execute("delete from branch where bid = " + branchId + ";");
            System.out.println("\nBranch removed successfully!");
        }
        catch(SQLException e) {
            System.out.println("\nError!");
        }
    }
}
