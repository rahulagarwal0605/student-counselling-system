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
    char gender;
    String category;
    Branch preferences;
    
    Student(int rollNum, String name, String dob, int rank, String state, char gender, String category) {
        this.rollNum = rollNum;
        this.name = name;
        this.dob = dob;
        this.rank = rank;
        this.state = state;
        this.gender = gender;
        this.category = category;
    }
    
    static void addStudent(Statement stmt) {
        int rollNum;
        String name;
        String dob;
        int rank;
        String state;
        char gender;
        String category;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student Details");
        System.out.print("Enter Roll Number: ");
        rollNum = sc.nextInt();
        System.out.print("Enter Name: ");
        name = sc.next();
        System.out.print("Enter Date of Birth: ");
        dob = sc.next();
        System.out.print("Enter Rank: ");
        rank = sc.nextInt();
        System.out.print("Enter State: ");
        dob = sc.next();
        sc.close();
    }
    
    static void addStudent(int rollNum, String name, String dob, int rank, String state, char gender, String category) {
        
    }
    static void addStudent(Student s) {
        
    }
}
