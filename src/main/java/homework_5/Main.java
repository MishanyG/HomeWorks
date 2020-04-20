package homework_5;

public class Main
{
    public static void main(String[] args)
    {
        Dog dog = new Dog("Собака", 500, 0.5f, 10);
        Horse horse = new Horse("Лошадь", 1500, 3, 100);
        Bird bird = new Bird("Птичка", 5, 0.2f);
        Cat cat = new Cat("Кот", 200, 2);

        Animal[] animals = {dog, horse, bird, cat};

        for (int i = 0; i < animals.length; i++)
        {
            System.out.println("Животное: " + animals[i].name +
                    " Дистанция бега: " + animals[i].run + " м. " +
                    "Высота прыжка: " + animals[i].jump + " м. " + "Дистанция плавания: " + animals[i].swim + " м.");
        }

        Cat.setMaxRun(100);
        cat.run(50);
        Dog.setMaxRun(400);
        dog.run(150);

    }
}
