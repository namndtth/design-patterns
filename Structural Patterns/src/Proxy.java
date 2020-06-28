//// The proxy acts as a simplified or lightweight version of the original object.
//
//// Proxy class wraps the real subject class
//// Real object is a part of a system.
//// It may contain sensitive information or resource intensive to instantiate.
//
//// Why we use:
//// 1, To act as a virtual proxy where the proxy class is used in place a real subject class
//// that is resource intensive to instantiate
//// 2, To act as a protection proxy in order to control access to the real subject class.
//// 3, To act as a remote proxy.
//
//
//// How to implement:
//// Step 1: Design the subject interface

import java.util.ArrayList;
import java.util.List;

interface Internet
{
    public void connectTo(String serverHost) throws Exception;
}
//// Step 2: Implement the real subject class

class RealInternet implements Internet
{
    @Override
    public void connectTo(String serverHost) throws Exception
    {
        System.out.println("Connecting to " + serverHost);
    }
}
//// Step 3: Implement the proxy class

class ProxyInternet implements Internet
{
    private Internet internet = new RealInternet();
    private static List<String> bannedSites;

    static
    {
        bannedSites = new ArrayList<String>();
        bannedSites.add("abc.com");
        bannedSites.add("def.com");
        bannedSites.add("ijk.com");
        bannedSites.add("lnm.com");
    }

    @Override
    public void connectTo(String serverHost) throws Exception
    {
        if (bannedSites.contains(serverHost.toLowerCase()))
            throw new Exception("Access denied");
        internet.connectTo(serverHost);
    }
}

class Client1
{
    public static void main(String[] args)
    {
        Internet internet = new ProxyInternet();
        try
        {
            internet.connectTo("geeksforgeeks.org");
            internet.connectTo("abc.com");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}