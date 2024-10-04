package com.gui;

import javax.swing.*;
import java.awt.*;

public class GradeChooserGUI {
    public static void main(String[] args) {
        // Create the main frame for the application
        JFrame frame = createMainFrame("Art Dealer Game", 400, 400);

        // Create a panel to hold components
        JPanel panel = createMainPanel();

        // Add labels to the panel
        JLabel choosegradetxt = createLabel("Choose which grade you are in!", Color.white);
        JLabel createdbytxt = createLabel("Created by Prithu Kathet and Travis Lester", Color.white);

        // Create buttons for each grade level
        JButton buttonK2 = createGradeButton("K-2");
        JButton button35 = createGradeButton("3-5");
        JButton button68 = createGradeButton("6-8");

        // Add action listeners to buttons to handle grade selection
        buttonK2.addActionListener(e -> openGradeWindow("K-2"));
        button35.addActionListener(e -> openGradeWindow("3-5"));
        button68.addActionListener(e -> openGradeWindow("6-8"));

        // Add components to the panel with spacing
        addComponentsToPanel(panel, choosegradetxt, buttonK2, button35, button68, createdbytxt);

        // Add the panel to the frame
        frame.add(panel);

        ImageIcon image = new ImageIcon("/com/resources/artdealer.jpg");
        frame.setIconImage(image.getImage());

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to create the main frame
    private static JFrame createMainFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes completed when X is pressed
        frame.setSize(width, height); // sets default width and height
        frame.setResizable(false); // Do not allow people resize this screen
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Add a neon green border to the frame
        ((JComponent) frame.getRootPane()).setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));

        return frame;
    }

    // Method to create the main panel
    private static JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack components vertically
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the panel
        panel.setBackground(Color.BLACK); // Set background to black
        return panel;
    }

    // Method to create a JLabel with specified text and foreground color
    private static JLabel createLabel(String text, Color foregroundColor) {
        JLabel label = new JLabel(text);
        label.setForeground(foregroundColor);
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Center label
        return label;
    }

    // Method to create a grade button with specified text
    private static JButton createGradeButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 70));
        button.setMaximumSize(button.getPreferredSize());
        button.setBackground(Color.black);
        button.setForeground(Color.GREEN);
        button.setFont(new Font("Comic Sans", Font.BOLD, 22));
        button.setFocusable(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center button
        button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Add neon green border
        return button;
    }

    // Method to add components to the panel with appropriate spacing
    private static void addComponentsToPanel(JPanel panel, JLabel choosegradetxt, JButton buttonK2,
            JButton button35, JButton button68, JLabel createdbytxt) {
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space at the top
        panel.add(choosegradetxt);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between the label and buttons

        // Adding horizontal glue for centering buttons
        panel.add(Box.createHorizontalGlue());
        panel.add(buttonK2);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons
        panel.add(button35);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons
        panel.add(button68);
        panel.add(Box.createHorizontalGlue()); // Adding glue after buttons

        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space at the bottom
        panel.add(createdbytxt);
    }

    // Method to open a new window for each grade
    private static void openGradeWindow(String grade) {
        switch (grade) {
            case "K-2":

                new K2GradeWindow();
                break;
            case "3-5":
                new Grade35Window();
                break;
            case "6-8":
                new Grade68Window();
                break;
            default:
                break;
        }
    }
}
