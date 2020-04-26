package homework_7;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel
{
    public static final int MODE_HVH = 0;
    public static final int MODE_HVA = 1;

    private static int sizeCell;
    private int gameMode;
    private int fieldSizeX;
    private int fieldSizeY;
    private int winLength;
    private char sign;

    Map()
    {
        setBackground(Color.CYAN);
    }

    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength, char sign)
    {
        System.out.printf("mode: %d, size: %d, len: %d, sign: %c\n",
                gameMode, fieldSizeX, winLength, sign);
        this.gameMode = gameMode;
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLength = winLength;
        this.sign = sign;
        sizeCell = getWidth() / fieldSizeX;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        for (int i = 0; i <= fieldSizeX; i++)
        {
            graphics.drawLine(0, i * sizeCell, getWidth(), i * sizeCell);
            graphics.drawLine(i * sizeCell, 0, i * sizeCell, getWidth());
        }
        graphics.dispose();
    }
}
