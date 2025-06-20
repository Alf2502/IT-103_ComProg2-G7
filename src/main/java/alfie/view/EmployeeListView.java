/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.view;

/**
 * 
 * Part of MotorPH Change Requests
 * Change request form: MPHCR02-Feature 2
 * Purpose:
 *  1.  Interface to view all list of employee using JFrame on top of JDialog.
 *  2.  Added button to view the detailed information
 *          of employee via EmployeeDetailView class using JDialog.
 *  3.  Added button to add new employee via NewEmployeeForm class using JDialog.
 *  4.  Added search bar for quick search to specific employee.
 *  5.  Added refresh button to provide the updated data every time.
 * 
 */

import alfie.model.Employee;
import alfie.util.EmployeeFileHandler;
import alfie.util.AttendanceFileHandler;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class EmployeeListView extends JDialog {

    private final JTable employeeTable;
    private final DefaultTableModel tableModel;
    private final TableRowSorter<DefaultTableModel> sorter;
    private final JTextField searchField;

    public EmployeeListView(JFrame parent) {
        super(parent, "Employee List", true);
        setSize(860, 660);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Table headers
        String[] columnNames = {
            "Employee Number",
            "Last Name",
            "First Name",
            "SSS Number",
            "PhilHealth Number",
            "TIN",
            "Pag-IBIG Number"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        employeeTable = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        employeeTable.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);
        
        // View Details Button
        JButton viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setEnabled(false);
        viewDetailsButton.addActionListener(e -> showEmployeeDetails());

        JButton newEmployeeButton = new JButton("Add new employee");
        newEmployeeButton.addActionListener(e -> {
            NewEmployeeForm form = new NewEmployeeForm(
            (JFrame) SwingUtilities.getWindowAncestor(this),
            new EmployeeFileHandler(),
                    this::loadEmployeeData
            );
            form.setVisible(true);
        });
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(viewDetailsButton);
        bottomPanel.add(newEmployeeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Enable button when row is selected
        employeeTable.getSelectionModel().addListSelectionListener(e -> {
            viewDetailsButton.setEnabled(employeeTable.getSelectedRow() != -1);
        });

        // Top: Search & Refresh
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        searchField = new JTextField();
        JButton refreshButton = new JButton("Refresh");

        topPanel.add(new JLabel("Search: "), BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(refreshButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Load Data
        loadEmployeeData();

        // Search filter logic
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { search(); }
            @Override
            public void removeUpdate(DocumentEvent e) { search(); }
            @Override
            public void changedUpdate(DocumentEvent e) { search(); }

            private void search() {
                String text = searchField.getText();
                sorter.setRowFilter(text.trim().isEmpty() ? null : RowFilter.regexFilter("(?i)" + text));
            }
        });

        // Refresh reloads employee table
        refreshButton.addActionListener(e -> loadEmployeeData());
    }

    private void showEmployeeDetails() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) return;

        int modelRow = employeeTable.convertRowIndexToModel(selectedRow);
        String empNumber = (String) tableModel.getValueAt(modelRow, 0);

        EmployeeFileHandler fileHandler = new EmployeeFileHandler();
        List<Employee> employees = fileHandler.readEmployees();

        Employee selectedEmp = null;
        for (Employee emp : employees) {
            if (emp.getEmployeeNumber().equals(empNumber)) {
                selectedEmp = emp;
                break;
            }
        }

        if (selectedEmp != null) {
            AttendanceFileHandler handler = new AttendanceFileHandler();

            EmployeeDetailView dialog = new EmployeeDetailView(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                selectedEmp,
                handler
            );
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Employee details not found.");
        }
    }


    private void loadEmployeeData() {
        tableModel.setRowCount(0);
        EmployeeFileHandler fileHandler = new EmployeeFileHandler();
        List<Employee> employees = fileHandler.readEmployees();

        for (Employee emp : employees) {
            Object[] row = {
                emp.getEmployeeNumber(),
                emp.getLastName(),
                emp.getFirstName(),
                emp.getSssNumber(),
                emp.getPhilHealthNumber(),
                emp.getTin(),
                emp.getPagIbigNumber()
            };
            tableModel.addRow(row);
        }
    }
}
