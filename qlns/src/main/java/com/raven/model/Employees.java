package com.raven.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {
    
    @XmlElement(name = "employee")
    private List<Employee> employees;
    
    public Employees() {
        employees = new ArrayList<>();
    }
    
    public List<Employee> getEmployees() {
        return employees;
    }
    
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
    public void addEmployee(Employee employee) {
        // Check for duplicates before adding
        if (isEmailExists(employee.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại trong hệ thống");
        }
        
        if (isPhoneExists(employee.getPhone())) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại trong hệ thống");
        }
        
        this.employees.add(employee);
    }
    
    public void updateEmployee(Employee employee) {
        // Find the employee index
        int index = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == employee.getId()) {
                index = i;
                break;
            }
        }
        
        if (index == -1) {
            throw new IllegalArgumentException("Không tìm thấy nhân viên cần cập nhật");
        }
        
        // Check for duplicate email
        for (Employee existingEmp : employees) {
            if (existingEmp.getId() == employee.getId()) {
                continue; // Skip comparing with self
            }
            
            if (employee.getEmail().equalsIgnoreCase(existingEmp.getEmail())) {
                throw new IllegalArgumentException("Email đã tồn tại trong hệ thống");
            }
            
            if (employee.getPhone().equals(existingEmp.getPhone())) {
                throw new IllegalArgumentException("Số điện thoại đã tồn tại trong hệ thống");
            }
        }
        
        // Update the employee
        employees.set(index, employee);
    }
    
    public boolean isEmailExists(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        for (Employee existingEmp : employees) {
            if (email.equalsIgnoreCase(existingEmp.getEmail())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isPhoneExists(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        
        for (Employee existingEmp : employees) {
            if (phone.equals(existingEmp.getPhone())) {
                return true;
            }
        }
        return false;
    }
    
    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
    }
    
    public int getNextId() {
        if (employees.isEmpty()) {
            return 1;
        }
        
        int maxId = 0;
        for (Employee employee : employees) {
            if (employee.getId() > maxId) {
                maxId = employee.getId();
            }
        }
        return maxId + 1;
    }
} 