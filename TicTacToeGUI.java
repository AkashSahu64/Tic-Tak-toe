import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGUI {
    private JFrame frame;
    private JPanel panel;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private JButton resetButton;
    private boolean playerXTurn;
    private boolean gameOver;

    public TicTacToeGUI() {
        playerXTurn = true;
        gameOver = false;

        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 250));
                buttons[i][j].addActionListener(new ButtonListener(i, j));
                panel.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("Player X's Turn");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));

        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(statusLabel);
        buttonPanel.add(resetButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    private void checkWin() {
    for (int i = 0; i < 3; i++) {
        if (buttons[i][0].getText().equals(buttons[i][1].getText()) && 
            buttons[i][1].getText().equals(buttons[i][2].getText()) && 
            !buttons[i][0].getText().equals("")) {
            endGame(buttons[i][0].getText());
            return;
        }
    }
    for (int i = 0; i < 3; i++) {
        if (buttons[0][i].getText().equals(buttons[1][i].getText()) && 
            buttons[1][i].getText().equals(buttons[2][i].getText()) && 
            !buttons[0][i].getText().equals("")) {
            endGame(buttons[0][i].getText());
            return;
        }
    }
    if (buttons[0][0].getText().equals(buttons[1][1].getText()) && 
        buttons[1][1].getText().equals(buttons[2][2].getText()) && 
        !buttons[0][0].getText().equals("")) {
        endGame(buttons[0][0].getText());
        return;
    }
    if (buttons[0][2].getText().equals(buttons[1][1].getText()) && 
        buttons[1][1].getText().equals(buttons[2][0].getText()) && 
        !buttons[0][2].getText().equals("")) {
        endGame(buttons[0][2].getText());
        return;
    }
    if (!gameOver) {
        boolean tie = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    tie = false;
                }
            }
        }
        if (tie) {
            endGame("Tie");
        }
    }
}
    private void endGame(String winner) {
        gameOver = true;
        String message;
        if (winner.equals("Tie")) {
            message = "Game ended in a tie.";
        } else {
            message = "Player " + winner + " wins!";
        }
        statusLabel.setText(message);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        gameOver = false;
        playerXTurn = true;
        statusLabel.setText("Player X's Turn");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    private class ButtonListener implements ActionListener {
        private int row;
        private int col;

        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            if (gameOver || !buttons[row][col].getText().equals("")) {
                return;
            }

            if (playerXTurn) {
                buttons[row][col].setText("X");
                statusLabel.setText("Player O's Turn");
            } else {
                buttons[row][col].setText("O");
                statusLabel.setText("Player X's Turn");
            }

            playerXTurn = !playerXTurn;
            checkWin();
        }
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
