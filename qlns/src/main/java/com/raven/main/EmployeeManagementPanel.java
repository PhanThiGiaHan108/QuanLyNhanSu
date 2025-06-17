package com.raven.main;

import com.raven.model.Config;
import com.raven.model.Employee;
import com.raven.model.Employees;
import com.raven.table.EmployeeTableModel;
import com.raven.utils.FormatUtils;
import com.raven.utils.XMLUtils;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class EmployeeManagementPanel extends JPanel {
    
    private Employees employees;
    private Config config;
    private EmployeeTableModel tableModel;
    private int selectedRow = -1;
    
    // UI Components
    private JPanel formPanel;
    private JPanel actionPanel;
    private JPanel searchPanel;
    private JPanel tablePanel;
    
    private JLabel lblId;
    private JLabel lblName;
    private JLabel lblDateOfBirth;
    private JLabel lblDepartment;
    private JLabel lblPosition;
    private JLabel lblEducation;
    private JLabel lblSalary;
    private JLabel lblEmail;
    private JLabel lblPhone;
    private JLabel lblAddress;
    
    private JTextField txtId;
    private JTextField txtName;
    private JDateChooser dateChooser;
    private JComboBox<String> cboDepartment;
    private JComboBox<String> cboPosition;
    private JComboBox<String> cboEducation;
    private JTextField txtSalary;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtAddress;
    
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    
    private JLabel lblSearchName;
    private JTextField txtSearchName;
    private JLabel lblSalaryFrom;
    private JTextField txtSalaryFrom;
    private JLabel lblSalaryTo;
    private JTextField txtSalaryTo;
    private JButton btnSearch;
    private JButton btnClearSearch;
    
    private JTable tblEmployees;
    private JScrollPane scrollPane;
    
    public EmployeeManagementPanel(Employees employees, Config config) {
        this.employees = employees;
        this.config = config;
        
        initComponents();
        setupUI();
        setupListeners();
        loadData();
    }
    
    private void initComponents() {
        formPanel = new JPanel();
        actionPanel = new JPanel();
        searchPanel = new JPanel();
        tablePanel = new JPanel();
        
        lblId = new JLabel("Mã nhân viên:");
        lblName = new JLabel("Họ và tên:");
        lblDateOfBirth = new JLabel("Ngày sinh:");
        lblDepartment = new JLabel("Phòng ban:");
        lblPosition = new JLabel("Vị trí:");
        lblEducation = new JLabel("Trình độ học vấn:");
        lblSalary = new JLabel("Lương:");
        lblEmail = new JLabel("Email:");
        lblPhone = new JLabel("Điện thoại:");
        lblAddress = new JLabel("Địa chỉ:");
        
        txtId = new JTextField();
        txtId.setEditable(false);
        txtName = new JTextField();
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        cboDepartment = new JComboBox<>();
        cboPosition = new JComboBox<>();
        cboEducation = new JComboBox<>();
        txtSalary = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();
        txtAddress = new JTextField();
        
      btnAdd = new JButton("Thêm", new ImageIcon(getClass().getResource("/image/add.png")));


        btnUpdate = new JButton("Sửa", new ImageIcon(getClass().getResource("/image/edit.png")));
        btnDelete = new JButton("Xóa", new ImageIcon(getClass().getResource("/image/delete.png")));
        btnClear = new JButton("Làm mới", new ImageIcon(getClass().getResource("/image/trash.png")));
        
        lblSearchName = new JLabel("Tìm theo tên:");
        txtSearchName = new JTextField();
        lblSalaryFrom = new JLabel("Lương từ:");
        txtSalaryFrom = new JTextField();
        lblSalaryTo = new JLabel("Lương đến:");
        txtSalaryTo = new JTextField();
        btnSearch = new JButton("Tìm kiếm", new ImageIcon(getClass().getResource("/image/search.png")));
        btnClearSearch = new JButton("Xóa tìm kiếm", new ImageIcon(getClass().getResource("/image/delete.png")));
        
        tblEmployees = new JTable();
        scrollPane = new JScrollPane(tblEmployees);
    }
    
    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Form panel
        formPanel.setLayout(null);
        formPanel.setBorder(BorderFactory.createTitledBorder(
                null, "Thông tin nhân viên", TitledBorder.DEFAULT_JUSTIFICATION, 
                TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14)));
        formPanel.setPreferredSize(new Dimension(350, 500));
        
        // Form components positioning
        lblId.setBounds(20, 30, 100, 25);
        txtId.setBounds(130, 30, 200, 25);
        
        lblName.setBounds(20, 65, 100, 25);
        txtName.setBounds(130, 65, 200, 25);
        
        lblDateOfBirth.setBounds(20, 100, 100, 25);
        dateChooser.setBounds(130, 100, 200, 25);
        
        lblDepartment.setBounds(20, 135, 100, 25);
        cboDepartment.setBounds(130, 135, 200, 25);
        
        lblPosition.setBounds(20, 170, 100, 25);
        cboPosition.setBounds(130, 170, 200, 25);
        
        lblEducation.setBounds(20, 205, 120, 25);
        cboEducation.setBounds(130, 205, 200, 25);
        
        lblSalary.setBounds(20, 240, 100, 25);
        txtSalary.setBounds(130, 240, 200, 25);
        
        lblEmail.setBounds(20, 275, 100, 25);
        txtEmail.setBounds(130, 275, 200, 25);
        
        lblPhone.setBounds(20, 310, 100, 25);
        txtPhone.setBounds(130, 310, 200, 25);
        
        lblAddress.setBounds(20, 345, 100, 25);
        txtAddress.setBounds(130, 345, 200, 25);
        
        // Add components to form panel
        formPanel.add(lblId);
        formPanel.add(txtId);
        formPanel.add(lblName);
        formPanel.add(txtName);
        formPanel.add(lblDateOfBirth);
        formPanel.add(dateChooser);
        formPanel.add(lblDepartment);
        formPanel.add(cboDepartment);
        formPanel.add(lblPosition);
        formPanel.add(cboPosition);
        formPanel.add(lblEducation);
        formPanel.add(cboEducation);
        formPanel.add(lblSalary);
        formPanel.add(txtSalary);
        formPanel.add(lblEmail);
        formPanel.add(txtEmail);
        formPanel.add(lblPhone);
        formPanel.add(txtPhone);
        formPanel.add(lblAddress);
        formPanel.add(txtAddress);
        
        // Action panel
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
        actionPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        actionPanel.add(btnAdd);
        actionPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        actionPanel.add(btnUpdate);
        actionPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        actionPanel.add(btnDelete);
        actionPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        actionPanel.add(btnClear);
        
        // Right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout(0, 10));
        
        // Search panel
        searchPanel.setLayout(null);
        searchPanel.setBorder(BorderFactory.createTitledBorder(
                null, "Tìm kiếm", TitledBorder.DEFAULT_JUSTIFICATION, 
                TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14)));
        searchPanel.setPreferredSize(new Dimension(600, 100));
        
        lblSearchName.setBounds(20, 30, 100, 25);
        txtSearchName.setBounds(120, 30, 150, 25);
        
        lblSalaryFrom.setBounds(290, 30, 70, 25);
        txtSalaryFrom.setBounds(360, 30, 100, 25);
        
        lblSalaryTo.setBounds(470, 30, 70, 25);
        txtSalaryTo.setBounds(540, 30, 100, 25);
        
        btnSearch.setBounds(400, 70, 150, 25);
        btnClearSearch.setBounds(570, 70, 150, 25);
        
        searchPanel.add(lblSearchName);
        searchPanel.add(txtSearchName);
        searchPanel.add(lblSalaryFrom);
        searchPanel.add(txtSalaryFrom);
        searchPanel.add(lblSalaryTo);
        searchPanel.add(txtSalaryTo);
        searchPanel.add(btnSearch);
        searchPanel.add(btnClearSearch);
        
        // Table panel
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(
                null, "Danh sách nhân viên", TitledBorder.DEFAULT_JUSTIFICATION, 
                TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14)));
        
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add panels to right panel
        rightPanel.add(searchPanel, BorderLayout.NORTH);
        rightPanel.add(tablePanel, BorderLayout.CENTER);
        
        // Add all panels to main panel
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(formPanel, BorderLayout.CENTER);
        leftPanel.add(actionPanel, BorderLayout.SOUTH);
        
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }
    
    private void setupTable() {
        if (tblEmployees.getColumnCount() == 0) {
            return;
        }
        
        tblEmployees.setAutoCreateRowSorter(true);
        tblEmployees.setRowHeight(25);
        
        JTableHeader header = tblEmployees.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(51, 102, 255));
        header.setForeground(Color.WHITE);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        tblEmployees.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblEmployees.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblEmployees.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblEmployees.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
    }
    
    private void setupListeners() {
        btnAdd.addActionListener((ActionEvent e) -> {
            addEmployee();
        });
        
        btnUpdate.addActionListener((ActionEvent e) -> {
            updateEmployee();
        });
        
        btnDelete.addActionListener((ActionEvent e) -> {
            deleteEmployee();
        });
        
        btnClear.addActionListener((ActionEvent e) -> {
            clearForm();
        });
        
        btnSearch.addActionListener((ActionEvent e) -> {
            searchEmployees();
        });
        
        btnClearSearch.addActionListener((ActionEvent e) -> {
            clearSearch();
        });
        
        tblEmployees.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow = tblEmployees.getSelectedRow();
                if (selectedRow >= 0) {
                    selectedRow = tblEmployees.convertRowIndexToModel(selectedRow);
                    displayEmployeeDetails(selectedRow);
                }
            }
        });
    }
    
    private void loadData() {
        // Load dropdown data
        for (String department : config.getDepartments()) {
            cboDepartment.addItem(department);
        }
        
        for (String position : config.getPositions()) {
            cboPosition.addItem(position);
        }
        
        for (String education : config.getEducations()) {
            cboEducation.addItem(education);
        }
        
        // Load table data
        tableModel = new EmployeeTableModel(employees.getEmployees());
        tblEmployees.setModel(tableModel);
        
        // Gọi setupTable sau khi đã thiết lập model
        setupTable();
    }
    
    private void addEmployee() {
        // Validate input
        if (!validateInput()) {
            return;
        }
        
        // Create new employee
        Employee employee = getEmployeeFromForm();
        employee.setId(employees.getNextId());
        
        try {
            // Add to model - only add to employees list to avoid duplication
            employees.addEmployee(employee);
            
            // Save to XML
            XMLUtils.saveEmployees(employees);
            
            // Refresh table model to show the new employee
            tableModel = new EmployeeTableModel(employees.getEmployees());
            tblEmployees.setModel(tableModel);
            setupTable();
            
            // Clear form
            clearForm();
            
            JOptionPane.showMessageDialog(this, "Nhân viên đã được thêm thành công.", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateEmployee() {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần cập nhật.", 
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateInput()) {
            return;
        }
        
        try {
            Employee employee = getEmployeeFromForm();
            Employee selectedEmployee = tableModel.getEmployeeAt(selectedRow);
            employee.setId(selectedEmployee.getId());
            
            // Update employee using the new method
            employees.updateEmployee(employee);
            
            // Refresh table model
            tableModel = new EmployeeTableModel(employees.getEmployees());
            tblEmployees.setModel(tableModel);
            setupTable();
            
            // Save changes
            XMLUtils.saveEmployees(employees);
            
            JOptionPane.showMessageDialog(this, "Nhân viên đã được cập nhật thành công.", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteEmployee() {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa.", 
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn xóa nhân viên này?", 
                "Xác nhận", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            Employee employee = tableModel.getEmployeeAt(selectedRow);
            employees.removeEmployee(employee);
            
            // Refresh table model
            tableModel = new EmployeeTableModel(employees.getEmployees());
            tblEmployees.setModel(tableModel);
            setupTable();
            
            XMLUtils.saveEmployees(employees);
            clearForm();
            
            JOptionPane.showMessageDialog(this, "Nhân viên đã được xóa thành công.", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void searchEmployees() {
        String name = txtSearchName.getText().trim().toLowerCase();
        String salaryFromStr = txtSalaryFrom.getText().trim();
        String salaryToStr = txtSalaryTo.getText().trim();
        
        double salaryFrom = 0;
        double salaryTo = Double.MAX_VALUE;
        
        try {
            if (!salaryFromStr.isEmpty()) {
                salaryFrom = FormatUtils.parseCurrency(salaryFromStr);
            }
            
            if (!salaryToStr.isEmpty()) {
                salaryTo = FormatUtils.parseCurrency(salaryToStr);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mức lương hợp lệ.", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Filter employees
        List<Employee> filteredEmployees = new ArrayList<>();
        
        for (Employee employee : employees.getEmployees()) {
            boolean nameMatch = name.isEmpty() || 
                    employee.getName().toLowerCase().contains(name);
            
            boolean salaryMatch = employee.getSalary() >= salaryFrom && 
                    employee.getSalary() <= salaryTo;
            
            if (nameMatch && salaryMatch) {
                filteredEmployees.add(employee);
            }
        }
        
        // Update table with filtered results
        tableModel = new EmployeeTableModel(filteredEmployees);
        tblEmployees.setModel(tableModel);
        setupTable();
    }
    
    private void clearSearch() {
        txtSearchName.setText("");
        txtSalaryFrom.setText("");
        txtSalaryTo.setText("");
        
        // Reset table to show all employees
        tableModel = new EmployeeTableModel(employees.getEmployees());
        tblEmployees.setModel(tableModel);
        setupTable();
    }
    
    private void displayEmployeeDetails(int rowIndex) {
        Employee employee = tableModel.getEmployeeAt(rowIndex);
        
        txtId.setText(String.valueOf(employee.getId()));
        txtName.setText(employee.getName());
        dateChooser.setDate(employee.getDateOfBirth());
        cboDepartment.setSelectedItem(employee.getDepartment());
        cboPosition.setSelectedItem(employee.getPosition());
        cboEducation.setSelectedItem(employee.getEducation());
        txtSalary.setText(FormatUtils.formatCurrency(employee.getSalary()));
        txtEmail.setText(employee.getEmail());
        txtPhone.setText(employee.getPhone());
        txtAddress.setText(employee.getAddress());
    }
    
    private void clearForm() {
        txtId.setText("");
        txtName.setText("");
        dateChooser.setDate(null);
        cboDepartment.setSelectedIndex(0);
        cboPosition.setSelectedIndex(0);
        cboEducation.setSelectedIndex(0);
        txtSalary.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        selectedRow = -1;
        tblEmployees.clearSelection();
    }
    
    private boolean validateInput() {
        // Check required fields
        if (txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập họ và tên.", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtName.requestFocus();
            return false;
        }
        
        if (dateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh.", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            dateChooser.requestFocus();
            return false;
        }
        
        if (txtSalary.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập lương.", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtSalary.requestFocus();
            return false;
        }
        
        // Validate salary
        try {
            double salary = FormatUtils.parseCurrency(txtSalary.getText().trim());
            if (salary <= 0) {
                JOptionPane.showMessageDialog(this, "Lương phải lớn hơn 0.", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtSalary.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập lương hợp lệ.", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtSalary.requestFocus();
            return false;
        }
        
        // Validate address is required
        String address = txtAddress.getText().trim();
        if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ.", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtAddress.requestFocus();
            return false;
        }
        
        // Validate email is required and formatted correctly
        String emailToValidate = txtEmail.getText().trim();
        if (emailToValidate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập email.", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }
        
        // Validate email format
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!emailToValidate.matches(emailRegex)) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng.", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }
        
        // Validate phone is required and formatted correctly
        String phoneToValidate = txtPhone.getText().trim();
        if (phoneToValidate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại.", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtPhone.requestFocus();
            return false;
        }
        
        // Validate phone format (Vietnamese phone number: starts with 0, has 10 digits)
        String phoneRegex = "^0[0-9]{9}$";
        if (!phoneToValidate.matches(phoneRegex)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng (phải bắt đầu bằng số 0 và có 10 chữ số).", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtPhone.requestFocus();
            return false;
        }
        
        List<Employee> employeeList = employees.getEmployees();
        boolean isUpdateOperation = !txtId.getText().isEmpty();
        int idBeingUpdated = -1;
        if (isUpdateOperation) {
            try {
                idBeingUpdated = Integer.parseInt(txtId.getText());
            } catch (NumberFormatException e) {
                // This case should ideally not happen if txtId is managed correctly (e.g., non-editable or validated)
                JOptionPane.showMessageDialog(this, "Mã nhân viên không hợp lệ cho việc cập nhật.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        for (Employee existingEmp : employeeList) {
            if (isUpdateOperation && existingEmp.getId() == idBeingUpdated) {
                continue; // Skip self-check during an update
            }

            // Check for email duplication
            if (emailToValidate.equalsIgnoreCase(existingEmp.getEmail())) {
                JOptionPane.showMessageDialog(this,
                        "Email đã tồn tại trong hệ thống (" + existingEmp.getName() + "). Vui lòng sử dụng email khác.",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtEmail.requestFocus();
                return false;
            }

            // Check for phone duplication
            if (phoneToValidate.equals(existingEmp.getPhone())) {
                JOptionPane.showMessageDialog(this,
                        "Số điện thoại đã tồn tại trong hệ thống (" + existingEmp.getName() + "). Vui lòng sử dụng số điện thoại khác.",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtPhone.requestFocus();
                return false;
            }
        }
        
        return true;
    }
    
    private Employee getEmployeeFromForm() {
        String name = txtName.getText().trim();
        Date dateOfBirth = dateChooser.getDate();
        String department = cboDepartment.getSelectedItem().toString();
        String position = cboPosition.getSelectedItem().toString();
        String education = cboEducation.getSelectedItem().toString();
        double salary = FormatUtils.parseCurrency(txtSalary.getText().trim());
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String address = txtAddress.getText().trim();
        
        int id = txtId.getText().isEmpty() ? 0 : Integer.parseInt(txtId.getText());
        
        return new Employee(id, name, dateOfBirth, department, position, 
                education, salary, email, phone, address);
    }
} 