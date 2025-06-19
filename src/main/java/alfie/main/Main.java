/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.main;

import javax.swing.*;
import alfie.view.MainMenu; //Import custom MainMenu class located in the alfie.view package.

/**
 *
 * @author Alfie
 */
public class Main {
    public static void main(String[] args) {
        
    /*
     * Using lambda epression for shorter runnable contains code to:
     * 1. Create new instance to mainmenu
     * 2. Make the window visible using .setVisible(true);
     */
       SwingUtilities.invokeLater(() -> {       // Ensures GUI runs safely on the Event Dispatch Thread
           MainMenu mainMenu = new MainMenu();  // Initializes the main window
           mainMenu.setVisible(true);
       });
    } 
}
