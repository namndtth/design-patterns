// Two goals:
// Compose nested structures of objects
// Deal with the classes for these objects uniformly.

// Step 1: Design the interface that defines the overall type

import java.lang.reflect.Array;
import java.util.ArrayList;

interface IStructure
{
    public void enter();
    public void exit();
    public void location();
    public String getName();
}

// Step 2: Implement the composite class.
class Housing implements IStructure
{
    private ArrayList<IStructure> structures;
    private String address;
    public Housing(String address)
    {
        this.structures = new ArrayList<IStructure>();
        this.address = address;
    }

    public int addStructure(IStructure component)
    {
        this.structures.add(component);
        return this.structures.size() - 1;
    }

    public IStructure getStructure(int componentNumber)
    {
        return this.structures.get(componentNumber);
    }
    @Override
    public void enter() {}

    @Override
    public void exit() {}

    @Override
    public void location()
    {
        System.out.println("You are currently in " + this.getName() + ". It has");
        for (IStructure struct: this.structures)
            System.out.println(struct.getName());
    }

    @Override
    public String getName() {
        return this.address;
    }
}

// Step 3: Implement the leaf class
class Room implements IStructure
{
    public String name;

    public Room(String name)
    {
        this.name = name;
    }
    @Override
    public void enter()
    {
        System.out.println("You have entered the " + this.name);
    }

    @Override
    public void exit()
    {
        System.out.println("You have exited the " + this.name);
    }

    @Override
    public void location()
    {
        System.out.println("You have currently in the " + this.name);
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}

// Step 4: construct our building structure by structure
class Program
{
    public static void main(String[] args)
    {
        Housing building = new Housing("123 Street");
        Housing floor1 = new Housing("123 Street - first floor");
        int firstFloor = building.addStructure(floor1);

        Room washroom1m = new Room("1F Men's Washroom");
        Room washroom1w = new Room("1F Women's Washroom");
        Room common1 = new Room("1F Common Area");

        int firstMens = floor1.addStructure(washroom1m);
        int firstWomans = floor1.addStructure(washroom1w);
        int firstCommon = floor1.addStructure(common1);

        building.enter();
        Housing currentFloor = (Housing) building.getStructure(firstFloor);
        currentFloor.enter();
        Room currentRoom = (Room) currentFloor.getStructure(firstMens);
        currentRoom.enter();
        currentRoom = (Room) currentFloor.getStructure(firstCommon);
        currentRoom.enter();
    }
}