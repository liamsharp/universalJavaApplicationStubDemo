package com.github.liamsharp;

import java.awt.Dimension;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.apple.eawt.Application;


@SuppressWarnings("restriction")
public class SwingApp
{
    private static final int MB = 1024 * 1024;

    private static void createAndShowGUI(
        final List<String> args)
    {
        final JFrame frame = new JFrame(System.getProperty("java.version"));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final long maxMemory = Runtime.getRuntime().maxMemory() / MB;

        String[] cp = System.getProperty("java.class.path").split(":");
        String classpath = "";
        for (String s : cp) {
          classpath += s + "<br>";
        }


        JLabel label = new JLabel(
                "<html>" +
                "Hello!<br>" +
                "args: " + args + "<br>" +
                "maxMemory: " + maxMemory + "M<br>" +
                "classpath: " + classpath +
                "</html>");
        frame.getContentPane().add(label);
        frame.setMinimumSize(new Dimension(400, 300));

        frame.pack();
        frame.setVisible(true);
    }

    private static void handleFileOpen(
        final List<File> files)
    {
       JOptionPane.showMessageDialog(null, files);
    }

    public static void main(String[] args)
    {
        final Application appleApp = Application.getApplication();
        appleApp.setOpenFileHandler(e -> SwingUtilities.invokeLater(() -> handleFileOpen(e.getFiles())));
        javax.swing.SwingUtilities.invokeLater( () -> createAndShowGUI(Arrays.asList(args)));
    }

}
