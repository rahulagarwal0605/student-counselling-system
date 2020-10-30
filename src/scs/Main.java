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
    static void studentQueries(MysqlCon obj) {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        while(true) {
            System.out.println("**************************************************");
            System.out.println("1. Search for college");
            System.out.println("2. Get admission");
            System.out.print("Choose any one of the option: ");
            option = sc.nextInt();
            switch(option) {
                case 1: Student s = new Student();
                    s.addStudent(obj.stmt);
                    System.out.println("\n***************Result***************");
                    s.getResult(obj.stmt);
                    break;
                case 2: instituteQueries();
                    break;
                case 3: branchQueries();
                    break;
                default: System.out.println("Choose appropriate option");
                    break;
            }
            System.out.println("**************************************************");
        }
    }
    
    static void branchQueries() {
        
    }
    
    static void instituteQueries() {
        
    }
    
    public static void main(String[] args) {
        MysqlCon obj = new MysqlCon();
        obj.createDB();
        Scanner sc = new Scanner(System.in);
        System.out.println("*****Student Counselling System*****\n");
        //----------------------------------------------------------------------
        int option=0;
        while(true) {
            System.out.println("**************************************************");
            System.out.println("1. Queries related to Students");
            System.out.println("2. Queries related to Institutes");
            System.out.println("3. Queries related to Branches\n");
            System.out.print("Choose any one of the option: ");
            option = sc.nextInt();
            switch(option) {
                case 1: studentQueries(obj);
                    break;
                case 2: instituteQueries();
                    break;
                case 3: branchQueries();
                    break;
                default: System.out.println("Choose appropriate option");
                    break;
            }
            System.out.println("**************************************************");
        }
        /*
        
        */
    }
}