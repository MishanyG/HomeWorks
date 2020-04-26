package homework_7;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame
{
    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_HEIGHT = 270;

    private static final int MIN_WIN_LENGTH = 3;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String FIELD_SIZE_PREFIX = "Field size is: ";
    private static final String WIN_LENGTH_PREFIX = "Win length is: ";

    private JRadioButton humVSAI;
    private JRadioButton humVShum;
    private JSlider slideWinLen;
    private JSlider slideFieldSize;

    GameWindow gameWindow;
    ChoiceOfSign choiceOfSign;

    SettingsWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.choiceOfSign = new ChoiceOfSign(gameWindow);

        Rectangle gameWindowBounds = gameWindow.getBounds();
        int posX = (int) gameWindowBounds.getCenterX() - WINDOW_WIDTH / 2;
        int posY = (int) gameWindowBounds.getCenterY() - WINDOW_HEIGHT / 2;

        setLocation(posX, posY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setTitle("Creating new game");
        setLayout(new GridLayout(10, 1));
        addGameModeControls();
        addFieldControls();
        JButton btnSaveMode = new JButton("Сохранить установки игры!");
        btnSaveMode.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btnStartClicked();
            }
        });
        add(btnSaveMode);
    }

    private void addGameModeControls()
    {
        add(new JLabel("Choose game mode"));
        humVShum = new JRadioButton("Human vs. Human");
        humVSAI = new JRadioButton("Human vs. AI", true);
        ButtonGroup groupMode = new ButtonGroup();
        groupMode.add(humVSAI);
        groupMode.add(humVShum);
        add(humVSAI);
        add(humVShum);
    }

    private void addFieldControls() {
        final JLabel lbFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
        final JLabel lbWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_WIN_LENGTH);
        slideFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slideWinLen = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_SIZE, MIN_FIELD_SIZE);

        slideWinLen.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                lbWinLength.setText(WIN_LENGTH_PREFIX + slideWinLen.getValue());
            }
        });
        slideFieldSize.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                int currentValue = slideFieldSize.getValue();
                lbFieldSize.setText(FIELD_SIZE_PREFIX + currentValue);
                slideWinLen.setMaximum(currentValue);
            }
        });

        add(new JLabel("Choose field size"));
        add(lbFieldSize);
        add(slideFieldSize);
        add(new JLabel("Choose win length"));
        add(lbWinLength);
        add(slideWinLen);
    }

    private void btnStartClicked()
    {
        int gameMode;
        if (humVSAI.isSelected())
            gameMode = Map.MODE_HVA;
        else if (humVShum.isSelected())
            gameMode = Map.MODE_HVH;
        else
            throw new RuntimeException("Unknown game mode selected");

        int fieldSize = slideFieldSize.getValue();
        int winLength = slideWinLen.getValue();
        choiceOfSign.setSettingsWindow(gameMode, fieldSize, winLength);
        setVisible(false);
    }
}
