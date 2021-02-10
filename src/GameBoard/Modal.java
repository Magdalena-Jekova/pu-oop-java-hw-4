package GameBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modal extends JDialog {

    public Modal(JFrame parent, String title, String message) {
        super(parent, title, true);

        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);

        JButton restartButton = new JButton("Нова игра");

        panel.add(label);
        panel.add(restartButton);
        getContentPane().add(panel);
        this.setSize(370,150);
        restartButton.addActionListener(new RestartGameButton());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public Modal(JFrame parent, String message) {
        super(parent, true);

        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);

        panel.add(label);
        getContentPane().add(panel);
        this.setSize(370,150);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void showMessage(JFrame parent, String message){
        new Modal(parent, message);
    }

    public static void showEndOfTheGameMessage(JFrame parent, String title, String message){
        new Modal(parent, title, message);
    }

    public static class RestartGameButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameBoard newGame = new GameBoard();
        }
    }
}