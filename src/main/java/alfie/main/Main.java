/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.main;

/**
 * Entry point of the MotorPHCP2 application.
 * Initializes the file path manager and launches the GUI.
 * 
 * Part of MotorPH Change Requests
 * Change request form: MPHCR01-Feature 1
 * Purpose: GUI based application
 * 
 */

import javax.swing.*;
import alfie.view.MainMenu;                 // Import the custom GUI class
import alfie.util.FilePathManager;         // Import centralized file path manager

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

