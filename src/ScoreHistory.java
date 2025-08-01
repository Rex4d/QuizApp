import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class ScoreHistory extends JFrame {
    JTextArea textArea;

    public ScoreHistory() {
        setTitle("Score History");
        setSize(400, 300);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Previous Quiz Scores", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        loadScores();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // allows closing this window only
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadScores() {
        File file = new File("scores.txt");
        if (!file.exists()) {
            textArea.setText("No scores saved yet.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                textArea.append(scanner.nextLine() + "\n");
            }
        } catch (IOException e) {
            textArea.setText("Error reading scores.");
        }
    }
}
