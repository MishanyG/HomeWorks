import java.util.Arrays;

public class homework {
    public static void main(String[] args) {
        /*
        1 Задать целочисленный массив, состоящий из элементов 0 и 1.
        Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
        Написать метод, заменяющий в принятом массиве 0 на 1, 1 на 0;
        */
        byte[] mas = {0, 1, 1, 0, 0, 1, 0, 1, 1, 1};
        modify(mas);
        /*
        2 Задать пустой целочисленный массив размером 8. Написать метод,
        который помощью цикла заполнит его значениями 1 4 7 10 13 16 19 22;
        */
        int[] p = new int[8];
        fill(p);
        /*
        3 Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], написать метод,
        принимающий на вход массив и умножающий числа меньше 6 на 2;
        */
        int[] m = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multiplier(m);
        /*
        4 Задать одномерный массив. Написать методы поиска в нём минимального и максимального элемента;
        */
        int[] o = {2, 5, 8, 2, 1, -9, 6, 3, 15, 12, 2, -18};
        min(o);
        max(o);
        /*
        5 * Создать квадратный целочисленный массив (количество строк и
        столбцов одинаковое), заполнить его диагональные элементы единицами, используя цикл(ы);
        */
        int[][] k = new int[8][8];
        square(k);
        /*
        6 ** Написать метод, в который передается не пустой одномерный целочисленный
        массив, метод должен вернуть true если в массиве есть место, в котором сумма левой и
        правой части массива равны. Примеры: checkBalance([1, 1, 1, || 2, 1]) → true,
        checkBalance ([2, 1, 1, 2, 1]) → false, checkBalance ([10, || 1, 2, 3, 4]) → true.
        Абстрактная граница показана символами ||, эти символы в массив не входят.
        */
        int[] l = {1, 5, 6, 4, 1, 2, 3, 2, 5, 3};
        equality (l);
        /*
        7 *** Написать метод, которому на вход подаётся одномерный массив и число n
        (может быть положительным, или отрицательным), при этом метод должен циклически
        сместить все элементы массива на n позиций.
        */
        int[] f = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int n = 3;
        cyclic(f, n);
        /*
        8 **** Не пользоваться вспомогательным массивом при решении задачи 7.
        */
        int[] q = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int n1 = -5;
        cyclicN(q, n1);
    }

    private static void modify (byte[] mas)
    {
        for (int i = 0; i < mas.length; i++)
        {
            if (mas[i] == 1)
                mas[i] = 0;
            else
                mas[i] = 1;
        }
    }
    private static void fill (int[] p)
    {
        for (int i = 0, j = 1; i < p.length; i++, j += 3)
            p[i] = j;
    }
    private static void multiplier (int[] m)
    {
        for (int i = 0; i < m.length; i++)
            if (m[i] < 6) m[i] *= 2;
    }
    private static void min (int[] o)
    {
        for (int i = 0, min = o[0]; i < o.length; i++)
            if (min > o[i]) min = o[i];
    }
    private static void max (int[] o)
    {
        for (int i = 0, max = o[0]; i < o.length; i++)
            if (max < o[i]) max = o[i];
    }
    private static void square (int[][] k)
    {
        for (int i = 0, j = 0; i < k.length; i++, j++)
        {
            k[i][j] = 1;
            for (int p = k.length - 1, l = 0; l < k.length; p--, l++)
                k[p][l] = 1;
        }
    }
    private static boolean equality (int[] l)
    {
        for (int i = 0, rs, ls; i < l.length - 1; i++)
        {
            rs = 0; ls = 0;
            for (int b = i + 1; b < l.length; b++)  rs += l[b];
            for (int a = 0; a <= i; a++)            ls += l[a];
            if (rs == ls)                           return true;
        }
        return false;
    }
    private static void cyclic (int[] f, int n)
    {
        int[] j = new int[f.length];
        for (int i = 0, p = n, l = f.length - n; i < f.length; i++, n--, l++)
        {
            if (n > 0)
                j[l] = f[i];
            else
                j[i - p] = f[i];
        }
        f = j;
    }
    private static void cyclicN (int[] q, int n1)
    {
        if (n1 > 0)
        {
            for (int s = 0, t; n1 > 0; s++, n1--)
            {
                t = q[0];
                for (int r = 0; r < q.length - 1;)
                {
                    q[r] = q[++r];
                }
                q[q.length - 1] = t;
            }
        }
        else
        {
            for (int s = 0, t; n1 < 0; s++, n1++)
            {
                t = q[q.length - 1];
                for (int r = q.length - 1; r != 0;)
                {
                    q[r] = q[--r];
                }
                q[0] = t;
            }
        }
    }
}
