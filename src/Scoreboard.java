import javax.swing.*;
import java.io.*;
import java.awt.*;

public class Scoreboard extends JFrame {
    public Scoreboard(String playerName, int score, int total) {
        setTitle("Scoreboard");
        setSize(300, 150);
        setLayout(null);

        JLabel result = new JLabel("You scored: " + score + " out of " + total);
        result.setBounds(50, 30, 200, 30);
        add(result);

        JLabel status = new JLabel(saveScore(playerName, score, total));
        status.setBounds(50, 60, 200, 30);
        add(status);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String saveScore(String name, int score, int total) {
        try (FileWriter fw = new FileWriter("scores.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("Player: " + name + " | Score: " + score + "/" + total);
            bw.newLine();
            return "Score saved successfully.";
        } catch (IOException e) {
            return "Error saving score.";
        }
    }
}
