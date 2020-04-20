package homework_5;

public class Bird extends Animal
{
    protected static int MAX_RUN = 5;
    protected final float MAX_JUMP = 0.2f;

    public Bird(String name, int run, float jump) {
        super(name, run, jump);
    }

    public static void setMaxRun(int maxRun) {
        MAX_RUN = maxRun;
    }

    @Override
    public void run(int length) {
        if (MAX_RUN >= length)
            System.out.println(length + " м. : Птичка пробежала!");
        else
            System.out.println("Птичка не способна пробежать такую дистанцию!");
    }

    @Override
    public void swim(int length) {
        System.out.println("Очень жаль, но птичка не умеет плавать!");
    }

    @Override
    public void jumpOver(int height) {
        if (MAX_JUMP >= height)
            System.out.println(height + " м. : Птичка прыгнула!");
        else
            System.out.println("Птичка не способна прыгнуть так высоко!");
    }
}
