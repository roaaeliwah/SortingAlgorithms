package main;

import algorithms.SelectionSort;
import gui.MainFrame;
import utils.ArrayGenerator;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // so that the GUI is created on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}