package homework_5;

public class Cat extends Animal
{
    protected static int MAX_RUN = 200;
    protected final float MAX_JUMP = 2.0f;

    public Cat(String name, int run, float jump) {
        super(name, run, jump);
    }

    public static void setMaxRun(int maxRun) {
        MAX_RUN = maxRun;
    }

    @Override
    public void run(int length) {
        if (MAX_RUN >= length)
            System.out.println(length + " м. : Котик пробежал!");
        else
            System.out.println("Котик не способен пробежать такую дистанцию!");
    }

    @Override
    public void swim(int length) {
        System.out.println("Очень жаль, но котик не умеет плавать!");
    }

    @Override
    public void jumpOver(int height) {
        if (MAX_JUMP >= height)
            System.out.println(height + " м. : Котик прыгнул!");
        else
            System.out.println("Котик не способен прыгнуть так высоко!");
    }
}
