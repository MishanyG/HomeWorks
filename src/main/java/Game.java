import java.util.Random;
import java.util.Scanner;

public class Game {

    private static int fieldSizeY;
    private static int fieldSizeX;
    private static int X;
    private static int Y;
    private static int fishki;
    private static char[][] field;

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    private static final char HUMAN_DOT = 'X';
    private static final char AI_DOT = 'O';
    private static final char EMPTY_DOT = '.';

    // init field
    private static void initMap() {
        fieldSizeY = 5;
        fieldSizeX = 5;
        fishki = 4;
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = EMPTY_DOT;
            }
        }
    }

    // print field
    private static void printMap() {
        System.out.println("-------");
        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print("|");
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
    }

    // human turn
    private static void humanTurn() {
        do {
            System.out.printf("Пожалуйста, введите координаты X и Y (в диапазоне от 1 до %d)»: ", fieldSizeX);
            X = SCANNER.nextInt() - 1;
            Y = SCANNER.nextInt() - 1;
        } while (!(isEmptyCell(Y, X) && isValidCell(Y, X)));
        field[Y][X] = HUMAN_DOT;
    }

    // is cell empty
    private static boolean isEmptyCell(int y, int x)
    {
        return field[y][x] == EMPTY_DOT;
    }

    // is cell valid
    private static boolean isValidCell(int y, int x)
    {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    // ai turn
    private static void aiTurn()
    {
        int x = -1, y = -1, n = 0;
        for (int i = 0; i < field.length; i++)
        {
            for (int j = 0; j < field.length; j++)
            {
                if ((isEmptyCell(i, j) && isValidCell(i, j)))
                {
                    field[i][j] = AI_DOT;
                    if (checkWin(AI_DOT))
                    {
                        x = i;
                        y = j;
                        n = 1;
                    }
                    field[i][j] = EMPTY_DOT;
                }
            }
        }
        for (int i = 0; i < field.length; i++)
        {
            for (int j = 0; j < field.length; j++)
            {
                if ((isEmptyCell(i, j) && isValidCell(i, j)))
                {
                    field[i][j] = HUMAN_DOT;
                    if (checkWin(HUMAN_DOT))
                    {
                        x = i;
                        y = j;
                        n = 1;
                    }
                    field[i][j] = EMPTY_DOT;
                }
            }
        }
        if (n == 0)
        {
            do {
                X = RANDOM.nextInt(fieldSizeX);
                Y = RANDOM.nextInt(fieldSizeY);
            } while (!isEmptyCell(Y, X));
            field[Y][X] = AI_DOT;
        }
        else field[x][y] = AI_DOT;
    }

    // check win
    private static boolean checkWin(char c)
    {
        for (int g = 0, a = 0, b = 0; g < field.length; g++)
        {
            for (int i = 0, d = 0, f = 0; i < field.length; i++)
            {
                d += field[g][i] == c ? 1 : -1;
                f += field[i][g] == c ? 1 : -1;
                if (i == g)
                {
                    a += field[g][g] == c ? 1 : -1;
                    b += field[g][field.length - 1 - g] == c ? 1 : -1;
                }
                if (d >= fishki || f >= fishki)
                    return true;
                if (d < 0)
                    d = 0;
                if (f < 0)
                    f = 0;
            }
            if (a >= fishki || b >= fishki)
                return true;
            if (a < 0)
                a = 0;
            if (b < 0)
                b = 0;
        }
        for (int g = 0, a = 0, b = 0; g < field.length; g++)
        {
            for (int i = 1; i < field.length; i++)
            {
                if (i - 1 == g)
                {
                    a += field[g + 1][g] == c ? 1 : -1;
                    b += field[g + 1][field.length - 1 - g] == c ? 1 : -1;
                }
            }
            if (a >= fishki || b >= fishki)
                return true;
            if (a < 0)
                a = 0;
            if (b < 0)
                b = 0;
        }
        return false;
    }

    // check draw
    private static boolean isMapFull() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(y, x)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        while (true) {
            initMap();
            printMap();
            while (true) {
                humanTurn();
                printMap();
                if (gameChecks(HUMAN_DOT, "Human win!")) break;
                aiTurn();
                printMap();
                if (gameChecks(AI_DOT, "AI win!")) break;
            }
            System.out.println("Play again?");
            if (!SCANNER.next().equals("Y"))
                break;
        }
        SCANNER.close();
    }

    private static boolean gameChecks(char aiDot, String s) {
        if (checkWin(aiDot)) {
            System.out.println(s);
            return true;
        }
        if (isMapFull()) {
            System.out.println("draw!");
            return true;
        }
        return false;
    }
}