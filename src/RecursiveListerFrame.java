import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class RecursiveListerFrame extends JFrame {
    JPanel main;
    JPanel button;
    JPanel display;

    JTextArea textArea;

    JScrollPane scroll;

    JLabel label;

    JButton start;
    JButton quit;

    JFileChooser chooser;

    public RecursiveListerFrame() {
        main = new JPanel();
        main.setLayout(new BorderLayout());

        label = new JLabel("Recursive Lister");
        label.setFont(new Font("Times New Roman", Font.BOLD, 36));
        label.setHorizontalAlignment(JLabel.CENTER);

        createDisplay();
        createButton();

        main.add(label, BorderLayout.NORTH);
        main.add(display, BorderLayout.CENTER);
        main.add(button, BorderLayout.SOUTH);

        add(main);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();

        int screenWidth = screen.width;
        int screenHeight = screen.height;

        setSize(screenWidth / 2, screenHeight / 2);
        setLocation(screenWidth / 4, screenHeight / 4);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createDisplay() {
        display = new JPanel();
        display.setLayout(new GridLayout(1, 1));

        textArea = new JTextArea();
        textArea.setEditable(false);

        scroll = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        display.add(scroll);
    }

    public void createButton() {
        button = new JPanel();
        button.setLayout(new GridLayout(1, 2));

        start = new JButton("Start");
        quit = new JButton("Quit");

        start.setFont(new Font("Times New Roman", Font.BOLD, 18));
        quit.setFont(new Font("Times New Roman", Font.BOLD, 18));

        start.addActionListener((ActionEvent e) -> {display();});
        quit.addActionListener((ActionEvent e) -> {System.exit(0);});

        button.add(start);
        button.add(quit);
    }

    public void display() {
        textArea.setText("");
        chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        File f = null;
        String[] paths;

        chooser.setCurrentDirectory(workingDirectory);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            f = chooser.getCurrentDirectory();
            paths = f.list();
            textArea.append("Current Directory: " + f + "\n" + "Sub Directories: \n");
            assert paths != null;
            for(String i : paths) {
                textArea.append(i + "\n");
            }
        } else {
            JOptionPane.showMessageDialog(main, "You did not select a directory", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}