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

import alfie.model.Employee;
import alfie.util.AttendanceFileHandler;
import alfie.util.EmployeeFileHandler;
import alfie.util.SalaryCalculator;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainMenu extends JFrame {

    private EmployeeListView employeeListView;
    private boolean isLoggedIn = false;
    private JPanel mainPanel;

    private final JButton loginButton;
    private final JButton employeeBtn;
    private final JButton addAdminBtn;
    private final JButton exitBtn;
    private final JTextField searchField;
    private final JButton searchButton;
    private final JButton computeButton;
    private final JComboBox<String> monthCombo;
    private final JComboBox<String> yearCombo;

    private final DefaultListModel<String> listModel;
    private final DefaultListModel<String> resultModel;
    private final JList<String> searchResultList;
    private final JList<String> resultList;

    private List<Employee> allEmployees;
    private final AttendanceFileHandler attendanceHandler;
    private final SalaryCalculator salaryCalculator;

    public MainMenu() {
        showLoginDialog();
        if (!isLoggedIn) {
            System.exit(0);
        }

        setTitle("MotorPH Payroll System - Main Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        EmployeeFileHandler handler = new EmployeeFileHandler();
        attendanceHandler = new AttendanceFileHandler();
        salaryCalculator = new SalaryCalculator(attendanceHandler);
        allEmployees = handler.readEmployees();

        searchField = new JTextField();
        searchButton = new JButton("Search");

        listModel = new DefaultListModel<>();
        searchResultList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(searchResultList);

        computeButton = new JButton("Compute Salary");
        computeButton.setVisible(true);
        computeButton.setEnabled(false);

        resultModel = new DefaultListModel<>();
        resultList = new JList<>(resultModel);
        resultList.setBorder(BorderFactory.createTitledBorder("Salary Computation"));

        monthCombo = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"});
        yearCombo = new JComboBox<>(new String[]{"2023", "2024", "2025"});

        searchButton.addActionListener(e -> performSearch());
        searchResultList.addListSelectionListener(e -> computeButton.setEnabled(!searchResultList.isSelectionEmpty()));

        computeButton.addActionListener(e -> {
            resultModel.clear(); // Clear previous result
            String selectedValue = searchResultList.getSelectedValue();
            if (selectedValue != null && !selectedValue.contains("No results")) {
                String empNum = selectedValue.split(" - ")[0];
                String month = (String) monthCombo.getSelectedItem();
                String monthTwoDigit = String.format("%02d", monthCombo.getSelectedIndex() + 1);
                String year = (String) yearCombo.getSelectedItem();

                Employee emp = allEmployees.stream()
                    .filter(e1 -> e1.getEmployeeNumber().equals(empNum))
                    .findFirst()
                    .orElse(null);

                if (emp != null) {
                    double totalHours = salaryCalculator.calculateMonthlyHours(empNum, year, monthTwoDigit);
                    double salary = salaryCalculator.calculateSalary(emp, totalHours);
                    double fixedComp = (salary > 0) ? salaryCalculator.calculateTotalWithAllowances(emp) : 0.0;
                    double totalPay = salary + fixedComp;

                    resultModel.addElement("Employee #" + empNum);
                    resultModel.addElement("Month: " + month + " " + year);
                    resultModel.addElement("Total Hours: " + String.format("%.2f", totalHours));
                    resultModel.addElement("Hourly Salary: ₱" + String.format("%.2f", salary));
                    resultModel.addElement("Fixed Allowances: ₱" + String.format("%.2f", fixedComp));
                    resultModel.addElement("TOTAL: ₱" + String.format("%.2f", totalPay));
                    resultModel.addElement("------------------------------");
                }
            }
        });

        JPanel topSearchPanel = new JPanel(new BorderLayout(5, 5));
        topSearchPanel.add(searchField, BorderLayout.CENTER);
        topSearchPanel.add(searchButton, BorderLayout.EAST);

        JPanel centerSearchPanel = new JPanel(new BorderLayout(5, 5));
        centerSearchPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Employee"));
        searchPanel.add(topSearchPanel, BorderLayout.NORTH);
        searchPanel.add(centerSearchPanel, BorderLayout.CENTER);

        employeeBtn = new JButton("Employee Records");
        addAdminBtn = new JButton("   "); //add admin
        loginButton = new JButton("Logout");
        exitBtn = new JButton("Exit");

        JPanel topRightPanel = new JPanel();
        topRightPanel.setLayout(new BoxLayout(topRightPanel, BoxLayout.Y_AXIS));
        for (JComponent comp : new JComponent[]{employeeBtn, computeButton, monthCombo, yearCombo}) {
            comp.setMaximumSize(new Dimension(200, 30));
            comp.setAlignmentX(Component.CENTER_ALIGNMENT);
            topRightPanel.add(Box.createVerticalStrut(10));
            topRightPanel.add(comp);
        }
        
        JPanel centerRightPanel = new JPanel();
        centerRightPanel.setLayout(new BoxLayout(centerRightPanel, BoxLayout.Y_AXIS));
        for (JList list : new JList[]{resultList}) {
            list.setMaximumSize(new Dimension(200, 180));
            list.setAlignmentX(TOP_ALIGNMENT);
            centerRightPanel.add(Box.createVerticalStrut(10));
            centerRightPanel.add(list);
        }
        //centerRightPanel.add(new JScrollPane(resultList));

        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setLayout(new BoxLayout(bottomRightPanel, BoxLayout.Y_AXIS));
        for (JButton btn : new JButton[]{addAdminBtn, loginButton, exitBtn}) {
            btn.setMaximumSize(new Dimension(200, 30));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            bottomRightPanel.add(Box.createVerticalStrut(10));
            bottomRightPanel.add(btn);
        }

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(topRightPanel, BorderLayout.NORTH);
        rightPanel.add(centerRightPanel, BorderLayout.CENTER);
        rightPanel.add(bottomRightPanel, BorderLayout.SOUTH);

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(searchPanel, BorderLayout.CENTER);
        setContentPane(mainPanel);

        employeeBtn.addActionListener(e -> {
            if (employeeListView == null || !employeeListView.isDisplayable()) {
                employeeListView = new EmployeeListView(this, isLoggedIn);
            }
            employeeListView.setVisible(true);
        });

        addAdminBtn.setEnabled(isLoggedIn);
        addAdminBtn.addActionListener(e -> new NewAdminForm(this).setVisible(true));
        loginButton.addActionListener(e -> handleLogout());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void performSearch() {
        String query = searchField.getText().trim().toLowerCase();
        listModel.clear();
        if (query.isEmpty()) return;
        allEmployees.stream()
                .filter(e -> e.getEmployeeNumber().toLowerCase().contains(query))
                .forEach(e -> listModel.addElement(e.getEmployeeNumber() + " - " + e.getFirstName() + " " + e.getLastName()));
        if (listModel.isEmpty()) {
            listModel.addElement("No results found.");
        }
    }

    private void showLoginDialog() {
        JDialog loginDialog = new JDialog(this, "Login", true);
        loginDialog.setSize(300, 200);
        loginDialog.setLocationRelativeTo(null);
        LoginPanel loginPanel = new LoginPanel(() -> {
            isLoggedIn = true;
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
            addAdminBtn.setEnabled(false);
            if (employeeListView != null && employeeListView.isDisplayable()) {
                employeeListView.setProtectedButtonsEnabled(false);
            }
            showLoginDialog();
            if (!isLoggedIn) {
                dispose();
                System.exit(0);
            } else {
                loginButton.setText("Logout");
                enableProtectedFeatures(true);
            }
        }
    }

    private void enableProtectedFeatures(boolean enable) {
        addAdminBtn.setEnabled(enable);
        if (employeeListView != null && employeeListView.isDisplayable()) {
            employeeListView.setProtectedButtonsEnabled(enable);
        }
    }
}
