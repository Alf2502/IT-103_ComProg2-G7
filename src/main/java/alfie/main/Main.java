/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.main;

import javax.swing.*;

/**
 *
 * @author Alfie
 */

import alfie.view.MainMenu;                 // Import the custom GUI class
import alfie.util.FilePathManager;         // Import centralized file path manager

/**
 * Entry point of the MotorPHCP2 application.
 * Initializes the file path manager and launches the GUI.
 */
public class Main {
    public static void main(String[] args) {
        
        /*
         * Ensures GUI runs on the Event Dispatch Thread.
         * This prevents threading issues with Swing components.
         */
        SwingUtilities.invokeLater(() -> {

            //  Initialize FilePathManager ONCE before any other file operation occurs
            new FilePathManager("C:/Users/Alfie/Documents/NetBeansProjects/MotorPHCP2");

            //  Create and show the main window
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}

