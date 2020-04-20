package homework_5;

public class Dog extends Animal
{
    protected static int MAX_RUN = 500;
    protected final float MAX_JUMP = 0.5f;
    protected final int MAX_SWIM = 10;

    public Dog(String name, int run, float jump, int swim) {
        super(name, run, jump, swim);
    }

    public static void setMaxRun(int maxRun) {
        MAX_RUN = maxRun;
    }

    @Override
    public void run(int length) {
        if (MAX_RUN >= length)
            System.out.println(length + " м. : Пёсик пробежал!");
        else
        System.out.println("Пёсик не способен пробежать такую дистанцию!");
    }

    @Override
    public void swim(int length) {
        if (MAX_SWIM >= length)
            System.out.println(length + " м. : Пёсик проплыл!");
        else
            System.out.println("Очень жаль, но пёсик не способен так далеко проплыть!");
    }

    @Override
    public void jumpOver(int height) {
        if (MAX_JUMP >= height)
            System.out.println(height + " м. : Пёсик прыгнул!");
        else
            System.out.println("Пёсик не способен прыгнуть так высоко!");
    }
}
