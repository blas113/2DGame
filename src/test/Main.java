package test;

import models.GamePanel;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        JFrame windows  = new JFrame();
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setResizable(false);
        windows.setTitle("2D Game");

        GamePanel gamePanel = new GamePanel();
        windows.add(gamePanel);

        windows.pack(); // Fit the preferred size and layouts of its subcomponents

        windows.setLocationRelativeTo(null);
        windows.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}