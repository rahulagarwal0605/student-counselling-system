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
    
    Student() {
        
    }
    
    Student(int rollNum, String name, String dob, int rank, String gender, String category) {
        this.rollNum = rollNum;
        this.name = name;
        this.dob = dob;
        this.rank = rank;
        this.gender = gender;
        this.category = category;
    }
    
    void addStudent(Statement stmt) {
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************");
        System.out.println("Enter Student Details\n");
        System.out.print("Enter Roll Number: ");
        rollNum = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        name = sc.nextLine();
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        dob = sc.next();
        System.out.print("Enter Rank: ");
        rank = sc.nextInt();
        System.out.print("Enter Gender (M/F/O): ");
        gender = sc.next();
        System.out.print("Enter Category (gen/gen_ews/obc/sc/st): ");
        category = sc.next();
        System.out.println("******************************");
        addStudent(this, stmt);
    }
    
    static void addStudent(int rollNum, String name, String dob, int rank, String gender, String category, Statement stmt) {
        Student s = new Student(rollNum, name, dob, rank, gender, category);
        addStudent(s, stmt);
    }
    
    static void addStudent(Student s, Statement stmt) {
        try {
            System.out.println();
            ResultSet rs = stmt.executeQuery("select * from state");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        System.out.print("\nChoose Home State: ");
        Scanner sc = new Scanner(System.in);
        s.stateId = sc.nextInt();
        try {
            stmt.execute("insert into student values (" + s.rollNum + ", '" + s.name + "', '" + s.dob + "', " + s.stateId + ", '" + s.gender + "', '" + s.category + "', " + s.rank + ")");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        s.addPreference(stmt);
    }
    
    void addPreference(Statement stmt) {
        addPreference(this.rollNum, stmt);
    }
    
    static void addPreference(Student s, Statement stmt) {
        addPreference(s.rollNum, stmt);
    }
    
    static void addPreference(int rollNum, Statement stmt) {
        try {
            System.out.println();
            ResultSet rs = stmt.executeQuery("select * from branch");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4));
            }
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        System.out.print("\nEnter total count of your preferred branches (Choose 0 to select all): ");
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        for(int i=0; i<count; i++) {
            System.out.print("Enter branch ID: ");
            int id = sc.nextInt();
            try {
                stmt.execute("insert into choice (roll_num, bid)  values (" + rollNum + ", " + id + ")");
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(count == 0) {
            try {
                ResultSet rs = stmt.executeQuery("select count(bid) from branch");
                while(rs.next())
                    count = rs.getInt(1);
                int choices[] = new int[count];
                rs = stmt.executeQuery("select bid from branch");
                int i=0;
                while(rs.next()) {
                    choices[i] = rs.getInt(1);
                    i++;
                }
                rs.close();
                for(i=0; i<count; i++) {
                    stmt.execute("insert into choice (roll_num, bid)  values (" + rollNum + ", " + choices[i] + ")");
                }
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
 
    void getResult(Statement stmt) {
        getResult(this, stmt);
    }
    
    static void getResult(Student s, Statement stmt) {
        try {
            System.out.println();
            if(s.gender.equals("F")) {
                ResultSet rs = stmt.executeQuery(
                    "select institute_name, city, state, branch_name, duration, degree, quota, seat_pool, " + s.category + "_or, " + s.category + "_cr\n" +
                    "from ((josaa inner join \n" +
                    "	(select iid, institute_name, city, state\n" +
                    "	from institute inner join state on institute.state_id = state.id\n" +
                    "	where iid in (select iid from institute\n" +
                    "		where state_id in (" + s.stateId + "))) as ins on josaa.iid = ins.iid)\n" +
                    "	inner join (select bid, branch_name, duration, degree from branch\n" +
                    "	where bid in (select bid from choice\n" +
                    "		where roll_Num = " + s.rollNum + ")) as bra on josaa.bid = bra.bid)\n" +
                    "where quota in ('HS', 'AI') and seat_pool in ('Gender-Neutral','Female-Only') and " + s.category + "_cr >= " + s.rank + ";");
                while(rs.next()) {
                    System.out.println("Institute Name - " + " " + rs.getString(1) + "\nCity - " + rs.getString(2) + "\nState - " + rs.getString(3) + "\nBranch Name - " + rs.getString(4) + "\nDuration - " + rs.getInt(5) + "\nDegree - " + rs.getString(6) + "\nQuota - " + rs.getString(7) + "\nSeat Pool - " + rs.getString(8) + "\nOpening Rank - " + rs.getInt(9) + "\nClosing Rank - " + rs.getInt(10) + "\n");
                }
                rs = stmt.executeQuery(
                    "select institute_name, city, state, branch_name, duration, degree, quota, seat_pool, " + s.category + "_or, " + s.category + "_cr\n" +
                    "from ((josaa inner join \n" +
                    "	(select iid, institute_name, city, state\n" +
                    "	from institute inner join state on institute.state_id = state.id\n" +
                    "	where iid in (select iid from institute\n" +
                    "		where state_id not in (" + s.stateId + "))) as ins on josaa.iid = ins.iid)\n" +
                    "	inner join (select bid, branch_name, duration, degree from branch\n" +
                    "	where bid in (select bid from choice\n" +
                    "		where roll_Num = " + s.rollNum + ")) as bra on josaa.bid = bra.bid)\n" +
                    "where quota in ('OS') and seat_pool in ('Gender-Neutral','Female-Only') and " + s.category + "_cr >= " + s.rank + ";");
                while(rs.next()) {
                    System.out.println("Institute Name - " + " " + rs.getString(1) + "\nCity - " + rs.getString(2) + "\nState - " + rs.getString(3) + "\nBranch Name - " + rs.getString(4) + "\nDuration - " + rs.getInt(5) + "\nDegree - " + rs.getString(6) + "\nQuota - " + rs.getString(7) + "\nSeat Pool - " + rs.getString(8) + "\nOpening Rank - " + rs.getInt(9) + "\nClosing Rank - " + rs.getInt(10) + "\n");
                }
                rs.close();
            }
            else {
                ResultSet rs = stmt.executeQuery(
                    "select institute_name, city, state, branch_name, duration, degree, quota, seat_pool, " + s.category + "_or, " + s.category + "_cr\n" +
                    "from ((josaa inner join \n" +
                    "	(select iid, institute_name, city, state\n" +
                    "	from institute inner join state on institute.state_id = state.id\n" +
                    "	where iid in (select iid from institute\n" +
                    "		where state_id in (" + s.stateId + "))) as ins on josaa.iid = ins.iid)\n" +
                    "	inner join (select bid, branch_name, duration, degree from branch\n" +
                    "	where bid in (select bid from choice\n" +
                    "		where roll_Num = " + s.rollNum + ")) as bra on josaa.bid = bra.bid)\n" +
                    "where quota in ('HS', 'AI') and seat_pool = 'Gender-Neutral' and " + s.category + "_cr >= " + s.rank + ";");
                while(rs.next()) {
                    System.out.println("Institute Name - " + " " + rs.getString(1) + "\nCity - " + rs.getString(2) + "\nState - " + rs.getString(3) + "\nBranch Name - " + rs.getString(4) + "\nDuration - " + rs.getInt(5) + "\nDegree - " + rs.getString(6) + "\nQuota - " + rs.getString(7) + "\nSeat Pool - " + rs.getString(8) + "\nOpening Rank - " + rs.getInt(9) + "\nClosing Rank - " + rs.getInt(10) + "\n");
                }
                rs = stmt.executeQuery(
                    "select institute_name, city, state, branch_name, duration, degree, quota, seat_pool, " + s.category + "_or, " + s.category + "_cr\n" +
                    "from ((josaa inner join \n" +
                    "	(select iid, institute_name, city, state\n" +
                    "	from institute inner join state on institute.state_id = state.id\n" +
                    "	where iid in (select iid from institute\n" +
                    "		where state_id not in (" + s.stateId + "))) as ins on josaa.iid = ins.iid)\n" +
                    "	inner join (select bid, branch_name, duration, degree from branch\n" +
                    "	where bid in (select bid from choice\n" +
                    "		where roll_Num = " + s.rollNum + ")) as bra on josaa.bid = bra.bid)\n" +
                    "where quota in ('OS') and seat_pool = 'Gender-Neutral' and " + s.category + "_cr >= " + s.rank + ";");
                while(rs.next()) {
                    System.out.println("Institute Name - " + " " + rs.getString(1) + "\nCity - " + rs.getString(2) + "\nState - " + rs.getString(3) + "\nBranch Name - " + rs.getString(4) + "\nDuration - " + rs.getInt(5) + "\nDegree - " + rs.getString(6) + "\nQuota - " + rs.getString(7) + "\nSeat Pool - " + rs.getString(8) + "\nOpening Rank - " + rs.getInt(9) + "\nClosing Rank - " + rs.getInt(10) + "\n");
                }
                rs.close();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}