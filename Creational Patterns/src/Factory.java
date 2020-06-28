// Goal: create objects
// Benefits:
// If there are multiple clients that want to instantiate the same set of
// classes, then by using a Factory object, you have cut out redundant
// code and made the software easier to modify.

// Factory method
// The factory method design intent is to to define an interface for creating objects,
// but the subclasses decide which class to instantiate.
class Knife
{
    void sharpen(){}
    void polish(){}
    void pk(){}
}
class SteakKnife extends Knife {}
class ChefsKnife extends Knife {}

class BudgetChefsKnife extends Knife {}
class BudgetSteakKnife extends Knife {}

class KnifeFactory
{
    public  Knife createKnife(String knifeType)
    {
        Knife knife = null;

        // create Knife object
        if (knifeType.equals("steak"))
            knife = new SteakKnife();
        else if (knifeType.equals("chefs"))
            knife = new ChefsKnife();

        return knife;
    }
}
// we cannot instantiate KnifeStore, instead, we'll need to have knife store sub-classes.
abstract class KnifeStore
{
    private KnifeFactory factory;
    //
    public abstract Knife orderKnife(String knifeType);
}

class BudgetKnifeStore extends KnifeStore
{
    Knife createKnife(String knifeType)
    {
        if (knifeType.equals("steak"))
            return new BudgetSteakKnife();
        else if (knifeType.equals("chefs"))
            return new BudgetChefsKnife();

        else return null;
    }

    @Override
    public Knife orderKnife(String knifeType) {
        return null;
    }
}