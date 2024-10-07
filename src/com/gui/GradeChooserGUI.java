package com.gui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import com.artdealergame.GameType;

public class GradeChooserGUI {

    // Store the Clip instance so we can stop it later -- must be global
    private static Clip welcomeClip;
    private static Clip backgroundClip;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = createMainFrame("Art Dealer Game", 400, 400); // Create the main frame for the application
            JPanel panel = createMainPanel(); // Create a panel to hold components

            // Add labels to the panel
            JLabel choosegradetxt = createLabel("Choose which grade you are in!", Color.white);
            JLabel createdbytxt = createLabel("Created by Prithu Kathet and Travis Lester", Color.white);

            // Create buttons for each grade level
            JButton buttonK2 = createGradeButton("K-2");
            JButton button35 = createGradeButton("3-5");
            JButton button68 = createGradeButton("6-8");
            JButton instructionsButton = createGradeButton("Instructions");

            // Add action listeners to buttons to handle grade selection
            buttonK2.addActionListener(e -> handleGradeButtonClick(frame, "K-2"));
            button35.addActionListener(e -> handleGradeButtonClick(frame, "3-5"));
            button68.addActionListener(e -> handleGradeButtonClick(frame, "6-8"));
            instructionsButton.addActionListener(e -> handleGradeButtonClick(frame, "Instructions"));

            // Add components to the panel with spacing
            addComponentsToPanel(panel, choosegradetxt, buttonK2, button35, button68, instructionsButton, createdbytxt);

            // Add the panel to the frame
            frame.add(panel);

            // Changes Icon of the window
            ImageIcon image = new ImageIcon("src/resources/artdealer.jpg");
            frame.setIconImage(image.getImage());

            // Makes the frame visible
            frame.setVisible(true);

            // Play background music when the application opens
            welcomeClip = createClip();
            playMusic("src/resources/welcome.wav", false, welcomeClip);

            backgroundClip = createClip();

            // Play the background music with welcome.wav overlapping
            playBackgroundMusic();

            // Stop music when the window is closed
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    stopMusic(); // Stop the music when the window is closing
                    frame.dispose(); // Dispose the frame
                }
            });
        });
    }

    // Handle button clicks to open grade windows and stop music
    private static void handleGradeButtonClick(JFrame frame, String grade) {
        openGradeWindow(grade); // Open the corresponding grade window
        stopMusic(); // Stop the music when a grade button is clicked
        frame.dispose(); // Close the main window
    }

    // Creates the main frame
    private static JFrame createMainFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        ((JComponent) frame.getRootPane()).setBorder(BorderFactory.createLineBorder(Color.GREEN, 5)); // Add a neon
                                                                                                      // green border to
                                                                                                      // the frame
        return frame;
    }

    // Create the main panel
    private static JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Space around the panel
        panel.setBackground(Color.BLACK); // Set background to black
        return panel;
    }

    // Creates a JLabel with specified text and foreground color
    private static JLabel createLabel(String text, Color foregroundColor) {
        JLabel label = new JLabel(text);
        label.setForeground(foregroundColor);
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Center label
        return label;
    }

    // Create a grade button with specified text
    private static JButton createGradeButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 70));
        button.setMaximumSize(button.getPreferredSize());
        button.setBackground(Color.black);
        button.setForeground(Color.GREEN);
        button.setFont(new Font("Comic Sans", Font.BOLD, 22));
        button.setFocusable(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Add neon green border
        return button;
    }

    // Add components to the panel with appropriate spacing
    private static void addComponentsToPanel(JPanel panel, JLabel choosegradetxt, JButton buttonK2, JButton button35,
            JButton button68, JButton instructionsButton, JLabel createdbytxt) {
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space at the top
        panel.add(choosegradetxt);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between the label and buttons
        panel.add(Box.createHorizontalGlue()); // Adding horizontal glue for centering buttons
        panel.add(buttonK2);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons
        panel.add(button35);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons
        panel.add(button68);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons
        panel.add(instructionsButton);
        panel.add(Box.createHorizontalGlue()); // Adding glue after buttons
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space at the bottom
        panel.add(createdbytxt);
    }

    // Create a new Clip instance
    private static Clip createClip() {
        try {
            return AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Play music using javax.sound.sampled
    private static void playMusic(String filepath, boolean loop, Clip clip) {
        try {
            // Open an audio input stream from the provided file path
            File soundFile = new File(filepath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Get the audio clip and open it with the stream
            clip.open(audioStream);

            // Adjust the volume
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.f); // Set the volume (in decibels)

            // Start playing the clip
            clip.start();

            // Loop the clip continuously if needed
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Play background music in a loop
    private static void playBackgroundMusic() {
        playMusic("src/resources/background.wav", true, backgroundClip); // Ensure this file exists
    }

    // Stop the music upon opening another window
    private static void stopMusic() {
        if (welcomeClip != null && welcomeClip.isRunning()) {
            welcomeClip.stop(); // Stop the welcome music
            welcomeClip.close(); // Release system resources to free up space
        }
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop(); // Stop the background music
            backgroundClip.close(); // Release system resources to free up space
        }
    }

    // Open a new window for each grade
    private static void openGradeWindow(String grade) {
        switch (grade) {
        case "K-2":
            new ArtDealerGameGui(GameType.GAMEK2); // Ensure this class is defined
            break;
        case "3-5":
            new ArtDealerGameGui(GameType.GAME35); // Ensure this class is defined
            break;
        case "6-8":
            new ArtDealerGameGui(GameType.GAME68); // Ensure this class is defined
            break;
        case "Instructions":
            new InstructionsWindow(); // Ensure this class is defined
            break;
        default:
            break;
        }
    }
}
