/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package alfie.view;

/**
 * 
 * Part of MotorPH Change Requests
 * Change request form: MPHCR01-Feature 1
 * Purpose: Main GUI (Home screen of the application)
 * 
 */

import javax.swing.*;

import java.awt.*;

public class MainMenu extends JFrame {

    private EmployeeListView employeeListView;
    private boolean isLoggedIn = false;

    private final JButton loginButton;
    private final JButton employeeBtn;
    private final JButton addAdminBtn;
    private final JButton settingsBtn;

    public MainMenu() {
        
        setTitle("MotorPH Payroll System - Main Menu");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        // Exit button
        JButton exitBtn = new JButton("Exit");
        exitBtn.addActionListener(e -> System.exit(0));

        // Employee Records button
        employeeBtn = new JButton("Employee Records");
        employeeBtn.addActionListener(e -> {
            if (employeeListView == null || !employeeListView.isDisplayable()) {
                employeeListView = new EmployeeListView(this, isLoggedIn); // pass login state
            }
            employeeListView.setVisible(true);
        });

        // Add Admin button
        addAdminBtn = new JButton("Add New Admin");
        addAdminBtn.setEnabled(false);
        addAdminBtn.addActionListener(e -> new NewAdminForm(this).setVisible(true));

        // Settings button
        settingsBtn = new JButton("Settings");
        settingsBtn.setEnabled(false);
        settingsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Settings is a work in progress"));

        // Login/Logout button
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            if (!isLoggedIn) {
                showLoginDialog();
            } else {
                handleLogout();
            }
        });

        // Add buttons to layout
        add(employeeBtn);
        add(addAdminBtn);
        add(loginButton);
        add(settingsBtn);
        add(exitBtn);
    }

    private void showLoginDialog() {
        JDialog loginDialog = new JDialog(this, "Login", true);
        loginDialog.setSize(300, 200);
        loginDialog.setLocationRelativeTo(this);

        LoginPanel loginPanel = new LoginPanel(() -> {
            isLoggedIn = true;
            loginButton.setText("Logout");
            enableProtectedFeatures(true);
            loginDialog.dispose();
        });

        loginDialog.add(loginPanel);
        loginDialog.setVisible(true);
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            isLoggedIn = false;
            loginButton.setText("Login");
            enableProtectedFeatures(false);
        }
    }

    private void enableProtectedFeatures(boolean enable) {
        addAdminBtn.setEnabled(enable);
        settingsBtn.setEnabled(enable);
        if (employeeListView != null && employeeListView.isDisplayable()) {
            employeeListView.setProtectedButtonsEnabled(enable);
        }
    }
}
