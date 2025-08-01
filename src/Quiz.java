import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Quiz extends JFrame implements ActionListener {
    Question[] questions;
    int index = 0, score = 0;
    JLabel qLabel, timerLabel;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup bg = new ButtonGroup();
    JButton nextBtn;
    String playerName;

    Timer countdownTimer;
    int timeLeft = 15; // seconds per question

    public Quiz(Question[] questions, String playerName) {
        this.questions = questions;
        this.playerName = playerName;

        setTitle("Java Quiz");
        setSize(500, 300);
        setLayout(new BorderLayout());

        // Top panel with question and timer
        JPanel topPanel = new JPanel(new BorderLayout());
        qLabel = new JLabel("Question");
        qLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timerLabel = new JLabel("Time: " + timeLeft + "s", SwingConstants.RIGHT);
        timerLabel.setForeground(Color.RED);
        topPanel.add(qLabel, BorderLayout.WEST);
        topPanel.add(timerLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Center panel with options
        JPanel centerPanel = new JPanel(new GridLayout(4, 1));
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            bg.add(options[i]);
            centerPanel.add(options[i]);
        }
        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel with button
        nextBtn = new JButton("Next");
        nextBtn.addActionListener(this);
        add(nextBtn, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        loadQuestion();
        startTimer();
    }

    void startTimer() {
        timeLeft = 15;
        timerLabel.setText("Time: " + timeLeft + "s");

        if (countdownTimer != null) {
            countdownTimer.stop();
        }

        countdownTimer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time: " + timeLeft + "s");
            if (timeLeft <= 0) {
                countdownTimer.stop();
                submitAnswer(); // auto submit when time is up
            }
        });

        countdownTimer.start();
    }

    void loadQuestion() {
        bg.clearSelection();
        qLabel.setText(questions[index].question);
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[index].options[i]);
        }
        startTimer();
    }

    void submitAnswer() {
        int selected = -1;
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selected = i;
                break;
            }
        }

        if (selected != -1 && questions[index].isCorrect(selected)) {
            score++;
        }

        index++;
        if (index < questions.length) {
            loadQuestion();
        } else {
            dispose();
            new Scoreboard(playerName, score, questions.length);
        }
    }

    public void actionPerformed(ActionEvent e) {
        countdownTimer.stop();
        submitAnswer();
    }
}
