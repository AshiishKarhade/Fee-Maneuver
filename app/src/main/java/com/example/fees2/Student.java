package com.example.fees2;

public class Student {
    public String name;
    public String year;
    public String caste;

    public Student(String name, String year, String caste) {
        this.name = name;
        this.year = year;
        this.caste = caste;
    }

    public Student(){

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
                '}';
    }
}
