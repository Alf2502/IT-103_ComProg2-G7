/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Alfie
 */

package alfie.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;



public class MainMenu extends JFrame {
    
    public MainMenu() {
        
    // MainMenu Design
        setTitle("MotorPH Payroll System - Main Menu");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //Center window
        setLayout(new GridLayout(4, 1, 10, 10));
    
    // Exit Button
        JButton exitBtn = new JButton("Exit");
        add(exitBtn);
        exitBtn.addActionListener(e -> System.exit(0));
        
    // Employee details button
        JButton employeeBtn = new JButton("Employee Records");
        employeeBtn.addActionListener(e -> {
            EmployeeListView dialog = new EmployeeListView(this); // Pass MainMenu as parent
            dialog.setVisible(true); // Modal: blocks MainMenu until closed
        });
        add(employeeBtn);
        
    }
}