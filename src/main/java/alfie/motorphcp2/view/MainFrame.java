/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.motorphcp2.view;

import alfie.motorphcp2.model.User;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private User currentUser;
    private final HeaderPanel headerPanel;
    private final ContentPanel contentPanel;
    private final FooterPanel footerPanel;

    public MainFrame() {
        setTitle("MotorPH Official Application");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        headerPanel = new HeaderPanel(this);
        contentPanel = new ContentPanel();
        footerPanel = new FooterPanel();

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void login(User user) {
        this.currentUser = user;
        headerPanel.updateUser(user);
    }

    public void logout() {
        this.currentUser = null;
        headerPanel.updateUser(null);
    }

    public User getCurrentUser() {
        return currentUser;
    }
}

