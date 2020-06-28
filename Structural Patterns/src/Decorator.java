import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

interface EmployeeComponent
{
    String getName();
    void doTask();
    void join(Date joinDate);
    void terminate(Date terminateDate);
    default String formatDate(Date theDate)
    {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(theDate);
    }
    default void ShowBasicInformation()
    {
        System.out.println("-------");
        System.out.println("The basic information of " + getName());

        join(Calendar.getInstance().getTime());

        Calendar terminateDate = Calendar.getInstance();
        terminateDate.add(Calendar.MONTH, 6);
        terminate(terminateDate.getTime());
    }
}

class EmployeeConcreteComponent implements EmployeeComponent
{
    private String name;
    public EmployeeConcreteComponent(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return  name;
    }

    @Override
    public void doTask()
    {
        // Unassigned task
    }

    @Override
    public void join(Date joinDate)
    {
        System.out.println(this.getName() + " joined on " + formatDate(joinDate));
    }

    @Override
    public void terminate(Date terminateDate)
    {
        System.out.println(this.getName() + " terminated on " + formatDate(terminateDate));
    }
}

abstract class EmployeeDecorator implements EmployeeComponent
{
    protected EmployeeComponent employee;

    protected EmployeeDecorator(EmployeeComponent employee)
    {
        this.employee = employee;
    }

    @Override
    public String getName()
    {
        return employee.getName();
    }

    @Override
    public void join(Date joinDate)
    {
        employee.join(joinDate);
    }

    @Override
    public void terminate(Date terminateDate)
    {
        employee.terminate(terminateDate);
    }
}

class Manager extends EmployeeDecorator
{
    protected Manager(EmployeeComponent employee)
    {
        super(employee);
    }
    public void createRequirement()
    {
        System.out.println(this.employee.getName() + " creates requirements.");
    }
    public void assignTask()
    {
        System.out.println(this.employee.getName() + "assigns tasks.");
    }
    public void manageProgress()
    {
        System.out.println(this.employee.getName() + " manages the progress.");
    }
    @Override
    public void doTask()
    {
        employee.doTask();
        createRequirement();
        assignTask();
        manageProgress();
    }
}

class TeamLeader extends EmployeeDecorator
{
    protected TeamLeader(EmployeeComponent employee)
    {
        super(employee);
    }

    public void plan()
    {
        System.out.println(this.employee.getName() + " is planing.");
    }
    public void motivate() {
        System.out.println(this.employee.getName() + " is motivating his members.");
    }

    public void monitor() {
        System.out.println(this.employee.getName() + " is monitoring his members.");
    }

    @Override
    public void doTask()
    {
        employee.doTask();
        plan();
        motivate();
        monitor();
    }
}

class Client
{
    public static void main(String[] args)
    {
        EmployeeComponent employee = new EmployeeConcreteComponent("Nam");
        employee.doTask();
        employee.ShowBasicInformation();

        EmployeeComponent manager = new Manager(employee);
        manager.ShowBasicInformation();
        manager.doTask();
    }
}