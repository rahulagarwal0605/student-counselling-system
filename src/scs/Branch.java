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
    int branchID;
    String branchName;
    int duration;
    String degree;
    
    Branch(String name, int dur, String deg) {
        this.branchID = 0;
        this.branchName = name;
        this.duration = dur;
        this.degree = deg;
    }

    static void addBranch(Statement stmt) {
        String tempName, tempDeg;
        int tempDur;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Branch Name: ");
        tempName = sc.next();
        System.out.print("Enter Branch Duration: ");
        tempDur = sc.nextInt();
        System.out.print("Enter Degree: ");
        tempDeg = sc.next();
        sc.close();
        Branch tempBranch = new Branch(tempName, tempDur, tempDeg);
        addBranch(tempBranch, stmt);
    }
    
    static void addBranch(String name, int dur, String deg, Statement stmt) {
        Branch tempBranch = new Branch(name, dur, deg);
        addBranch(tempBranch, stmt);
    }
    
    static void addBranch(Branch b, Statement stmt){
        try {
            stmt.execute("insert into branch (branch_name, duration, degree) values ('" + b.branchName + "', " + b.duration + ", '" + b.degree + "')");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    static void showAllBranch(Statement stmt) {
        System.out.println("*****Branches*****");
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
}
