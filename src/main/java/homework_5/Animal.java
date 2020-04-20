package homework_5;

public abstract class Animal
{
    protected String name;
    protected int run;
    protected float jump;
    protected int swim;

    public Animal(String name, int run, float jump, int swim) {
        this.name = name;
        this.run = run;
        this.jump = jump;
        this.swim = swim;
    }

    public Animal(String name, int run, float jump) {
        this.name = name;
        this.run = run;
        this.jump = jump;
    }

    public abstract void run(int length);

    public abstract void swim(int length);

    public abstract void jumpOver(int height);
}
