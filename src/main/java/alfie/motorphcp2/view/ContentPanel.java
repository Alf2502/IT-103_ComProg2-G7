/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.motorphcp2.view;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {
    public ContentPanel() {
        setBackground(new Color(3, 252, 161));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton btn = new JButton("Don't Click");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "G@GO, sabing wag mong i-Click!",
                "Pinindot talaga ni loko",
                JOptionPane.INFORMATION_MESSAGE));

        add(Box.createVerticalStrut(100));
        add(btn);
    }
}
