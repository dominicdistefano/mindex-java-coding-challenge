package com.mindex.challenge.data;

public class Compensation {
    private String employee;
    private String salary;
    private String effectiveDate;

    public Compensation(){

    }

    public String getEmployee(){
        return employee;
    }

    public void setEmployee(String employee){
        this.employee = employee;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
