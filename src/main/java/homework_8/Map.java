package homework_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel
{
    public static final int GM_HVA = 0;
    public static final int GM_HVH = 1;

    private static final int DOT_EMPTY = 0;
    private int DOT_HUMAN;
    private static int DOT_AI;
    private int gameMode;
    private static int statusMove = 1;
    private static final int DOT_PADDING = 10;

    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;

    private static final String MSG_WIN_HUMAN = "Победил игрок!";
    private static final String MSG_WIN_AI = "Победил компьютер!";
    private static final String MSG_DRAW = "Ничья!";

    private static final Random RANDOM = new Random();

    private int[][] field;
    private int fieldSizeX;
    private int fieldSizeY;
    private int winLength;
    private int cellWidth;
    private int cellHeight;
    private int stateGameOver;
    private boolean isGameOver;
    private boolean initialized;

    Map()
    {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                super.mouseReleased(e);
                update(e);
            }
        });
        initialized = false;
    }

    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength, int sign)
    {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLength = winLength;
        this.gameMode = gameMode;
        this.DOT_HUMAN = sign;
        if (DOT_HUMAN == 2) DOT_AI = 1; else DOT_AI = 2;
        field = new int[fieldSizeY][fieldSizeX];
        isGameOver = false;
        initialized = true;
        repaint();
    }

    private void update(MouseEvent e)
    {
        int cellX, cellY;
        if (!initialized) return;
        if (isGameOver) return;
        cellX = e.getX() / cellWidth;
        cellY = e.getY() / cellHeight;
        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY))
            return;
        if (statusMove == 1)
        {
            field[cellY][cellX] = DOT_HUMAN;

            if (checkGame(DOT_HUMAN, STATE_WIN_HUMAN)) return;
            repaint();
            if (gameMode == 0)
            {
                aiTurn();
                if (checkGame(DOT_AI, STATE_WIN_AI)) return;
            }
            if (gameMode == 1)
                statusMove = 2;
        }
        else
        {
            if (gameMode == 1)
            {
                field[cellY][cellX] = DOT_AI;
                statusMove = 1;
            }
            repaint();
            if (checkGame(DOT_AI, STATE_WIN_AI)) return;
            statusMove = 1;
        }
    }


    private boolean checkGame(int dot, int winState)
    {
        if (checkWin(dot))
        {
            setGameOver(winState);
            return true;
        }
        if (isMapFull())
        {
            setGameOver(STATE_DRAW);
            return true;
        }
        return false;
    }

    private void setGameOver(int gameOverState)
    {
        isGameOver = true;
        stateGameOver = gameOverState;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        if (!initialized) return;
        int width = getWidth();
        int height = getHeight();
        g2.setStroke(new BasicStroke(5));
        cellWidth = width / fieldSizeX;
        cellHeight = height / fieldSizeY;
        g.setColor(Color.BLACK);

        for (int i = 0; i < fieldSizeY; i++)
        {
            int y = i * cellHeight;
            g.drawLine(0, y, width, y);
        }
        for (int i = 0; i < fieldSizeX; i++)
        {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, height);
        }
        for (int y = 0; y < fieldSizeY; y++)
        {
            for (int x = 0; x < fieldSizeX; x++)
            {
                if (isEmptyCell(x, y)) continue;
                if (field[y][x] == 1)
                {
                    g.setColor(Color.RED);
                    g.drawLine((x * cellWidth + DOT_PADDING), (y * cellHeight + DOT_PADDING), (x + 1) * cellWidth - DOT_PADDING, (y + 1) * cellHeight - DOT_PADDING);
                    g.drawLine((x + 1) * cellWidth - DOT_PADDING, (y * cellHeight + DOT_PADDING), (x * cellWidth + DOT_PADDING), (y + 1) * cellHeight - DOT_PADDING);
                }
                else if (field[y][x] == 2)
                {
                    g.setColor(Color.BLUE);
                    g.drawOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING,cellWidth - DOT_PADDING * 2, cellHeight - DOT_PADDING * 2);
                }
                else
                {
                    throw new RuntimeException(String.format("Can't recognize cell field[%d][%d]: %d", y, x, field[y][x]));
                }
            }
        }
        if (isGameOver)
            showMessageGameOver(g);
    }

    private void showMessageGameOver(Graphics g)
    {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));

        switch (stateGameOver)
        {
            case STATE_DRAW:
                g.drawString(MSG_DRAW, 180, getHeight() / 2);
                break;
            case STATE_WIN_AI:
                if (gameMode == 0)
                    g.drawString(MSG_WIN_AI, 15, getHeight() / 2);
                else
                    g.drawString("Победил игрок 2!", 55, getHeight() / 2);
                break;
            case STATE_WIN_HUMAN:
                if (gameMode == 0)
                    g.drawString(MSG_WIN_HUMAN, 70, getHeight() / 2);
                else
                    g.drawString("Победил игрок 1!", 55, getHeight() / 2);
                break;
            default:
                throw new RuntimeException("Unexpected gameOver state: " + stateGameOver);
        }
    }

    private void aiTurn()
    {
        if(turnAIWinCell()) return;
        if(turnHumanWinCell()) return;

        int x, y;

        do
        {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        }
        while (!isEmptyCell(x, y));
        field[y][x] = DOT_AI;
    }

    private boolean turnAIWinCell()
    {
        for (int i = 0; i < fieldSizeY; i++)
        {
            for (int j = 0; j < fieldSizeX; j++)
            {
                if (isEmptyCell(j, i))
                {
                    field[i][j] = DOT_AI;
                    if (checkWin(DOT_AI)) return true;
                    field[i][j] = DOT_EMPTY;
                }
            }
        }
        return false;
    }

    private boolean turnHumanWinCell()
    {
        for (int i = 0; i < fieldSizeY; i++)
        {
            for (int j = 0; j < fieldSizeX; j++)
            {
                if (isEmptyCell(j, i))
                {
                    field[i][j] = DOT_HUMAN;
                    if (checkWin(DOT_HUMAN))
                    {
                        field[i][j] = DOT_AI;
                        return true;
                    }
                    field[i][j] = DOT_EMPTY;
                }
            }
        }
        return false;
    }

    private  boolean checkWin(int c)
    {
        int[][] shift = {{1, 0}, {1, 1}, {0, 1}, {1, -1}};
        for (int i = 0; i < fieldSizeX; i++)
            for (int j = 0; j < fieldSizeY; j++)
                for (int v = 0; v < shift.length; v++)
                    if (checkLine(i, j, shift[v][0], shift[v][1], winLength, c)) return true;
        return false;
    }

    private boolean checkLine(int x, int y, int vx, int vy, int len, int c)
    {
        final int far_x = x + (len - 1) * vx;
        final int far_y = y + (len - 1) * vy;

        if (!isValidCell(far_x, far_y)) return false;

        for (int i = 0; i < len; i++)
            if (field[y + i * vy][x + i * vx] != c) return false;

        return true;
    }

    private boolean isMapFull()
    {
        for (int i = 0; i < fieldSizeY; i++)
            for (int j = 0; j < fieldSizeX; j++)
                if (field[i][j] == DOT_EMPTY) return false;
        return true;
    }

    private boolean isValidCell(int x, int y)
    {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isEmptyCell(int x, int y)
    {
        return field[y][x] == DOT_EMPTY;
    }
}
