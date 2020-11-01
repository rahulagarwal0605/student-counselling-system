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
        int option;
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
                default: System.out.println("Choose appropriate option");
                    break;
            }
            System.out.println("**************************************************\n");
        }
    }
    
    static void branchQueries(MysqlCon obj) {
        Scanner sc = new Scanner(System.in);
        int option;
        Branch b = new Branch();
        while(true) {
            System.out.println("\n***************************************************");
            System.out.println("1. Add new Branch");
            System.out.println("2. Show all Branch");
            System.out.println("3. Update Branch");
            System.out.println("4. Remove Branch");
            System.out.println("5. Back");
            System.out.println("6. Exit");
            System.out.println("***************************************************\n");
            System.out.print("Choose any one of the option: ");
            option = sc.nextInt();
            switch(option) {
                case 1: b.addBranch(obj.stmt);
                    break;
                case 2: Branch.showBranch(obj.stmt);
                    break;
                case 3: b.updateBranch(obj.stmt);
                    break;
                case 4: b.removeBranch(obj.stmt);
                    break;
                case 5: return;
                case 6: System.exit(0);
                    break;
                default: System.out.println("\nChoose appropriate option");
                    break;
            }
            System.out.println("**************************************************\n");
        }
    }
    
    static void instituteQueries(MysqlCon obj) {
        Scanner sc = new Scanner(System.in);
        int option;
        Institute i = new Institute();
        while(true) {
            System.out.println("\n***************************************************");
            System.out.println("1. Add new Institute");
            System.out.println("2. Show all Institute");
            System.out.println("3. Update Institute");
            System.out.println("4. Remove Institute");
            System.out.println("5. Back");
            System.out.println("6. Exit");
            System.out.println("***************************************************\n");
            System.out.print("Choose any one of the option: ");
            option = sc.nextInt();
            switch(option) {
                case 1: i.addInstitute(obj.stmt);
                    break;
                case 2: Institute.showInstitute(obj.stmt);
                    break;
                case 3: i.updateInstitute(obj.stmt);
                    break;
                case 4: i.removeInstitute(obj.stmt);
                    break;
                case 5: return;
                case 6: System.exit(0);
                    break;
                default: System.out.println("\nChoose appropriate option");
                    break;
            }
            System.out.println("**************************************************\n");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("\n************Student Counselling System************\n");
        System.out.println("**************************************************");
        MysqlCon obj = new MysqlCon();
        obj.createDB();
        System.out.println("**************************************************\n");
        Scanner sc = new Scanner(System.in);
        //----------------------------------------------------------------------
        int option=0;
        while(true) {
            System.out.println("***************************************************");
            System.out.println("1. Student related queries");
            System.out.println("2. Institute related queries");
            System.out.println("3. Branch related queries");
            System.out.println("4. Exit");
            System.out.println("***************************************************\n");
            System.out.print("Choose any one of the option: ");
            option = sc.nextInt();
            switch(option) {
                case 1: studentQueries(obj);
                    break;
                case 2: instituteQueries(obj);
                    break;
                case 3: branchQueries(obj);
                    break;
                case 4: System.exit(0);
                    break;
                default: System.out.println("Choose appropriate option");
                    break;
            }
            System.out.println("**************************************************");
        }
    }
}