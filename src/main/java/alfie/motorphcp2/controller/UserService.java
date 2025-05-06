/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.motorphcp2.controller;

import alfie.motorphcp2.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final String USER_CSV_PATH = 
        "C:\\Users\\Alfie\\Documents\\NetBeansProjects\\MotorPHCP2\\src\\main\\java\\alfie\\motorphcp2\\User.csv";

    public static List<User> loadUsersFromCSV() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_CSV_PATH))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; } // skip header
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    users.add(new User(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading User.csv: " + e.getMessage());
        }
        return users;
    }

    public static User authenticate(String username, String password) {
        for (User user : loadUsersFromCSV()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
