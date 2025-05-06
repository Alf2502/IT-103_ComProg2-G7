/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.motorphcp2.view;

import javax.swing.*;
import java.awt.*;

public class FooterPanel extends JPanel {
    public FooterPanel() {
        setBackground(new Color(3, 182, 252));
        setPreferredSize(new Dimension(0, 30));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        add(new JLabel("Footer Panel"));
    }
}
