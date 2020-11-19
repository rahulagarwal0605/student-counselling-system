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
    
    Student(Student s) {
        this.rollNum = s.rollNum;
        this.name = s.name;
        this.dob = s.dob;
        this.rank = s.rank;
        this.gender = s.gender;
        this.category = s.category;
    }
    
    void createStudent(Statement stmt) {
        Scanner sc = new Scanner(System.in);
        if(rollNum == 0) {
            System.out.print("Enter Roll Number: ");
            rollNum = sc.nextInt();
            sc.nextLine();
        }
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
        System.out.print("\nEnter Home StateID: ");
        stateId = sc.nextInt();
    }
    
    void addStudent(Statement stmt) {
        try {
            stmt.execute("insert into student values (" + rollNum + ", '" + name + "', '" + dob + "', " + stateId + ", '" + gender + "', '" + category + "', " + rank + ")");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        addPreference(stmt);
        System.out.println("\nStudent added successfully!");
    }
    
    void showStudent(Statement stmt) {
        try {
            System.out.println();
            ResultSet rs = stmt.executeQuery("select roll_num, name, dob, state, gender, category, exam_rank from student inner join state on state_id = id where roll_num = " + rollNum);
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDate(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7));
            }
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("\n********************Preferences*****************************");
        showPreference(stmt);
    }
    
    boolean isRecordExist(Statement stmt) {
        try {
            ResultSet rs = stmt.executeQuery("select count(roll_num) from student where roll_num = " + rollNum);
            while(rs.next()) {
                if(rs.getInt(1) == 1) {
                    return true;
                }
                else {
                    return false;
                }
            }
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    void updateStudent(Statement stmt) {
        createStudent(stmt);
        try {
            stmt.execute("update student set name = '" + name + "', dob = '" + dob + "', state_id = " + stateId + ", gender = '" + gender + "', category = '" + category + "', exam_rank = " + rank + " where roll_num = " + rollNum + ";");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\nStudent updated successfully!");
    }
    
    void removeStudentRecord(Statement stmt) {
        try {
            stmt.execute("delete from student where roll_num = " + rollNum + ";\n" +
                "delete from choice where roll_num = " + rollNum + ";");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\nStudent removed successfully!");
    }
    
    void addPreference(Statement stmt) {
        Scanner sc = new Scanner(System.in);
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
    
    void showPreference(Statement stmt) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println();
            ResultSet rs = stmt.executeQuery("select * from branch\n" +
                "where bid in (select bid from choice where roll_num = " + rollNum + ");");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4));
            }
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    void removePreference(Statement stmt) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println();
            ResultSet rs = stmt.executeQuery("select * from branch\n" +
                "where bid in (select bid from choice where roll_num = " + rollNum + ");");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4));
            }
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        System.out.print("\nEnter total number of branches you want to remove: ");
        int count = sc.nextInt();
        for(int i=0; i<count; i++) {
            System.out.print("Enter branch ID: ");
            int id = sc.nextInt();
            try {
                stmt.execute("delete from choice where roll_num = " + rollNum + " and bid = " + id);
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    void getResult(Statement stmt) {
        boolean flag = false;
        try {
            System.out.println();
            if(gender.equals("F")) {
                ResultSet rs = stmt.executeQuery(
                    "select institute_name, city, state, branch_name, duration, degree, quota, seat_pool, " + category + "_or, " + category + "_cr\n" +
                    "from ((josaa inner join \n" +
                    "	(select iid, institute_name, city, state\n" +
                    "	from institute inner join state on institute.state_id = state.id\n" +
                    "	where iid in (select iid from institute\n" +
                    "		where state_id in (" + stateId + "))) as ins on josaa.iid = ins.iid)\n" +
                    "	inner join (select bid, branch_name, duration, degree from branch\n" +
                    "	where bid in (select bid from choice\n" +
                    "		where roll_Num = " + rollNum + ")) as bra on josaa.bid = bra.bid)\n" +
                    "where quota in ('HS', 'AI') and seat_pool in ('Gender-Neutral','Female-Only') and " + category + "_cr >= " + rank + ";");
                while(rs.next()) {
                    flag = true;
                    System.out.println("Institute Name - " + " " + rs.getString(1) + "\nCity - " + rs.getString(2) + "\nState - " + rs.getString(3) + "\nBranch Name - " + rs.getString(4) + "\nDuration - " + rs.getInt(5) + "\nDegree - " + rs.getString(6) + "\nQuota - " + rs.getString(7) + "\nSeat Pool - " + rs.getString(8) + "\nOpening Rank - " + rs.getInt(9) + "\nClosing Rank - " + rs.getInt(10) + "\n");
                }
                rs = stmt.executeQuery(
                    "select institute_name, city, state, branch_name, duration, degree, quota, seat_pool, " + category + "_or, " + category + "_cr\n" +
                    "from ((josaa inner join \n" +
                    "	(select iid, institute_name, city, state\n" +
                    "	from institute inner join state on institute.state_id = state.id\n" +
                    "	where iid in (select iid from institute\n" +
                    "		where state_id not in (" + stateId + "))) as ins on josaa.iid = ins.iid)\n" +
                    "	inner join (select bid, branch_name, duration, degree from branch\n" +
                    "	where bid in (select bid from choice\n" +
                    "		where roll_Num = " + rollNum + ")) as bra on josaa.bid = bra.bid)\n" +
                    "where quota in ('OS') and seat_pool in ('Gender-Neutral','Female-Only') and " + category + "_cr >= " + rank + ";");
                while(rs.next()) {
                    flag = true;
                    System.out.println("Institute Name - " + " " + rs.getString(1) + "\nCity - " + rs.getString(2) + "\nState - " + rs.getString(3) + "\nBranch Name - " + rs.getString(4) + "\nDuration - " + rs.getInt(5) + "\nDegree - " + rs.getString(6) + "\nQuota - " + rs.getString(7) + "\nSeat Pool - " + rs.getString(8) + "\nOpening Rank - " + rs.getInt(9) + "\nClosing Rank - " + rs.getInt(10) + "\n");
                }
                rs.close();
            }
            else {
                ResultSet rs = stmt.executeQuery(
                    "select institute_name, city, state, branch_name, duration, degree, quota, seat_pool, " + category + "_or, " + category + "_cr\n" +
                    "from ((josaa inner join \n" +
                    "	(select iid, institute_name, city, state\n" +
                    "	from institute inner join state on institute.state_id = state.id\n" +
                    "	where iid in (select iid from institute\n" +
                    "		where state_id in (" + stateId + "))) as ins on josaa.iid = ins.iid)\n" +
                    "	inner join (select bid, branch_name, duration, degree from branch\n" +
                    "	where bid in (select bid from choice\n" +
                    "		where roll_Num = " + rollNum + ")) as bra on josaa.bid = bra.bid)\n" +
                    "where quota in ('HS', 'AI') and seat_pool = 'Gender-Neutral' and " + category + "_cr >= " + rank + ";");
                while(rs.next()) {
                    flag = true;
                    System.out.println("Institute Name - " + " " + rs.getString(1) + "\nCity - " + rs.getString(2) + "\nState - " + rs.getString(3) + "\nBranch Name - " + rs.getString(4) + "\nDuration - " + rs.getInt(5) + "\nDegree - " + rs.getString(6) + "\nQuota - " + rs.getString(7) + "\nSeat Pool - " + rs.getString(8) + "\nOpening Rank - " + rs.getInt(9) + "\nClosing Rank - " + rs.getInt(10) + "\n");
                }
                rs = stmt.executeQuery(
                    "select institute_name, city, state, branch_name, duration, degree, quota, seat_pool, " + category + "_or, " + category + "_cr\n" +
                    "from ((josaa inner join \n" +
                    "	(select iid, institute_name, city, state\n" +
                    "	from institute inner join state on institute.state_id = state.id\n" +
                    "	where iid in (select iid from institute\n" +
                    "		where state_id not in (" + stateId + "))) as ins on josaa.iid = ins.iid)\n" +
                    "	inner join (select bid, branch_name, duration, degree from branch\n" +
                    "	where bid in (select bid from choice\n" +
                    "		where roll_Num = " + rollNum + ")) as bra on josaa.bid = bra.bid)\n" +
                    "where quota in ('OS') and seat_pool = 'Gender-Neutral' and " + category + "_cr >= " + rank + ";");
                while(rs.next()) {
                    flag = true;
                    System.out.println("Institute Name - " + " " + rs.getString(1) + "\nCity - " + rs.getString(2) + "\nState - " + rs.getString(3) + "\nBranch Name - " + rs.getString(4) + "\nDuration - " + rs.getInt(5) + "\nDegree - " + rs.getString(6) + "\nQuota - " + rs.getString(7) + "\nSeat Pool - " + rs.getString(8) + "\nOpening Rank - " + rs.getInt(9) + "\nClosing Rank - " + rs.getInt(10) + "\n");
                }
                rs.close();
            }
            if(!flag) {
                System.out.println("Sorry no branch is allocated to you!");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}