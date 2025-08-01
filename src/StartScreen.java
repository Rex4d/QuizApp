import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartScreen extends JFrame implements ActionListener {
    JTextField nameField;
    JButton startButton, exitButton, viewScoresButton;

    public StartScreen() {
        setTitle("Welcome to Java Quiz");
        setSize(400, 300);
        setLayout(new GridLayout(5, 1));

        JLabel welcomeLabel = new JLabel("Welcome to the Java Quiz!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel);

        nameField = new JTextField();
        nameField.setToolTipText("Enter your name");
        add(nameField);

        startButton = new JButton("Start Quiz");
        startButton.addActionListener(this);
        add(startButton);

        viewScoresButton = new JButton("View Score History");
        viewScoresButton.addActionListener(this);
        add(viewScoresButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        add(exitButton);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name");
                return;
            }

            dispose();

            Question[] questions = {
                    new Question("What is the capital of France?", new String[]{"Paris", "Rome", "Berlin", "Madrid"}, 0),
                    new Question("Which language runs in a web browser?", new String[]{"Java", "Python", "JavaScript", "C++"}, 2),
                    new Question("What does OOP stand for?", new String[]{"Object Oriented Programming", "Online Object Program", "Overriding Programming", "Optional Output Processing"}, 0),
                    new Question("Which keyword is used to create an object?", new String[]{"create", "object", "new", "class"}, 2),
                    new Question("Which company developed Java?", new String[]{"Google", "Sun Microsystems", "Apple", "Microsoft"}, 1)
            };

            new Quiz(questions, name);

        } else if (e.getSource() == viewScoresButton) {
            new ScoreHistory(); // open score history window

        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}
