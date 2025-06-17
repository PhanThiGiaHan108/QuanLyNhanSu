package com.raven.utils;

import com.raven.model.Config;
import com.raven.model.Employees;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLUtils {
    
    private static final String EMPLOYEES_FILE = "employees.xml";
    private static final String CONFIG_FILE = "config.xml";
    
    public static void saveEmployees(Employees employees) {
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(employees, new File(EMPLOYEES_FILE));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    public static Employees loadEmployees() {
        File file = new File(EMPLOYEES_FILE);
        if (!file.exists()) {
            return new Employees();
        }
        
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Employees) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return new Employees();
        }
    }
    
    public static void saveConfig(Config config) {
        try {
            JAXBContext context = JAXBContext.newInstance(Config.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(config, new File(CONFIG_FILE));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    public static Config loadConfig() {
        File file = new File(CONFIG_FILE);
        if (!file.exists()) {
            Config config = new Config();
            saveConfig(config);
            return config;
        }
        
        try {
            JAXBContext context = JAXBContext.newInstance(Config.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Config) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return new Config();
        }
    }
} 