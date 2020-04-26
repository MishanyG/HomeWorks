package homework_7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame
{
    private static final int WIN_WIDTH = 507;
    private static final int WIN_HEIGHT = 555;
    private static final int WIN_POSX = 650;
    private static final int WIN_POSY = 250;

    private SettingsWindow settingsWindow;
    private ChoiceOfSign choiceOfSign;
    private Map map;

    GameWindow()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocation(WIN_POSX, WIN_POSY);
        setTitle("Крестики - Нолики");
        setResizable(false);
        JButton btnStart = new JButton("<html><body><p><font color=\"green\"><b>Start new game</b></font></p></body></html>");
        JButton btnExit = new JButton("<html><body><p><font color=\"red\"><b>Exit game</b></font></p></body></html>");
        ActionListener(btnStart, btnExit);
        JPanel panelBottom = new JPanel(new GridLayout(1, 2));
        panelBottom.add(btnStart);
        panelBottom.add(btnExit);
        map = new Map();
        settingsWindow = new SettingsWindow(this);
        choiceOfSign = new ChoiceOfSign(this);
        add(panelBottom, BorderLayout.SOUTH);
        add(map);
        setVisible(true);
    }

    void startGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength, char sign)
    {
        map.startNewGame(gameMode, fieldSizeX, fieldSizeY, winLength, sign);
    }

    private void ActionListener(JButton btnStart, JButton btnExit)
    {
        btnStart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                settingsWindow.setVisible(true);
            }
        });

        btnExit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
    }
}
