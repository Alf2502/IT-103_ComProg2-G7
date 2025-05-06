/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.motorphcp2.view;

import alfie.motorphcp2.controller.LoginController;
import alfie.motorphcp2.model.User;

import javax.swing.*;
import java.awt.*;

public final class HeaderPanel extends JPanel {
    private final JLabel headerLabel;
    private final JButton loginButton;
    private final MainFrame mainFrame;

    public HeaderPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setBackground(new Color(3, 182, 252));
        setPreferredSize(new Dimension(0, 30));
        setLayout(new BorderLayout(5, 5)); // Proper layout for left/right alignment

        // Left-aligned welcome label
        headerLabel = new JLabel();
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // top, left, bottom, right
        add(headerLabel, BorderLayout.WEST);

        // Right-aligned login/logout button
        loginButton = new JButton("Log In");
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 2));
        rightPanel.setOpaque(false); // transparent to show header background
        rightPanel.add(loginButton);
        add(rightPanel, BorderLayout.EAST);

        loginButton.addActionListener(e -> {
            if (mainFrame.getCurrentUser() == null) {
                LoginController.login(mainFrame);
            } else {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to log out?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    mainFrame.logout();
                }
            }
        });

        updateUser(null); // Initial state
    }

    public void updateUser(User user) {
        if (user == null) {
            headerLabel.setText("Please LOG IN to view details");
            loginButton.setText("Log In");
        } else {
            headerLabel.setText("Welcome, " + user.getUsername() + "!");
            loginButton.setText("Log Out");
        }
    }
}