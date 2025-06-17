package com.raven.main;

import com.raven.model.Config;
import com.raven.model.Employee;
import com.raven.model.Employees;
import com.raven.table.EmployeeTableModel;
import com.raven.utils.FormatUtils;
import com.raven.utils.XMLUtils;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class EmployeeManagement extends javax.swing.JFrame {

    private Employees employees;
    private Config config;
    private EmployeeTableModel tableModel;
    private int selectedRow = -1;
    
    // UI Components declaration
    private JPanel mainPanel;
    private JPanel formPanel;
    private JPanel searchPanel;
    private JPanel tablePanel;
    private JPanel statisticsPanel;
    
    private JLabel lblTitle;
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
    
    private JLabel lblTotalEmployees;
    private JLabel lblTotalEmployeesValue;
    private JLabel lblHighestSalary;
    private JLabel lblHighestSalaryValue;
    private JLabel lblLowestSalary;
    private JLabel lblLowestSalaryValue;
    
    public EmployeeManagement() {
        initComponents();
        loadData();
        setupUI();
        setupTable();
        setupListeners();
    }
    
    private void initComponents() {
        // Initialize panels
        mainPanel = new JPanel();
        formPanel = new JPanel();
        searchPanel = new JPanel();
        tablePanel = new JPanel();
        statisticsPanel = new JPanel();
        
        // Initialize form components
        lblTitle = new JLabel("QUẢN LÝ NHÂN SỰ");
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
        
        btnAdd = new JButton("Thêm");
        btnUpdate = new JButton("Cập nhật");
        btnDelete = new JButton("Xóa");
        btnClear = new JButton("Làm mới");
        
        // Initialize search components
        lblSearchName = new JLabel("Tìm theo tên:");
        txtSearchName = new JTextField();
        lblSalaryFrom = new JLabel("Lương từ:");
        txtSalaryFrom = new JTextField();
        lblSalaryTo = new JLabel("Lương đến:");
        txtSalaryTo = new JTextField();
        btnSearch = new JButton("Tìm kiếm");
        btnClearSearch = new JButton("Xóa tìm kiếm");
        
        // Initialize table
        tblEmployees = new JTable();
        scrollPane = new JScrollPane(tblEmployees);
        
        // Initialize statistics
        lblTotalEmployees = new JLabel("Tổng số nhân viên:");
        lblTotalEmployeesValue = new JLabel("0");
        lblHighestSalary = new JLabel("Lương cao nhất:");
        lblHighestSalaryValue = new JLabel("0");
        lblLowestSalary = new JLabel("Lương thấp nhất:");
        lblLowestSalaryValue = new JLabel("0");
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý Nhân sự");
        setSize(1200, 800);
        setLocationRelativeTo(null);
    }
    
    private void loadData() {
        employees = XMLUtils.loadEmployees();
        config = XMLUtils.loadConfig();
        
        for (String department : config.getDepartments()) {
            cboDepartment.addItem(department);
        }
        
        for (String position : config.getPositions()) {
            cboPosition.addItem(position);
        }
        
        for (String education : config.getEducations()) {
            cboEducation.addItem(education);
        }
        
        tableModel = new EmployeeTableModel(employees.getEmployees());
        tblEmployees.setModel(tableModel);
        
        updateStatistics();
    }
    
    private void setupUI() {
        // Main layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(new Color(245, 245, 245));
        
        // Title
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(51, 51, 153));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle);
        
        // Main panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Form panel
        formPanel.setLayout(null);
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));
        formPanel.setPreferredSize(new java.awt.Dimension(400, 500));
        
        // Form components positioning
        lblId.setBounds(20, 30, 100, 25);
        txtId.setBounds(150, 30, 200, 25);
        
        lblName.setBounds(20, 65, 100, 25);
        txtName.setBounds(150, 65, 200, 25);
        
        lblDateOfBirth.setBounds(20, 100, 100, 25);
        dateChooser.setBounds(150, 100, 200, 25);
        
        lblDepartment.setBounds(20, 135, 100, 25);
        cboDepartment.setBounds(150, 135, 200, 25);
        
        lblPosition.setBounds(20, 170, 100, 25);
        cboPosition.setBounds(150, 170, 200, 25);
        
        lblEducation.setBounds(20, 205, 120, 25);
        cboEducation.setBounds(150, 205, 200, 25);
        
        lblSalary.setBounds(20, 240, 100, 25);
        txtSalary.setBounds(150, 240, 200, 25);
        
        lblEmail.setBounds(20, 275, 100, 25);
        txtEmail.setBounds(150, 275, 200, 25);
        
        lblPhone.setBounds(20, 310, 100, 25);
        txtPhone.setBounds(150, 310, 200, 25);
        
        lblAddress.setBounds(20, 345, 100, 25);
        txtAddress.setBounds(150, 345, 200, 25);
        
        btnAdd.setBounds(50, 390, 100, 30);
        btnUpdate.setBounds(160, 390, 100, 30);
        btnDelete.setBounds(50, 430, 100, 30);
        btnClear.setBounds(160, 430, 100, 30);
        
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
        formPanel.add(btnAdd);
        formPanel.add(btnUpdate);
        formPanel.add(btnDelete);
        formPanel.add(btnClear);
        
        // Right panel (search, table, statistics)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        
        // Search panel
        searchPanel.setLayout(null);
        searchPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        searchPanel.setPreferredSize(new java.awt.Dimension(700, 120));
        
        lblSearchName.setBounds(20, 30, 100, 25);
        txtSearchName.setBounds(120, 30, 200, 25);
        
        lblSalaryFrom.setBounds(350, 30, 70, 25);
        txtSalaryFrom.setBounds(420, 30, 100, 25);
        
        lblSalaryTo.setBounds(530, 30, 70, 25);
        txtSalaryTo.setBounds(600, 30, 100, 25);
        
        btnSearch.setBounds(250, 70, 100, 30);
        btnClearSearch.setBounds(370, 70, 120, 30);
        
        searchPanel.add(lblSearchName);
        searchPanel.add(txtSearchName);
        searchPanel.add(lblSalaryFrom);
        searchPanel.add(txtSalaryFrom);
        searchPanel.add(lblSalaryTo);
        searchPanel.add(txtSalaryTo);
        searchPanel.add(btnSearch);
        searchPanel.add(btnClearSearch);
        
        // Table panel
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
        tablePanel.add(scrollPane);
        
        // Statistics panel
        statisticsPanel.setLayout(new BoxLayout(statisticsPanel, BoxLayout.X_AXIS));
        statisticsPanel.setBorder(BorderFactory.createTitledBorder("Thống kê"));
        
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));
        totalPanel.add(lblTotalEmployees);
        totalPanel.add(lblTotalEmployeesValue);
        
        JPanel highestPanel = new JPanel();
        highestPanel.setLayout(new BoxLayout(highestPanel, BoxLayout.Y_AXIS));
        highestPanel.add(lblHighestSalary);
        highestPanel.add(lblHighestSalaryValue);
        
        JPanel lowestPanel = new JPanel();
        lowestPanel.setLayout(new BoxLayout(lowestPanel, BoxLayout.Y_AXIS));
        lowestPanel.add(lblLowestSalary);
        lowestPanel.add(lblLowestSalaryValue);
        
        statisticsPanel.add(Box.createHorizontalGlue());
        statisticsPanel.add(totalPanel);
        statisticsPanel.add(Box.createHorizontalGlue());
        statisticsPanel.add(highestPanel);
        statisticsPanel.add(Box.createHorizontalGlue());
        statisticsPanel.add(lowestPanel);
        statisticsPanel.add(Box.createHorizontalGlue());
        
        // Add components to right panel
        rightPanel.add(searchPanel);
        rightPanel.add(tablePanel);
        rightPanel.add(statisticsPanel);
        
        // Set sizes
        searchPanel.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 120));
        statisticsPanel.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 100));
        
        // Add panels to main panel
        mainPanel.add(formPanel);
        mainPanel.add(rightPanel);
        
        // Add main panel to frame
        add(mainPanel);
    }
    
    private void setupTable() {
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
            
            // Update statistics and clear form
            updateStatistics();
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
            updateStatistics();
            
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
            updateStatistics();
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
    
    private void updateStatistics() {
        List<Employee> employeeList = employees.getEmployees();
        
        // Total employees
        lblTotalEmployeesValue.setText(String.valueOf(employeeList.size()));
        
        // Highest and lowest salary
        if (!employeeList.isEmpty()) {
            double highest = Double.MIN_VALUE;
            double lowest = Double.MAX_VALUE;
            
            for (Employee employee : employeeList) {
                double salary = employee.getSalary();
                if (salary > highest) {
                    highest = salary;
                }
                if (salary < lowest) {
                    lowest = salary;
                }
            }
            
            lblHighestSalaryValue.setText(FormatUtils.formatCurrency(highest));
            lblLowestSalaryValue.setText(FormatUtils.formatCurrency(lowest));
        } else {
            lblHighestSalaryValue.setText("0");
            lblLowestSalaryValue.setText("0");
        }
    }
    
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeManagement.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new EmployeeManagement().setVisible(true);
        });
    }
} 