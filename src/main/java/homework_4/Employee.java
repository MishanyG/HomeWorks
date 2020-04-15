package homework_4;

public class Employee
{
    private String   fcs    = "Алексеев Алексей Алексеевич";
    public int       salary = 45000;
    private int      age    = 50;

    private static int count = 0;
    public int         id    = 0;

    public Employee(String f, int s, int a, int id)
    {
        fcs     = f;
        salary  = s;
        age     = a;
        count   += id;
        this.id = count;
    }

    public Employee()
    {

    }

    public int getId() {
        return id;
    }

    public String getFcs()
    {
        return fcs;
    }

    public int getSalary()
    {
        return salary;
    }

    public int getAge()
    {
        return age;
    }

    public void findFsc()
    {
        System.out.println("ФИО: " + getFcs());
    }

    public void findAge()
    {
        System.out.println("Возраст: " + getAge());
    }
}
