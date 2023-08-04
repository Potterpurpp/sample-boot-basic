package th.mfu;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
    private long id;

    @JsonProperty("first_name")
    private String fname;
    @JsonProperty("last_name")
    private String lname;

    private double salary;
    private String position;
    
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date birthday;
    

    public Employee(){
        ///

    }

    public Employee(long id, String fname, String lname, double salary, String position, Date birthday) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.salary = salary;
        this.position = position;
        this.birthday = birthday;
    }


    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    
    
}
