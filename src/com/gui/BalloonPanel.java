package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class BalloonPanel extends JPanel {
    private ArrayList<Point> balloonPositions = new ArrayList<>();
    private Timer timer;

    public BalloonPanel() {
        // Add starting positions for balloons
        for (int i = 0; i < 10; i++) {
            balloonPositions.add(new Point((int) (Math.random() * 400), 400 + i * 40)); // Random horizontal positions
        }

        // Create timer to move balloons upwards
        timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveBalloons();
                repaint();
            }
        });
        timer.start(); // Start the animation
    }

    private void moveBalloons() {
        for (Point p : balloonPositions) {
            p.y -= 5; // Move the balloon upwards
            if (p.y < -50) { // Reset balloon if it moves off screen
                p.y = 400;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < balloonPositions.size(); i++) {
            Point p = balloonPositions.get(i);
            if (i % 2 == 0) {
                g.setColor(Color.BLUE); // Set the color to blue for even index
            } else {
                g.setColor(Color.GREEN); // Set the color to green for odd index
            }
            g.fillOval(p.x, p.y, 30, 50); // Draw each balloon
        }
    }
}