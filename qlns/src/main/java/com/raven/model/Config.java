package com.raven.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "config")
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {
    
    @XmlElementWrapper(name = "departments")
    @XmlElement(name = "department")
    private List<String> departments;
    
    @XmlElementWrapper(name = "positions")
    @XmlElement(name = "position")
    private List<String> positions;
    
    @XmlElementWrapper(name = "educations")
    @XmlElement(name = "education")
    private List<String> educations;
    
    public Config() {
        departments = new ArrayList<>();
        positions = new ArrayList<>();
        educations = new ArrayList<>();
        
        // Initialize with default values if needed
        if (departments.isEmpty()) {
            departments.add("HR");
            departments.add("IT");
            departments.add("Finance");
            departments.add("Marketing");
            departments.add("Operations");
        }
        
        if (positions.isEmpty()) {
            positions.add("Manager");
            positions.add("Team Lead");
            positions.add("Senior Specialist");
            positions.add("Specialist");
            positions.add("Junior Specialist");
            positions.add("Intern");
        }
        
        if (educations.isEmpty()) {
            educations.add("Ph.D");
            educations.add("Master's Degree");
            educations.add("Bachelor's Degree");
            educations.add("Associate Degree");
            educations.add("High School");
        }
    }
    
    public List<String> getDepartments() {
        return departments;
    }
    
    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }
    
    public List<String> getPositions() {
        return positions;
    }
    
    public void setPositions(List<String> positions) {
        this.positions = positions;
    }
    
    public List<String> getEducations() {
        return educations;
    }
    
    public void setEducations(List<String> educations) {
        this.educations = educations;
    }
    
    public void addDepartment(String department) {
        if (!departments.contains(department)) {
            departments.add(department);
        }
    }
    
    public void addPosition(String position) {
        if (!positions.contains(position)) {
            positions.add(position);
        }
    }
    
    public void addEducation(String education) {
        if (!educations.contains(education)) {
            educations.add(education);
        }
    }
} 