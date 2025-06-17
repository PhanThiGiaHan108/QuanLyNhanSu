package com.raven.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.raven.utils.DateAdapter;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    
    @XmlElement(name = "id")
    private int id;
    
    @XmlElement(name = "name")
    private String name;
    
    @XmlElement(name = "dateOfBirth")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dateOfBirth;
    
    @XmlElement(name = "department")
    private String department;
    
    @XmlElement(name = "position")
    private String position;
    
    @XmlElement(name = "education")
    private String education;
    
    @XmlElement(name = "salary")
    private double salary;
    
    @XmlElement(name = "email")
    private String email;
    
    @XmlElement(name = "phone")
    private String phone;
    
    @XmlElement(name = "address")
    private String address;
    
    public Employee() {
    }
    
    public Employee(int id, String name, Date dateOfBirth, String department, 
            String position, String education, double salary, String email, 
            String phone, String address) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.position = position;
        this.education = education;
        this.salary = salary;
        this.email = email;
        this.phone = phone;
        this.address = address;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Employee other = (Employee) obj;
        return this.id == other.id;
    }
    
    @Override
    public int hashCode() {
        return 31 * id;
    }
} 