package alfie.motorphcp2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MotorPHCP2 extends JFrame {

    public MotorPHCP2() {
        super("MotorPH Official Application");

        // Frame setup
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Use BorderLayout for top-level frame

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(3, 182, 252));
        headerPanel.setPreferredSize(new Dimension(0, 30)); // height only matters in BorderLayout.NORTH
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 7));
        headerPanel.add(new JLabel("Header Panel"));

        // Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(3, 252, 161));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JButton btn = new JButton("Don't Click");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this, "G@GO, sabing wag mong i-Click!", "Pinindot talaga ni loko", JOptionPane.INFORMATION_MESSAGE);
        });
        contentPanel.add(Box.createVerticalStrut(100)); // spacing from top
        contentPanel.add(btn);

        // Footer panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(3, 182, 252));
        footerPanel.setPreferredSize(new Dimension(0, 30));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        footerPanel.add(new JLabel("Footer Panel"));

        // Add panels to frame
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MotorPHCP2::new);
    }
}
