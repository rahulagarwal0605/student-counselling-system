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
            stmt.execute("create table branch (bid int not null auto_increment, branch_name varchar(255), duration int, degree varchar(255), primary key (bid))");
            stmt.execute("insert into branch (branch_name, duration, degree)\n" +
"values ('Agricultural Engineering', 4, 'Bachelor of Technology'), ('Computer Science and Engineering', 4, 'Bachelor of Technology'), ('Integrated B. Tech.(IT) and M. Tech (IT)', 5, 'Integrated B. Tech. and M. Tech. /MBA'), ('Chemical Engineering',4,'Bachelor of Technology'),('Civil Engineering',4,'Bachelor of Technology'),('Electrical Engineering',4,'Bachelor of Technology'),('Electrical and Electronics Engineering',4,'Bachelor of Technology'),('Electronics and Communication Engineering',4,'Bachelor of Technology'),('Instrumentation and Control Engineering',4,'Bachelor of Technology'),('Mechanical Engineering',4,'Bachelor of Technology'),('Metallurgical and Materials Engineerin',4,'Bachelor of Technology'),('Production Engineering',4,'Bachelor of Technology'),('Bio Technology',4,'Bachelor of Technology'),('Chemical Engineering(Plastic and Polymer)',4,'Bachelor of Technology'),('Information Technology',4,'Bachelor of Technology'),('Food Engineering and Technology',4,'Bachelor of Technology'),('Instrumentation Engineering',4,'Bachelor of Technology'),('Industrial and Production Engineering',4,'Bachelor of Technology'),('Textile Technology',4,'Bachelor of Technology'),('Carpet and Textile Technology',4,'Bachelor of Technology'),('Aerospace Engineering',4,'Bachelor of Technology'),('Mining Engineering',4,'Bachelor of Technology'),('B. Tech. Electronics and Communication Engineering and M. Tech. Electronics and Communication Engineering with specialization in Communication Systems Design',5,', Bachelor and Master of Technology (Dual Degree)'),('B. Tech. Mechanical Engineering and M. Tech. Mechanical Engineering with specialization in Advanced Manufacturing',5,'Bachelor and Master of Technology (Dual Degree)')");
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
