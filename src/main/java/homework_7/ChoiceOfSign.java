package homework_7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceOfSign extends JFrame
{
    private static final int WINDOW_WIDTH = 250;
    private static final int WINDOW_HEIGHT = 120;

    private JRadioButton cross;
    private JRadioButton zero;

    private int gameMode;
    private int fieldSize;
    private int winLength;

    GameWindow gameWindow;

    ChoiceOfSign(GameWindow gameWindow)
    {
        this.gameWindow = gameWindow;

        Rectangle gameWindowBounds = gameWindow.getBounds();
        int posX = (int) gameWindowBounds.getCenterX() - WINDOW_WIDTH / 2;
        int posY = (int) gameWindowBounds.getCenterY() - WINDOW_HEIGHT / 2;

        setLocation(posX, posY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setTitle("Creating new game");
        setLayout(new GridLayout(3, 1));
        addModeCrossZero();
        JButton btnStart = new JButton("Start Game");
        btnStart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btnStartClicked();
            }
        });
        add(btnStart);
    }

    private void addModeCrossZero()
    {
        add(new JLabel("Кем предпочтёте играть?"));
        JPanel panelCenter = new JPanel(new GridLayout(1, 2));
        cross = new JRadioButton("X", true);
        zero = new JRadioButton("O");
        ButtonGroup groupMode = new ButtonGroup();
        groupMode.add(cross);
        groupMode.add(zero);
        panelCenter.add(cross);
        panelCenter.add(zero);
        add(panelCenter);
    }

    private void btnStartClicked()
    {
        char sign;
        if (cross.isSelected())
            sign = 'X';
        else if (zero.isSelected())
            sign = 'O';
        else
            throw new RuntimeException("Unknown game mode selected");
        gameWindow.startGame(gameMode, fieldSize, fieldSize, winLength, sign);
        setVisible(false);
    }

    public void setSettingsWindow(int gameMode, int fieldSize, int winLength)
    {
        this.gameMode = gameMode;
        this.fieldSize = fieldSize;
        this.winLength = winLength;
        setVisible(true);
    }
}
