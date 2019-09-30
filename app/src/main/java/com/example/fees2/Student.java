package com.example.fees2;

public class Student {
    public String name;
    public String year;
    public String caste;
    public int totalFees;
    public int remainingFees;


    public Student(String name, String year, String caste, int totalFees, int remainingFees) {
        this.name = name;
        this.year = year;
        this.caste = caste;
        this.totalFees = totalFees;
        this.remainingFees = remainingFees;

    }

    public Student(){

    }

    public int getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(int totalFees) {
        this.totalFees = totalFees;
    }

    public int getRemainingFees() {
        return remainingFees;
    }

    public void setRemainingFees(int remainingFees) {
        this.remainingFees = remainingFees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", caste='" + caste + '\'' +
                ", totalFees=" + totalFees +
                ", remainingFees=" + remainingFees +
                '}';
    }
}
