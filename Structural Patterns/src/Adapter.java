//// The adapter design pattern will help facilitate communication
//// between two existing systems by providing a compatible interface.
//
//import javax.jws.WebService;
//
//// Step 1: Design the target interface
//interface WebRequester
//{
//    public int request(Object object);
//}
//
//// Step 2: Implement the target interface with the adapter class
//
//class WebAdapter implements WebRequester
//{
//    private WebService service;
//
//    public void connect(WebService currentService)
//    {
//        this.service = currentService;
//    }
//
//    public int request(Object request)
//    {
//        Json result = this.toJson(request);
//        Json response = service.request(result);
//        if (response != null)
//            return 200; // OK status code
//        return 500; // Server error status code
//    }
//
//    private Json toJson(Object input)
//    {
//        return null;
//    }
//}
//
//// Step 3: send the request from the client to the adapter using the target interface
//class WebClient
//{
//    private WebRequester webRequester;
//    public WebClient(WebRequester webRequester)
//    {
//        this.webRequester = webRequester;
//    }
//
//    private Object makeObject() // make an object
//    {
//        return null;
//    }
//    public void doWork()
//    {
//        Object object = makeObject();
//        int status = webRequester.request(object);
//        if (status == 200)
//            System.out.println("OK");
//        else
//            System.out.println("NOT OK");
//
//        return;
//    }
//}
//
//class Program
//{
//    public static void main(String[] args) {
//        String webHost = "google.com.vn";
//        WebService service = new WebService(webHost);
//        WebAdapter adapter = new WebAdapter();
//        adapter.connect(service);
//        WebClient client = new WebClient(adapter);
//        client.doWork();
//    }
//}