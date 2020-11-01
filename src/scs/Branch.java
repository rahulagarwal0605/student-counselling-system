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
        this.branchName = sc.nextLine();
        System.out.print("Enter branch duration: ");
        this.duration = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter degree: ");
        this.degree = sc.nextLine();
    }
    
    void addBranch(Statement stmt) {
        this.createBranch();
        try {
            stmt.execute("insert into branch (branch_name, duration, degree) values ('" + this.branchName + "', " + this.duration + ", '" + this.degree + "')");
            System.out.println("Branch successfully added!");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
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
            e.printStackTrace();
        }
    }
    
    void updateBranch(Statement stmt) {
        showBranch(stmt);
        System.out.print("Select branch ID: ");
        Scanner sc = new Scanner(System.in);
        this.branchId = sc.nextInt();
        System.out.println("Enter new details");
        this.createBranch();
        try {
            stmt.execute("update branch set branch_name = '" + this.branchName + "', duration = " + this.duration + ", degree = '" + this.degree + "' where bid = " + this.branchId + ";");
            System.out.println("Branch successfully updated!");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    void removeBranch(Statement stmt) {
        showBranch(stmt);
        System.out.println("Select branch ID: ");
        Scanner sc = new Scanner(System.in);
        this.branchId = sc.nextInt();
        try {
            stmt.execute("delete from branch where bid = " + this.branchId + ";");
            System.out.println("Branch successfully deleted!");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
