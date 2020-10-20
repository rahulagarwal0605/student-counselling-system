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
    
    static void initDB(Statement stmt, ResultSet rs) {
        try {
            rs = stmt.executeQuery("select * from branch");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    Branch(int id, String name) {
        this.branchID = id;
        this.branchName = name;
    }

    static void addBranch() {
        int tempID;
        String tempName;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter BranchID");
        tempID = sc.nextInt();
        tempName = sc.next();
        Branch tempBranch = new Branch(tempID, tempName);
        
    }
    
    static void addBranch(Branch b){
        
    }
    
}
