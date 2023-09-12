import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int targetNumber;
    private int attempts;

    private JLabel label;
    private JTextField textField;
    private JButton guessButton;
    private JButton restartButton;

    public NumberGuessingGame() {
        setTitle("Угадай число");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        targetNumber = new Random().nextInt(2000) + 1;
        attempts = 0;

        label = new JLabel("Угадай число от 1 до 2000:");
        add(label);

        textField = new JTextField(10);
        add(textField);

        guessButton = new JButton("Попробовать");
        add(guessButton);

        restartButton = new JButton("Начать заново");
        restartButton.setEnabled(false);
        add(restartButton);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
    }

    private void checkGuess() {
        String userGuessText = textField.getText();

        try {
            int userGuess = Integer.parseInt(userGuessText);
            attempts++;

            if (userGuess < targetNumber) {
                JOptionPane.showMessageDialog(this, "Загаданное число больше.");
            } else if (userGuess > targetNumber) {
                JOptionPane.showMessageDialog(this, "Загаданное число меньше.");
            } else {
                JOptionPane.showMessageDialog(this, "Поздравляем! Вы угадали число " + targetNumber + " за " + attempts + " попыток.");
                restartButton.setEnabled(true);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Пожалуйста, введите целое число.");
        }

        textField.setText("");
    }

    private void restartGame() {
        targetNumber = new Random().nextInt(2000) + 1;
        attempts = 0;
        restartButton.setEnabled(false);
        JOptionPane.showMessageDialog(this, "Новая игра началась. Угадайте число от 1 до 2000.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NumberGuessingGame game = new NumberGuessingGame();
                game.setVisible(true);
            }
        });
    }
}
