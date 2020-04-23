package homework_6;

import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        FileOutputStream file1 = new FileOutputStream("file_1.txt", true);
        FileOutputStream file2 = new FileOutputStream("file_2.txt", true);

        PrintStream psf1 = new PrintStream(file1);
        psf1.println("/**\n" +
                "     * Constructs an {@code IOException} with the specified cause and a\n" +
                "     * detail message of {@code (cause==null ? null : cause.toString())}\n" +
                "     * (which typically contains the class and detail message of {@code cause}).\n" +
                "     * This constructor is useful for IO exceptions that are little more\n" +
                "     * than wrappers for other throwables.\n" +
                "     *\n" +
                "     * @param cause\n" +
                "     *        The cause (which is saved for later retrieval by the\n" +
                "     *        {@link #getCause()} method).  (A null value is permitted,\n" +
                "     *        and indicates that the cause is nonexistent or unknown.)\n" +
                "     *\n" +
                "     * @since 1.6\n" +
                "     */");

        PrintStream psf2 = new PrintStream(file2);
        psf2.println("/**\n" +
                " * A {@code PrintStream} adds functionality to another output stream,\n" +
                " * namely the ability to print representations of various data values\n" +
                " * conveniently.  Two other features are provided as well.  Unlike other output\n" +
                " * streams, a {@code PrintStream} never throws an\n" +
                " * {@code IOException}; instead, exceptional situations merely set an\n" +
                " * internal flag that can be tested via the {@code checkError} method.\n" +
                " * Optionally, a {@code PrintStream} can be created so as to flush\n" +
                " * automatically; this means that the {@code flush} method is\n" +
                " * automatically invoked after a byte array is written, one of the\n" +
                " * {@code println} methods is invoked, or a newline character or byte\n" +
                " * ({@code '\\n'}) is written.\n" +
                " *\n" +
                " * <p> All characters printed by a {@code PrintStream} are converted into\n" +
                " * bytes using the given encoding or charset, or platform's default character\n" +
                " * encoding if not specified.\n" +
                " * The {@link PrintWriter} class should be used in situations that require\n" +
                " *  writing characters rather than bytes.\n" +
                " *\n" +
                " * <p> This class always replaces malformed and unmappable character sequences with\n" +
                " * the charset's default replacement string.\n" +
                " * The {@linkplain java.nio.charset.CharsetEncoder} class should be used when more\n" +
                " * control over the encoding process is required.\n" +
                " *\n" +
                " * @author     Frank Yellin\n" +
                " * @author     Mark Reinhold\n" +
                " * @since      1.0\n" +
                " */");
//--------------------------------------------------------------------------------------------------------------------//
        FileInputStream f1is = new FileInputStream("file_1.txt");
        FileInputStream f2is = new FileInputStream("file_2.txt");
        FileOutputStream file3 = new FileOutputStream("file_3.txt", true);
        FileInputStream f3is = new FileInputStream("file_3.txt");

            int i;
            while ((i = f1is.read()) != -1)
            {
                PrintStream psf3 = new PrintStream(file3);
                psf3.print((char) i);
                if ((i = f1is.read()) == -1)
                {
                    psf3.print("\n");
                    while ((i = f2is.read()) != -1)
                    {
                        psf3.print((char) i);
                    }
                }
                else
                psf3.print((char) i);
            }

//--------------------------------------------------------------------------------------------------------------------//
        BufferedReader bufR = new BufferedReader(new InputStreamReader(System.in));
        String s = bufR.readLine();
        byte[] str = new byte[f3is.available()];
        f3is.read(str);
        String f = new String(str);

        char[] sc = s.toCharArray();
        char[] fc = f.toCharArray();

        boolean res = false;

        res = searchInFile(sc, fc);

        System.out.println(res);

//--------------------------------------------------------------------------------------------------------------------//
        res = searchInFolder(res);

        System.out.println(res);

        file1.close();
        file2.close();
        file3.close();
    }

//--------------------------------------------------------------------------------------------------------------------//
    private static boolean searchInFile(char[] sc, char[] fc)
    {
        for (int j = 0, p = 0; p < fc.length; p++)
        {
            if (fc[p] == sc[j])
            {
                j++;
            }
            else
            {
                j = 0;
            }
            if (j >= sc.length)
                return true;
        }
        return false;
    }

//--------------------------------------------------------------------------------------------------------------------//
    private static boolean searchInFolder(boolean res) throws IOException
    {
        FileFilter filter = new FileFilter()
        {
            public boolean accept(File file)
            {
                return file.getName().endsWith("txt");
            }
        };

        File fi = new File("C:\\JavaDevelop");
        File[] files = fi.listFiles(filter);
        BufferedReader bufRO = new BufferedReader(new InputStreamReader(System.in));
        String st = bufRO.readLine();
        char[] scI = st.toCharArray();

        assert files != null;
        for (File file : files)
        {
            FileInputStream fAllis = new FileInputStream(file.getName());
            byte[] stA = new byte[fAllis.available()];
            fAllis.read(stA);
            String t = new String(stA);
            char[] fcI = t.toCharArray();

            res = searchInFile(scI, fcI);
            if (res)
                return true;
        }
        return res;
    }
}
