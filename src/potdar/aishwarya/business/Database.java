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

/**`
 *
 * @author soleg
 */
public class Database {
    private static ArrayList<StudentInfo> studentList;
    private static ArrayList<BatchInfo> batchList;
    private static ArrayList<ModuleInfo> moduleList;
    private static Connection con;
    private static Statement stmt;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            loadModuleList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadStudentList() {
        studentList = new ArrayList<StudentInfo>();
        try {
            connect();
            System.out.println("Connected");
            ResultSet rs = stmt.executeQuery("SELECT id, name, phone, email, fees_paid, batch_id FROM STUDENT");
            while (rs.next()) {
                studentList.add(new StudentInfo(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getInt(5), getBatch(rs.getInt(6))));
            }
            rs.close();
            close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        for (BatchInfo b : batchList) {
            b.setStudentCount(0);
        }
        for (StudentInfo s : studentList) {
            s.getBatch().setStudentCount(s.getBatch().getStudentCount() + 1);
        }
    }

    private static void loadBatchList() {
        batchList = new ArrayList<>();
        try {
            connect();
            ResultSet rs = stmt.executeQuery("SELECT id, name, module_id, schedule FROM BATCH");
            while (rs.next()) {
                batchList.add(new BatchInfo(rs.getInt(1), rs.getString(2), getModule(rs.getInt(3)), rs.getString(4)));
            }
            rs.close();
            close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        loadStudentList();
        for (ModuleInfo m : moduleList) {
            m.setBatchCount(0);
        }
        for (BatchInfo b : batchList) {
            b.getModule().setBatchCount(b.getModule().getBatchCount() + 1);
        }
    }

    private static void loadModuleList() {
        moduleList = new ArrayList<>();
        try {
            connect();
            ResultSet rs = stmt.executeQuery("SELECT id, name, months, fees, notes FROM MODULE");
            while (rs.next()) {
                moduleList.add(new ModuleInfo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
            }
            rs.close();
            close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        loadBatchList();
    }

    private static void connect() throws Exception {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdb", "root", "");
        stmt = con.createStatement();
    }

    private static void close() throws Exception {
        stmt.close();
        con.close();
    }

    public static void saveStudent(StudentInfo s) {
        try {
            String sql;
            connect();
            if (s.getId() == 0) {
                // Insert
                sql = String.format("INSERT INTO STUDENT (name, phone, email, batch_id) VALUES ('%s', %d, '%s', %d)", s.getName(), s.getPhone(), s.getEmail(), s.getBatch().getId());
            } else {
                // Update
                sql = String.format("UPDATE STUDENT set name='%s', phone=%d, email='%s', fees_paid=%d, batch_id=%d WHERE id = %d", s.getName(), s.getPhone(), s.getEmail(), s.getFeesPaid(), s.getBatch().getId(), s.getId());
            }
            System.out.println(sql);
            stmt.execute(sql);
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadStudentList();
    }

    public static void saveBatch(BatchInfo s) {
        try {
            String sql;
            connect();
            if (s.getId() == 0) {
                // Insert
                sql = String.format("INSERT INTO BATCH (name, module_id, schedule) VALUES ('%s', %d, '%s')", s.getName(), s.getModule().getId(), s.getSchedule());
            } else {
                // Update
                sql = String.format("UPDATE BATCH set name='%s', module_id=%d, schedule='%s' WHERE id = %d", s.getName(), s.getModule().getId(), s.getSchedule(), s.getId());
            }
            stmt.execute(sql);
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadBatchList();
    }

    public static void saveModule(ModuleInfo s) {
        try {
            String sql;
            connect();
            if (s.getId() == 0) {
                // Insert
                sql = String.format("INSERT INTO MODULE (name, months, fees, notes) VALUES ('%s', %d, %d, '%s')", s.getName(), s.getMonths(), s.getFees(), s.getNotes().replaceAll("'", "''"));
            } else {
                // Update
                sql = String.format("UPDATE MODULE set name='%s', months=%d, fees=%d, notes='%s' WHERE id = %d", s.getName(), s.getMonths(), s.getFees(), s.getNotes().replaceAll("'", "''"), s.getId());
            }
            stmt.execute(sql);
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadModuleList();
    }

    public static void deleteStudent(int id) {
        try {
            connect();
            stmt.execute(String.format("DELETE FROM STUDENT WHERE id = %d", id));
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadStudentList();
    }
    
    public static void deleteBatch(int id) {
        try {
            connect();
            stmt.execute(String.format("DELETE FROM BATCH WHERE id = %d", id));
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadBatchList();
    }

    public static void deleteModule(int id) {
        try {
            connect();
            stmt.execute(String.format("DELETE FROM MODULE WHERE id = %d", id));
            System.out.println("Deleting Module: " + id);

            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadModuleList();
    }

    public static void resetBatchStudentFees(int id) {
        try {
            String sql;
            connect();
            sql = String.format("UPDATE STUDENT set fees_paid=0 WHERE batch_id = %d", id);
            System.out.println(sql);
            stmt.execute(sql);
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadStudentList();
    }

    public static BatchInfo getBatch(int id) {
        for (BatchInfo b : batchList) {
            if (b.getId() == id) 
                return b;
        }
        return null;
    }

    public static ModuleInfo getModule(int id) {
        for (ModuleInfo m : moduleList) {
            if (m.getId() == id) 
                return m;
        }
        return null;
    }
    
    public static ArrayList<ModuleInfo> getModuleList() {
        return moduleList;
    }
    
    public static ArrayList<BatchInfo> getBatchList() {
        return batchList;
    }
    
    public static ArrayList<StudentInfo> getStudentList() {
        return studentList;
    }

    public static ArrayList<StudentInfo> getStudentList(StudentFilter filter) {
        ArrayList<StudentInfo> filterList = new ArrayList<>();
        for (StudentInfo s : studentList) {
            if (s.matches(filter))
                filterList.add(s);
        }
        return filterList;
    }
    
    public static ArrayList<BatchInfo> getFilteredBatchList(StudentFilter filter) {
        ArrayList<BatchInfo> filterList = new ArrayList<>();
        for (BatchInfo b : batchList)
            if ( filter.getModuleId() > 0 && filter.getModuleId() == b.getId())
                filterList.add(b);
        return filterList;
    }
}
