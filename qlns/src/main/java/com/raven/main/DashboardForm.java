package com.raven.main;

import com.raven.model.Config;
import com.raven.model.Employee;
import com.raven.model.Employees;
import com.raven.utils.FormatUtils;
import com.raven.utils.XMLUtils;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DashboardForm extends JFrame {
    
    private Employees employees;
    private Config config;
    
    // UI Components
    private JPanel mainPanel;
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JPanel headerPanel;
    
    private JLabel lblUsername;
    private JLabel lblTitle;
    private JButton btnHome;
    private JButton btnEmployees;
    private JButton btnStatistics;
    private JButton btnLogout;
    
    private CardLayout cardLayout;
    private JPanel homePanel;
    private EmployeeManagementPanel employeePanel;
    private JPanel statisticsPanel;
    
    private JLabel lblTotalEmployees;
    private JLabel lblTotalEmployeesValue;
    private JLabel lblHighestSalary;
    private JLabel lblHighestSalaryValue;
    private JLabel lblLowestSalary;
    private JLabel lblLowestSalaryValue;
    private JLabel lblAverageSalary;
    private JLabel lblAverageSalaryValue;
    
    public DashboardForm() {
        loadData();
        initComponents();
        setupUI();
        setupListeners();
        showHome();
    }
    
    private void loadData() {
        employees = XMLUtils.loadEmployees();
        config = XMLUtils.loadConfig();
    }
    
    private void initComponents() {
        mainPanel = new JPanel();
        sidebarPanel = new JPanel();
        contentPanel = new JPanel();
        headerPanel = new JPanel();
        
        lblUsername = new JLabel("Admin");
        lblTitle = new JLabel("QUẢN LÝ NHÂN SỰ");
        btnHome = new JButton("Trang chủ");
        btnEmployees = new JButton("Quản lý nhân viên");
        btnStatistics = new JButton("Thống kê");
        btnLogout = new JButton("Đăng xuất");
        
        cardLayout = new CardLayout();
        homePanel = new JPanel();
        employeePanel = new EmployeeManagementPanel(employees, config);
        statisticsPanel = new JPanel();
        
        lblTotalEmployees = new JLabel("Tổng số nhân viên:");
        lblTotalEmployeesValue = new JLabel("0");
        lblHighestSalary = new JLabel("Lương cao nhất:");
        lblHighestSalaryValue = new JLabel("0");
        lblLowestSalary = new JLabel("Lương thấp nhất:");
        lblLowestSalaryValue = new JLabel("0");
        lblAverageSalary = new JLabel("Lương trung bình:");
        lblAverageSalaryValue = new JLabel("0");
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard - Quản lý Nhân sự");
        setSize(1200, 800);
        setLocationRelativeTo(null);
    }
    
    private void setupUI() {
        // Main layout
        mainPanel.setLayout(new BorderLayout());
        
        // Header panel
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(51, 102, 255));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 60));
        
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(new EmptyBorder(0, 20, 0, 0));
        
        lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUsername.setBorder(new EmptyBorder(0, 0, 0, 20));
        
        headerPanel.add(lblTitle, BorderLayout.WEST);
        headerPanel.add(lblUsername, BorderLayout.EAST);
        
        // Sidebar panel
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(51, 51, 77));
        sidebarPanel.setPreferredSize(new Dimension(200, getHeight()));
        
        setupSidebarButton(btnHome);
        setupSidebarButton(btnEmployees);
        setupSidebarButton(btnStatistics);
        setupSidebarButton(btnLogout);
        
        // Add some space at the top
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebarPanel.add(btnHome);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(btnEmployees);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(btnStatistics);
        sidebarPanel.add(Box.createVerticalGlue());
        sidebarPanel.add(btnLogout);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Content panel with card layout
        contentPanel.setLayout(cardLayout);
        contentPanel.setBackground(Color.WHITE);
        
        // Setup home panel
        setupHomePanel();
        
        // Setup statistics panel
        setupStatisticsPanel();
        
        // Add panels to card layout
        contentPanel.add(homePanel, "home");
        contentPanel.add(employeePanel, "employees");
        contentPanel.add(statisticsPanel, "statistics");
        
        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Set main panel as content pane
        setContentPane(mainPanel);
    }
    
    private void setupSidebarButton(JButton button) {
        button.setMaximumSize(new Dimension(180, 40));
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 51, 77));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(true);
    }
    
    private void setupHomePanel() {
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        homePanel.setBackground(Color.WHITE);
        
        JLabel welcomeLabel = new JLabel("Chào mừng đến với Hệ thống Quản lý Nhân sự");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
        welcomeLabel.setBorder(new EmptyBorder(50, 0, 30, 0));
        
        JLabel infoLabel = new JLabel("Vui lòng chọn chức năng từ menu bên trái");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoLabel.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel userLabel = new JLabel("Đăng nhập với tài khoản: Admin");
        userLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        userLabel.setAlignmentX(CENTER_ALIGNMENT);
        userLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        // Add components to home panel
        homePanel.add(Box.createVerticalGlue());
        homePanel.add(welcomeLabel);
        homePanel.add(infoLabel);
        homePanel.add(userLabel);
        homePanel.add(Box.createVerticalGlue());
    }
    
    private void setupStatisticsPanel() {
        statisticsPanel.setLayout(new BoxLayout(statisticsPanel, BoxLayout.Y_AXIS));
        statisticsPanel.setBackground(Color.WHITE);
        
        JLabel statsTitle = new JLabel("Thống kê nhân sự");
        statsTitle.setFont(new Font("Arial", Font.BOLD, 24));
        statsTitle.setAlignmentX(CENTER_ALIGNMENT);
        statsTitle.setBorder(new EmptyBorder(30, 0, 30, 0));
        
        JPanel statsContent = new JPanel();
        statsContent.setLayout(new BoxLayout(statsContent, BoxLayout.Y_AXIS));
        statsContent.setBackground(Color.WHITE);
        statsContent.setAlignmentX(CENTER_ALIGNMENT);
        statsContent.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(20, 20, 20, 20)));
        statsContent.setMaximumSize(new Dimension(600, 300));
        
        // Style the labels
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font valueFont = new Font("Arial", Font.PLAIN, 16);
        
        lblTotalEmployees.setFont(labelFont);
        lblTotalEmployeesValue.setFont(valueFont);
        lblHighestSalary.setFont(labelFont);
        lblHighestSalaryValue.setFont(valueFont);
        lblLowestSalary.setFont(labelFont);
        lblLowestSalaryValue.setFont(valueFont);
        lblAverageSalary.setFont(labelFont);
        lblAverageSalaryValue.setFont(valueFont);
        
        // Create panel for each statistic
        JPanel totalPanel = createStatPanel(lblTotalEmployees, lblTotalEmployeesValue);
        JPanel highestPanel = createStatPanel(lblHighestSalary, lblHighestSalaryValue);
        JPanel lowestPanel = createStatPanel(lblLowestSalary, lblLowestSalaryValue);
        JPanel avgPanel = createStatPanel(lblAverageSalary, lblAverageSalaryValue);
        
        // Add stat panels to content
        statsContent.add(totalPanel);
        statsContent.add(Box.createRigidArea(new Dimension(0, 15)));
        statsContent.add(highestPanel);
        statsContent.add(Box.createRigidArea(new Dimension(0, 15)));
        statsContent.add(lowestPanel);
        statsContent.add(Box.createRigidArea(new Dimension(0, 15)));
        statsContent.add(avgPanel);
        
        // Add components to statistics panel
        statisticsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        statisticsPanel.add(statsTitle);
        statisticsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        statisticsPanel.add(statsContent);
        statisticsPanel.add(Box.createVerticalGlue());
    }
    
    private JPanel createStatPanel(JLabel label, JLabel value) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(CENTER_ALIGNMENT);
        
        label.setPreferredSize(new Dimension(200, 25));
        value.setPreferredSize(new Dimension(200, 25));
        
        panel.add(label);
        panel.add(Box.createHorizontalGlue());
        panel.add(value);
        
        return panel;
    }
    
    private void setupListeners() {
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHome();
            }
        });
        
        btnEmployees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEmployees();
            }
        });
        
        btnStatistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStatistics();
            }
        });
        
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
    }
    
    private void showHome() {
        cardLayout.show(contentPanel, "home");
        resetButtonColors();
        btnHome.setBackground(new Color(0, 123, 255));
    }
    
    private void showEmployees() {
        cardLayout.show(contentPanel, "employees");
        resetButtonColors();
        btnEmployees.setBackground(new Color(0, 123, 255));
    }
    
    private void showStatistics() {
        updateStatistics();
        cardLayout.show(contentPanel, "statistics");
        resetButtonColors();
        btnStatistics.setBackground(new Color(0, 123, 255));
    }
    
    private void resetButtonColors() {
        btnHome.setBackground(new Color(51, 51, 77));
        btnEmployees.setBackground(new Color(51, 51, 77));
        btnStatistics.setBackground(new Color(51, 51, 77));
    }
    
    private void updateStatistics() {
        List<Employee> employeeList = employees.getEmployees();
        
        // Total employees
        int total = employeeList.size();
        lblTotalEmployeesValue.setText(String.valueOf(total));
        
        if (!employeeList.isEmpty()) {
            double highest = Double.MIN_VALUE;
            double lowest = Double.MAX_VALUE;
            double sum = 0;
            
            for (Employee employee : employeeList) {
                double salary = employee.getSalary();
                sum += salary;
                
                if (salary > highest) {
                    highest = salary;
                }
                
                if (salary < lowest) {
                    lowest = salary;
                }
            }
            
            double average = sum / total;
            
            lblHighestSalaryValue.setText(FormatUtils.formatCurrency(highest));
            lblLowestSalaryValue.setText(FormatUtils.formatCurrency(lowest));
            lblAverageSalaryValue.setText(FormatUtils.formatCurrency(average));
        } else {
            lblHighestSalaryValue.setText("0");
            lblLowestSalaryValue.setText("0");
            lblAverageSalaryValue.setText("0");
        }
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn đăng xuất?", 
                "Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            new LoginForm().setVisible(true);
        }
    }
} 