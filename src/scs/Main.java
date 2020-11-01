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
        Student s = new Student();
        System.out.println("\nAdd student details");
        s.createStudent(obj.stmt);
        if(s.isRecordExist(obj.stmt)) {
            System.out.print("Student with this roll number already exists. Do you want to update it (y/n): ");
            String op = sc.next();
            if(op.equals("y")) {
                s.updateStudent(obj.stmt);
            }
            else if(!op.equals("n")) {
                studentQueries(obj);
            }
        }
        else {
            s.addStudent(obj.stmt);
        }
        while(true) {
            System.out.println("\n**************************************************");
            System.out.println("1. Show student details");
            System.out.println("2. Update student details");
            System.out.println("3. Show student preferences");
            System.out.println("4. Add preferences");
            System.out.println("5. Remove preferences");
            System.out.println("6. Search for college");
            System.out.println("7. Back");
            System.out.println("8. Exit");
            System.out.println("***************************************************\n");
            System.out.print("Choose any one of the option: ");
            option = sc.nextInt();
            switch(option) {
                case 1: s.showStudent(obj.stmt);
                    break;
                case 2: s.updateStudent(obj.stmt);
                    break;
                case 3: s.showPreference(obj.stmt);
                    break;
                case 4: s.addPreference(obj.stmt);
                    break;
                case 5: s.removePreference(obj.stmt);
                    break;
                case 6: s.getResult(obj.stmt);
                    break;
                case 7: return;
                case 8: System.exit(0);
                default: System.out.println("\nChoose appropriate option");
            }
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
                default: System.out.println("\nChoose appropriate option");
            }
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
                default: System.out.println("\nChoose appropriate option");
            }
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
            System.out.println("**************************************************");
            System.out.println("1. Student related queries");
            System.out.println("2. Institute related queries");
            System.out.println("3. Branch related queries");
            System.out.println("4. Exit");
            System.out.println("**************************************************\n");
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
                default: System.out.println("Choose appropriate option");
            }
        }
    }
}