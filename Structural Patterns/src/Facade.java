// The facade design pattern provides a single simplified interface
// for client classes to interact with subsystem.

// A facade simply acts as a point of entry into your system.
// What a facade does is exactly what a waiter or salesperson would do in real life.

// A facade is a wrapper class that encapsulates a subsystem
// in order to hide the subsystem's complexity.

import java.math.BigDecimal;
import java.util.Hashtable;

// How to do that ?
// Example is bank account
// Step 1: Design the interface
interface IAccount
{
    public void deposit(BigDecimal amount);
    public void withdraw(BigDecimal amount);
    public void transfer(IAccount toAccount, BigDecimal amount);
    public int getAccountNumber();
}

// Step 2: Implement the interface with one or more classes
class Chequing implements IAccount
{
    public Chequing(BigDecimal amount) {}
    @Override
    public void deposit(BigDecimal amount) { }

    @Override
    public void withdraw(BigDecimal amount) { }

    @Override
    public void transfer(IAccount toAccount, BigDecimal amount) { }

    @Override
    public int getAccountNumber() {return 0;}
}

 class Saving implements IAccount
 {
     public Saving(BigDecimal amount) {}
     @Override
     public void deposit(BigDecimal amount) {}

     @Override
     public void withdraw(BigDecimal amount) {}

     @Override
     public void transfer(IAccount toAccount, BigDecimal amount) {}

     @Override
     public int getAccountNumber() {return 0;}
 }
 class Investment implements IAccount
 {
     public Investment(BigDecimal amount) {}
     @Override
     public void deposit(BigDecimal amount) {}

     @Override
     public void withdraw(BigDecimal amount) {}

     @Override
     public void transfer(IAccount toAccount, BigDecimal amount) {}

     @Override
     public int getAccountNumber() {return 0;}
 }

// Step 3: Create the facade class and wrap the classes that implements the interface
class BankService
{
    private Hashtable<Integer, IAccount> bankAccounts;
    public BankService()
    {
        this.bankAccounts = new Hashtable<Integer, IAccount>();
    }

    public int createNewAccount(String type, BigDecimal initAmount)
    {
        IAccount newAccount = null;
        switch (type)
        {
            case "chequing":
                newAccount = new Chequing(initAmount);
                break;
            case "saving":
                newAccount = new Saving(initAmount);
                break;
            case "investment":
                newAccount = new Investment(initAmount);
                break;
            default:
                System.out.println("Invalid account type");
                break;
        }
        if (newAccount != null)
        {
            this.bankAccounts.put(newAccount.getAccountNumber(), newAccount);
            return newAccount.getAccountNumber();
        }
        return -1;
    }
    public void transferMoney(int to, int from, BigDecimal amount)
    {
        IAccount toAccount = this.bankAccounts.get(to);
        IAccount fromAccount = this.bankAccounts.get(from);
        fromAccount.transfer(toAccount, amount);
    }
}

// Step 4: Use the facade class to access the subsystem.
class Customer
{
    public static void main()
    {
        BankService myBankService = new BankService();
        int mySaving = myBankService.createNewAccount("saving", new BigDecimal(500.00));
        int myInvestment = myBankService.createNewAccount("investment", new BigDecimal(1000.00));
        myBankService.transferMoney(mySaving, myInvestment, new BigDecimal(300.00));
    }
}