package com.raven.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginForm extends JFrame {
    
    private JPanel mainPanel;
    private JLabel lblTitle;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    
    public LoginForm() {
        initComponents();
        setupUI();
        setupListeners();
    }
    
    private void initComponents() {
        mainPanel = new JPanel();
        lblTitle = new JLabel("ĐĂNG NHẬP HỆ THỐNG");
        lblUsername = new JLabel("Tên đăng nhập:");
        lblPassword = new JLabel("Mật khẩu:");
        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        btnLogin = new JButton("Đăng nhập");
        btnCancel = new JButton("Hủy");
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng nhập - Quản lý Nhân sự");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    private void setupUI() {
        // Main panel
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(240, 240, 255));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Title
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(51, 51, 153));
        lblTitle.setBounds(50, 20, 300, 30);
        
        // Username
        lblUsername.setBounds(50, 80, 100, 25);
        txtUsername.setBounds(160, 80, 180, 25);
        
        // Password
        lblPassword.setBounds(50, 120, 100, 25);
        txtPassword.setBounds(160, 120, 180, 25);
        
        // Buttons
        btnLogin.setBounds(100, 180, 100, 30);
        btnCancel.setBounds(220, 180, 100, 30);
        
        // Add components to panel
        mainPanel.add(lblTitle);
        mainPanel.add(lblUsername);
        mainPanel.add(txtUsername);
        mainPanel.add(lblPassword);
        mainPanel.add(txtPassword);
        mainPanel.add(btnLogin);
        mainPanel.add(btnCancel);
        
        // Add panel to frame
        setContentPane(mainPanel);
    }
    
    private void setupListeners() {
        btnLogin.addActionListener((ActionEvent e) -> {
            login();
        });
        
        btnCancel.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }
    
    private void login() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                    "Vui lòng nhập tên đăng nhập và mật khẩu.", 
                    "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Default credentials: admin/123456
        if (username.equals("admin") && password.equals("admin")) {
            // Login successful, open main application
            this.dispose();
            DashboardForm dashboard = new DashboardForm();
            dashboard.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, 
                    "Tên đăng nhập hoặc mật khẩu không đúng.", 
                    "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
            txtPassword.requestFocus();
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
            java.util.logging.Logger.getLogger(LoginForm.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
} 