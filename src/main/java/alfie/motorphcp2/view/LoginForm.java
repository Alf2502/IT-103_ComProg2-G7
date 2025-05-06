/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.motorphcp2.view;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JDialog {
    public LoginForm(MainFrame mainFrame) {
        super(mainFrame, "Login", true);
        setSize(300, 150);
        setLocationRelativeTo(mainFrame);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        panel.add(new JLabel("Username:"));
        panel.add(userField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            if (!username.isEmpty()) {
                mainFrame.login(new alfie.motorphcp2.model.User(username));
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        panel.add(loginBtn);
        add(panel);
        setVisible(true);
    }
}

