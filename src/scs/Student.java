/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scs;
import java.util.*;
import java.sql.*;

/**
 *
 * @author rahul
 */
public class Student {
    int rollNum;
    String name;
    String dob;
    int rank;
    String state;
    int stateId;
    String gender;
    String category;
    Branch preferences;
    
    Student(int rollNum, String name, String dob, int rank, String gender, String category) {
        this.rollNum = rollNum;
        this.name = name;
        this.dob = dob;
        this.rank = rank;
        this.gender = gender;
        this.category = category;
    }
    
    static void addStudent(Statement stmt) {
        int stRollNum;
        String stName;
        String stDob;
        int stRank;
        int stStateID;
        String stGender;
        String stCategory;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student Details");
        System.out.print("Enter Roll Number: ");
        stRollNum = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        stName = sc.nextLine();
        System.out.print("Enter Date of Birth: ");
        stDob = sc.next();
        System.out.print("Enter Rank: ");
        stRank = sc.nextInt();
        System.out.print("Enter Gender - M/F/O: ");
        stGender = sc.next();
        System.out.print("Enter Category - gen/gen_ews/obc/sc/st: ");
        stCategory = sc.next();
        addStudent(stRollNum, stName, stDob, stRank, stGender, stCategory, stmt);
    }
    
    static void addStudent(int rollNum, String name, String dob, int rank, String gender, String category, Statement stmt) {
        Student s = new Student(rollNum, name, dob, rank, gender, category);
        addStudent(s, stmt);
    }
    static void addStudent(Student s, Statement stmt) {
        try {
            ResultSet rs = stmt.executeQuery("select * from state");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        System.out.print("Choose Home State: ");
        Scanner sc = new Scanner(System.in);
        s.stateId = sc.nextInt();
        try {
            stmt.execute("insert into student (roll_num, name, dob, state_id, gender, category, exam_rank) values (" + s.rollNum + ", '" + s.name + "', '" + s.dob + "', " + s.stateId + ", '" + s.gender + "', '" + s.category + "', " + s.rank + ")");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
