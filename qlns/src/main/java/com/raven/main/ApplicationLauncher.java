package com.raven.main;

public class ApplicationLauncher {
    
    public static void main(String[] args) {
        // Launch the Login form
        java.awt.EventQueue.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
} 