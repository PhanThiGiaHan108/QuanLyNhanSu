package com.raven.table;

import com.raven.model.Employee;
import com.raven.utils.FormatUtils;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class EmployeeTableModel extends AbstractTableModel {
    
    private final List<Employee> employees;
    private final String[] columnNames = {
        "ID", "Họ và tên", "Ngày sinh", "Phòng ban", "Vị trí", "Trình độ", "Lương", "Email", "Điện thoại","Địa chỉ"
    };
    
    public EmployeeTableModel(List<Employee> employees) {
        this.employees = employees;
    }
    
    @Override
    public int getRowCount() {
        return employees.size();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = employees.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return employee.getId();
            case 1: return employee.getName();
            case 2: return FormatUtils.formatDate(employee.getDateOfBirth());
            case 3: return employee.getDepartment();
            case 4: return employee.getPosition();
            case 5: return employee.getEducation();
            case 6: return FormatUtils.formatCurrency(employee.getSalary());
            case 7: return employee.getEmail();
            case 8: return employee.getPhone();
            case 9: return employee.getAddress();
            
            default: return null;
        }
    }
    
    public Employee getEmployeeAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < employees.size()) {
            return employees.get(rowIndex);
        }
        return null;
    }    public void addEmployee(Employee employee) {
        employees.add(employee);
        fireTableDataChanged();
    }
      public void updateEmployee(int rowIndex, Employee employee) {
        if (rowIndex >= 0 && rowIndex < employees.size()) {
            employees.set(rowIndex, employee);
            fireTableDataChanged();
        }
    }
    
    public void removeEmployee(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < employees.size()) {
            employees.remove(rowIndex);
            fireTableDataChanged();
        }
    }
    
    public void refresh() {
        fireTableDataChanged();
    }
} 