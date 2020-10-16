/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scs;
import java.util.*;

/**
 *
 * @author rahul
 */
public class Student {
    String name;
    Date dob;
    int rollNum;
    int rank;
    String quota;
    char gender;
    String category;
    Branch Preferences;
    
    public Student(String name, Date dob, int rollNum, int rank, String quota, char gender, String category) {
        this.name = name;
        this.dob = dob;
        this.rollNum = rollNum;
        this.rank = rank;
        this.quota = quota;
        this.gender = gender;
        this.category = category;
    }
    
    static void addStudent(Student s) {
        
    }
}
