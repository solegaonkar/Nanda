/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potdar.aishwarya.business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author soleg
 */
public class Database {
    private static ArrayList<StudentInfo> studentList;
    private static Connection con;
    private static Statement stmt;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");  
        } catch (Exception e){
            e.printStackTrace();
        }
        loadStudentList();
    }

    public static void saveStudent(StudentInfo s) {
        try {
            String sql;
            connect();
            if (s.getId() == 0) {
                // Insert
                sql = String.format("INSERT INTO STUDENT (name, phone, email) VALUES ('%s', %d, '%s')", s.getName(), s.getPhone(), s.getEmail());
            } else {
                // Update
                sql = String.format("UPDATE STUDENT set name='%s', phone=%d, email='%s' WHERE id = %d", s.getName(), s.getPhone(), s.getEmail(), s.getId());
            }
            System.out.println(sql);
            stmt.execute(sql);
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadStudentList();
    }

    public static void deleteStudent(int id) {
        try {
            String sql;
            connect();
            sql = String.format("DELETE FROM STUDENT WHERE id = %d", id);
            stmt.execute(sql);
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadStudentList();
    }

    public static ArrayList<StudentInfo> getStudentList(String filter) {
        System.out.println("getStudentList " + studentList.size());
        ArrayList<StudentInfo> filteredList = new ArrayList<>();
        for (StudentInfo s : studentList) {
            System.out.println("Checking Match " + s.getName());
            if (s.matches(filter)) {
                System.out.println("Matches ");
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    private static void loadStudentList() {
        studentList = new ArrayList<StudentInfo>();
        try {
            connect();
            System.out.println("Connected");
            ResultSet rs = stmt.executeQuery("SELECT id, name, phone, email FROM STUDENT");
            System.out.println("RS");
            while (rs.next()) {
                studentList.add(new StudentInfo(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getString(4)));
                System.out.println("Added " + rs.getString(2));
            }
            System.out.println(studentList.size());
            rs.close();
            close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void connect()throws Exception{
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdb","root","");  
        stmt=con.createStatement();
    }
    
    private static void close() throws Exception {
        stmt.close();
        con.close();  
    }
}

