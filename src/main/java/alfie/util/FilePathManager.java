/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.util;

/**
 *
 * @author Alfie
 */

import java.io.File;

/**
 * Handles centralized file path management for the MotorPHCP2 application.
 *
 * Example usage:
 * FilePathManager manager = new FilePathManager("C:/Users/Alfie/Documents/NetBeansProjects/MotorPHCP2");
 * String attendancePath = manager.getAttendanceFilePath();
 */
public class FilePathManager {

    private static FilePathManager instance;
    private final String baseDirectory;

    public FilePathManager(String baseDirectory) {
        this.baseDirectory = baseDirectory;
        instance = this; // Store singleton instance
    }

    public static FilePathManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("FilePathManager has not been initialized yet.");
        }
        return instance;
    }

    public String getAttendanceFilePath() {
        return baseDirectory + File.separator + "MotorPH Attendance Record.csv";
    }

    public String getEmployeeFilePath() {
        return baseDirectory + File.separator + "MotorPH Employee Details.csv";
    }

    public boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    public String chooseFile(java.awt.Component parent) {
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        int result = chooser.showOpenDialog(parent);
        if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
} 

