package homework_5;

public class Horse extends Animal
{
    protected static int MAX_RUN = 1500;
    protected final float MAX_JUMP = 3.0f;
    protected final int MAX_SWIM = 100;

    public Horse(String name, int run, float jump, int swim) {
        super(name, run, jump, swim);
    }

    public static void setMaxRun(int maxRun) {
        MAX_RUN = maxRun;
    }

    @Override
    public void run(int length) {
        if (MAX_RUN >= length)
            System.out.println(length + " м. : Лошадь пробежала!");
        else
            System.out.println("Лошадь не способна пробежать такую дистанцию!");
    }

    @Override
    public void swim(int length) {
        if (MAX_SWIM >= length)
            System.out.println(length + " м. : Лошадь проплыла!");
        else
            System.out.println("Очень жаль, но лошадь не способна так далеко проплыть!");
    }

    @Override
    public void jumpOver(int height) {
        if (MAX_JUMP >= height)
            System.out.println(height + " м. : Лошадь прыгнула!");
        else
            System.out.println("Лошадь не способна прыгнуть так высоко!");
    }
}
