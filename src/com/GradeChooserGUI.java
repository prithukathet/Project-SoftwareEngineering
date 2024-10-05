package com;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GradeChooserGUI {

    // Store the Clip instance so we can stop it later -- must be global
    private static Clip clip;

    public static void main(String[] args) {
        // Play background music when the application opens
        playMusic("src/com/resources/background.wav");

        JFrame frame = createMainFrame("Art Dealer Game", 400, 400); // Create the main frame for the application
        JPanel panel = createMainPanel(); // Create a panel to hold components

        // Add labels to the panel -- imports later on
        JLabel choosegradetxt = createLabel("Choose which grade you are in!", Color.white);
        JLabel createdbytxt = createLabel("Created by Prithu Kathet and Travis Lester", Color.white);

        // Create buttons for each grade level -- imports later on
        JButton buttonK2 = createGradeButton("K-2");
        JButton button35 = createGradeButton("3-5");
        JButton button68 = createGradeButton("6-8");

        // Add action listeners to buttons to handle grade selection
        buttonK2.addActionListener(e -> {
            openGradeWindow("K-2"); // when K-2 button is clicked
            stopMusic(); // Stop the music when K-2 button is clicked and new window populates
            frame.dispose(); // Close the main window
        });
        button35.addActionListener(e -> { // creates the listener when button is clicked
            openGradeWindow("3-5"); // when 3-5 button is clicked
            stopMusic(); // Stop the music when 3-5 button is clicked and new window populates
            frame.dispose(); // Close the main window
        });
        button68.addActionListener(e -> { // creates the listener when button is clicked
            openGradeWindow("6-8"); // when 6-8 button is clicked
            stopMusic(); // Stop the music when 6-8 is clicked and new window populates
            frame.dispose(); // Closes the main window
        });

        // Add components to the panel with spacing
        addComponentsToPanel(panel, choosegradetxt, buttonK2, button35, button68, createdbytxt);

        // Adds the panel to the frame
        frame.add(panel);

        // Changes Icon of the window
        ImageIcon image = new ImageIcon("src/com/resources/artdealer.jpg");
        frame.setIconImage(image.getImage());

        // Makes the frame visible
        frame.setVisible(true);
    }

    // Creates the main frame
    private static JFrame createMainFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes completed when X is pressed
        frame.setSize(width, height); // sets default width and height
        frame.setResizable(false); // Do not allow people resize this screen
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        ((JComponent) frame.getRootPane()).setBorder(BorderFactory.createLineBorder(Color.GREEN, 5)); // Add a neon
                                                                                                      // green border to
                                                                                                      // the frame

        return frame;
    }

    // Create the main panel
    private static JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack components vertically
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Space around the panel
        panel.setBackground(Color.BLACK); // Set background to black
        return panel;
    }

    // Creates a JLabel with specified text and foreground color
    private static JLabel createLabel(String text, Color foregroundColor) {
        JLabel label = new JLabel(text); // creates a text
        label.setForeground(foregroundColor); // sets text color
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Center label
        return label;
    }

    // Create a grade button with specified text
    private static JButton createGradeButton(String text) {
        JButton button = new JButton(text); // creates a new button
        button.setPreferredSize(new Dimension(150, 70)); // sets the window size
        button.setMaximumSize(button.getPreferredSize()); // allows the button to change size upon screen resize,
                                                          // however, screen resize is disabled
        button.setBackground(Color.black); // sets background to black
        button.setForeground(Color.GREEN); // sets color of text to green
        button.setFont(new Font("Comic Sans", Font.BOLD, 22)); // Sets font, size, and boldness
        button.setFocusable(false); // Removes the box around the text upon clicking the button
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center button
        button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Add neon green border
        return button;
    }

    // Add components to the panel with appropriate spacing
    private static void addComponentsToPanel(JPanel panel, JLabel choosegradetxt, JButton buttonK2, JButton button35,
            JButton button68, JLabel createdbytxt) {
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space at the top
        panel.add(choosegradetxt); // Imports the choose grade text we added above
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between the label and buttons
        panel.add(Box.createHorizontalGlue()); // Adding horizontal glue for centering buttons
        panel.add(buttonK2);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons
        panel.add(button35);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons
        panel.add(button68);
        panel.add(Box.createHorizontalGlue()); // Adding glue after buttons
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space at the bottom
        panel.add(createdbytxt);
    }

    // Play music using javax.sound.sampled
    private static void playMusic(String filepath) {
        try {
            // Open an audio input stream from the provided file path
            File soundFile = new File(filepath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Get the audio clip and open it with the stream
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Start playing the clip
            clip.start();

            // Optional: loop the clip continuously
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Comment this line out if you don't want looping

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Stop the music upon opening another window
    private static void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Stop the music
            clip.close(); // Release system resources to free up space
        }
    }

    // Open a new window for each grade
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
