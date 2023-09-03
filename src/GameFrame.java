import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {

    FieldButton[] fieldButtons = new FieldButton[9];

    JLabel xScoreLabel, oScoreLabel;

    int xScore = 0, oScore = 0;

    char playerSign;

    boolean winCondition;

    JPanel buttonsPanel, scorePanel;
    GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 3));
        buttonsPanel.setPreferredSize(new Dimension(300, 300));
        buttonsPanel.setBounds(0, 0, 300, 300);

        playerSign = 'X';

        addButtons();
        addScoreBoard();

        this.add(buttonsPanel, BorderLayout.NORTH);
        this.add(scorePanel, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }

    public void addButtons() {
        for (int i = 0; i < 9; i++) {
            fieldButtons[i] = new FieldButton("");
            fieldButtons[i].addActionListener(this);
            buttonsPanel.add(fieldButtons[i]);
        }
    }

    public void addScoreBoard() {
        scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(2, 1));
        scorePanel.setPreferredSize(new Dimension(300, 100));

        xScoreLabel = new JLabel("Player X score: " + xScore);
        oScoreLabel = new JLabel("Player O score: " + oScore);

        xScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        oScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        scorePanel.add(xScoreLabel);
        scorePanel.add(oScoreLabel);
    }

    public boolean checkWinOnRowsOrColumns(String dimension) {
        winCondition = true;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(dimension.equals("row") && !fieldButtons[3*i+j].getText().equals(String.valueOf(playerSign))) {
                    winCondition = false;
                }
                if(dimension.equals("column") && !fieldButtons[3*j+i].getText().equals(String.valueOf(playerSign))) {
                    winCondition = false;
                }
            }
            if(winCondition) {
                return true;
            }
            winCondition = true;
        }
        return false;
    }

    public boolean checkWinOnDiagonals() {
        if(fieldButtons[0].getText().equals(String.valueOf(playerSign)) && fieldButtons[4].getText().equals(String.valueOf(playerSign)) && fieldButtons[8].getText().equals(String.valueOf(playerSign))) {
            return true;
        }
        return fieldButtons[2].getText().equals(String.valueOf(playerSign)) && fieldButtons[4].getText().equals(String.valueOf(playerSign)) && fieldButtons[6].getText().equals(String.valueOf(playerSign));
    }

    public boolean checkEndOfGame() {
        return checkWinOnRowsOrColumns("row") || checkWinOnRowsOrColumns("column") || checkWinOnDiagonals();
    }

    public void resetButtons() {
        for(int i = 0; i < 9; i++) {
            fieldButtons[i].setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 9; i++) {
            if(e.getSource() == fieldButtons[i]) {
                fieldButtons[i].setText(String.valueOf(playerSign));
                if(checkEndOfGame()) {
                    JOptionPane.showMessageDialog(null, playerSign + " player won!", "", JOptionPane.PLAIN_MESSAGE);
                    resetButtons();
                    if (playerSign == 'X') {
                        xScore++;
                        xScoreLabel.setText("Player X score: " + xScore);
                    }
                    else if(playerSign == 'O') {
                        oScore++;
                        oScoreLabel.setText("Player O score: " + oScore);
                    }
                }
                if(playerSign == 'X') {
                    playerSign = 'O';
                }
                else if(playerSign == 'O') {
                    playerSign = 'X';
                }
            }
        }
    }
}
