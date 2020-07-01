import java.io.*;

// SINGLETON
// A component which is instantiated only once.
class BasicSingleton implements Serializable
{
    private BasicSingleton()
    {

    }

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    public static BasicSingleton getInstance()
    {
        return INSTANCE;
    }

    private int value = 0;

    public void setValue(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    protected Object readResolve()
    {
        return INSTANCE;
    }
}

// There are two problems with this implementation
// 1. Reflections
// 2. Serialization
class Demo1
{
    static void saveToFile(BasicSingleton singleton, String fileName) throws Exception
    {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            out.writeObject(singleton);
        }
    }
    static BasicSingleton readFromFile(String filename) throws Exception
    {
        try (FileInputStream fileIn= new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn))
        {
            return (BasicSingleton) in.readObject();
        }
    }
    public static void main(String[] args) throws Exception
    {
        BasicSingleton instance = BasicSingleton.getInstance();
        instance.setValue(123);

        String filename = "singleton.bin";
        saveToFile(instance, filename);
        instance.setValue(222);
        BasicSingleton instance2 = readFromFile(filename);
        System.out.println(instance == instance2);
        System.out.println(instance.getValue());
        System.out.println(instance2.getValue());
    }
}