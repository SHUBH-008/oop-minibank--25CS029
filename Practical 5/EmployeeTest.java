abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    abstract int monthlySalary();
}

class FullTime extends Employee {
    private int fixedSalary;

    public FullTime(String name, int id, int fixedSalary) {
        super(name, id);
        this.fixedSalary = fixedSalary;
    }

    @Override
    int monthlySalary() {
        return fixedSalary;
    }
}

class PartTime extends Employee {
    private int hourlyRate;
    private int hoursWorked;

    public PartTime(String name, int id, int hourlyRate, int hoursWorked) {
        super(name, id);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    int monthlySalary() {
        return hourlyRate * hoursWorked;
    }
}

class Intern extends Employee {
    private int stipend;

    public Intern(String name, int id, int stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    public Intern(String name, int id) {
        this(name, id, 0);
    }

    @Override
    int monthlySalary() {
        return stipend;
    }
}

public class EmployeeTest {
    public static void main(String[] args) {
        System.out.println("=== Payroll Polymorphism Demo ===");

        Employee[] staff = {
            new FullTime("Shubh", 101, 65000),
            new PartTime("Mantra Patel", 102, 500, 80),
            new FullTime("Krupal Shah", 103, 75000),
            new PartTime("Smit Joshi", 104, 400, 60),
            new Intern("Nishit Varma", 105, 10000),
            new Intern("Fenil Dave", 106)
        };

        int totalPayroll = 0;

        for (Employee emp : staff) {
            int salary = emp.monthlySalary();
            totalPayroll += salary;

            System.out.printf("[%d] %-15s Salary: Rs %,d", emp.getId(), emp.getName(), salary);

            if (emp instanceof Intern intern) {
                System.out.print("  [NOTE: Intern role - learning stipend evaluated]");
            }
            System.out.println();
        }

        System.out.println("-----------------------------------------------------");
        System.out.printf("Total Monthly Payroll: Rs %,d%n", totalPayroll);
    }
}
