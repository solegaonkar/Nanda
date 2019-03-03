/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potdar.aishwarya.business;

/**
 *
 * @author soleg
 */
public class BatchInfo {
    private int id = 0;
    private ModuleInfo module;
    private String name;
    private String schedule;
    private int studentCount = 0;

    public BatchInfo(){}
    
    public BatchInfo(int id, String name, ModuleInfo module, String schedule) {
        this.id = id;
        this.module = module;
        this.name = name;
        this.schedule = schedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ModuleInfo getModule() {
        return module;
    }

    public void setModule(ModuleInfo module) {
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    
    @Override
    public String toString() {
        return this.name;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
    
    
}
