import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LazySingleton
{
    private static LazySingleton instance;
    private LazySingleton()
    {
        System.out.println("Initializing a lazy singleton");
    }
//    public static synchronized LazySingleton getInstance()
//    {
//        if (instance == null)
//        {
//            instance = new LazySingleton();
//        }
//        return instance;
//    }
    // double-checked locking
    public static LazySingleton getInstance()
    {
        if (instance == null)
        {
            synchronized (LazySingleton.class)
            {
                if (instance == null)
                {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
class InnerStaticSingleton
{
    private InnerStaticSingleton(){}
    private static class Impl
    {
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }
    public InnerStaticSingleton getInstance()
    {
        return Impl.INSTANCE;
    }
}

enum EnumBasedSingleton
{
    INSTANCE;

    EnumBasedSingleton()
    {
        value = 42;
    }

    private int value;

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }
}

// Mono-state
// We can create many instances we want,
// but its variables just reference to static fields.
class ChiefExecuteOfficer
{
    private static String name;
    private static int age;

    public void setAge(int age)
    {
        ChiefExecuteOfficer.age = age;
    }

    public void setName(String name)
    {
        ChiefExecuteOfficer.name = name;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    @Override
    public String toString()
    {
        return "ChiefExecuteOfficer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
class Demo2
{
    static void saveToFile(EnumBasedSingleton singleton, String filename)
        throws Exception
    {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            out.writeObject(singleton);
        }
    }

    static EnumBasedSingleton readFromFile(String filename)
         throws Exception
    {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn))
        {
            return (EnumBasedSingleton)in.readObject();
        }
    }

    public static void main(String[] args) throws Exception
    {
        String filename = "myfile.bin";
        EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
        singleton.setValue(111);
        saveToFile(singleton, filename);
        EnumBasedSingleton singleton1 = readFromFile(filename);
        System.out.println(singleton1.getValue());
    }
}