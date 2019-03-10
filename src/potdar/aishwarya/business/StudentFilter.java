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
public class StudentFilter {
    private String name = "";
    private int batchId = 0;
    private int moduleId = 0;
    private boolean feesPaid = false;
    private boolean feesNotPaid = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public boolean isFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(boolean feesPaid) {
        this.feesPaid = feesPaid;
    }

    public boolean isFeesNotPaid() {
        return feesNotPaid;
    }

    public void setFeesNotPaid(boolean feesNotPaid) {
        this.feesNotPaid = feesNotPaid;
    }
}
