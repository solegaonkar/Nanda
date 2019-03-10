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
public class ModuleInfo {
    private int id;
    private String name;
    private int fees;
    private int months;
    private String notes;
    private int batchCount = 0;

    public ModuleInfo(){}
    
    public ModuleInfo(int id, String name, int months, int fees, String notes) {
        this.id = id;
        this.name = name;
        this.months = months;
        this.fees = fees;
        this.notes = notes;
        System.out.println(getId() + ":" + getName() + ":" + getMonths() + ":" + getFees() + ":" + getNotes());

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getBatchCount() {
        return batchCount;
    }
    
    public void setBatchCount(int batchCount) {
        this.batchCount = batchCount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ModuleInfo other = (ModuleInfo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
