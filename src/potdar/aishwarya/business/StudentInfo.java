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
public class StudentInfo {
    private int id;
    private String name;
    private long phone;
    private String email;

    public StudentInfo(){}
    public StudentInfo(int id, String name, long phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean matches(String s) {
        System.out.println("match " + s + " " + this.name);
        return (s == null) || (name.toLowerCase().contains(s.toLowerCase()));
    }
    
}
