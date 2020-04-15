package homework_4;

import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        Employee employee = new Employee();
        employee.findFsc();
        employee.findAge();

        Employee[] employees = new Employee[5];
        int id = 1;

        employees[0] = new Employee("Иванов Иван Иванович", 15000, 35, id);
        employees[1] = new Employee("Петров Пётр Петрович", 23000, 46, id);
        employees[2] = new Employee("Сидоров Сидор Сидорович", 38000, 52, id);
        employees[3] = new Employee("Васильев Василий Васильевич", 45000, 38, id);
        employees[4] = new Employee("Сергеев Сергей Сергеевич", 12000, 23, id);

        for (int i = 0; i < employees.length; i++)
            if (employees[i].getAge() > 40)
                System.out.println("ID: " + employees[i].getId() + " ФИО: " + employees[i].getFcs() + "\nЗароботная плата: " + employees[i].getSalary() + "\nВозраст: " + employees[i].getAge());

        enhancing(employees);

        int sal = 0, ag = 0;
        for (int i = 0; i < employees.length; i++)
        {
            sal += employees[i].getSalary();
            ag += employees[i].getAge();
        }
        sal = sal / employees.length;
        ag = ag / employees.length;
    }

    public static void enhancing(Employee[] employees)
    {
        for (int i = 0; i < employees.length; i++)
            if (employees[i].getAge() > 45)
                employees[i].salary = employees[i].getSalary() + 5000;
    }
}
