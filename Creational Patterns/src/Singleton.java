// Goal: Having only one instance of a class.
// The Singleton pattern:
// Enforces one and only one object of a Singleton class.
// Has the Singleton object globally accessible
public class Singleton // Lazy construction
{
    // The class variable is null if no instance is instantiated

    private static  Singleton uniqueInstance = null;
    private Singleton() {}

    public static Singleton getInstance()
    {
        if (uniqueInstance == null)
            uniqueInstance = new Singleton();
        return  uniqueInstance;
    }
}
