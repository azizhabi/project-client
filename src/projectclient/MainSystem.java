package projectclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.*;
import java.io.*;
import org.json.JSONObject;

/**
 * class MainSystem
 */
public class MainSystem {
    ///name of System
    private String name;
    // socket
    private Socket socket;
    /// reader of
    private BufferedReader reader;
    ///  write
    private BufferedWriter writer;
    private ClientSockets clientsockets;
    private TheServer server;
    Pages page;

//    private ArrayList<CreatAccount> clients = file.account();

    /**
     * @param socket socket of server
     * @throws IOException
     */
    public MainSystem(Socket socket) throws IOException {
 
         page = new Pages(socket);
         server = new TheServer(socket);
         try {
            clientsockets = new ClientSockets(socket);
            this.socket=socket;
            this.reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
           // Thread thread=new Thread(this);
           // thread.start();
            //this.SendMessage(userName);
        }catch(IOException ex) {
            reader.close();
            writer.close();
            socket.close();
        }
    
    }

    /**
     * client of server
     * @throws InterruptedException if the problem happened
     */
    public void client() throws InterruptedException {
    Scanner scan = new Scanner(System.in);
    System.out.println("1->Sign in\n2->Sign up\n3->Exit");
    int n = scan.nextInt();
    switch (n) {
        case 1:
            SignIn();
            break;
        case 2:
            SignUp();
            break;
        case 3:
            break;
    }
        
    }

    /**
     * Sign in to System
     * @throws InterruptedException if the problem happened
     * */
    private void SignIn() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name : ");
        name = scanner.nextLine();
        System.out.print("Password : ");
        String password = scanner.nextLine();
        System.out.print("Email : ");
        String Email = scanner.nextLine();
        int i =0;
        while (i == 0) {
        if (!Email.endsWith("@gmail.com")) {
            System.out.println("Email is ont correct enter again");
            Email = scanner.nextLine();
           }
        else if (Email.endsWith("@gmail.com")) {
            i++;
        }
        }
        System.out.print("phoneNumber : ");
        String phoneNumber = scanner.nextLine();
        JSONObject json = new JSONObject();
        json.put("methode", "CreatAccount");
        json.put("Username", name);
        json.put("password", password);
        json.put("phoneNumber", phoneNumber);
        json.put("Email", Email);
        this.clientsockets.sendMassage(json);
        if (this.clientsockets.getMassage().getString("Answer").equals("True")){
            System.out.println("<<your Account Created seccusfully >>");
        }else {
            System.out.println("<<Sorry here is some problem with your account Try again");
            SignIn();
        }
        
        account();
        
        
     }

    /**
     * Sign up to System
     * @throws InterruptedException
     */
    private void SignUp() throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    Scanner scan = new Scanner(System.in);
    JSONObject json = new JSONObject();
    System.out.print("name : ");
    name = scanner.nextLine();
    System.out.print("password : ");
    String password = scanner.nextLine();
        json.put("methode", "CheckUser");
        json.put("Username", name);
        json.put("password", password);
        this.clientsockets.sendMassage(json);
        if (this.clientsockets.getMassage().getString("Answer").equals("True")) {
            System.out.println("<<Welcome to your Account>>");
            account();
        }
        else{
        System.out.println("you already don't have an account");
        System.out.println("1->sign in\n2->Exit");
        int n = scan.nextInt();
        if (n == 1){
         SignIn();   
        }
        else {
            return;
        }
        }
    }

    /**
     * Accoutn of system
     * @throws InterruptedException
     */
    private void account() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        int k = 0;
        while (k == 0) {
        System.out.println("1->Friend List\n2->Friend Request \n3->Personal Chat\n4->Server\n5->Answer Request\n6->Exite");
        int n = scanner.nextInt();
        switch (n) {
            case 1:
                
                 
                break;
            case 2:
                System.out.println("<<Enter the Name of your friend>>");
                String friend_name = scan.nextLine();
                JSONObject json3  = new JSONObject();
                json3.put("methode", "FriendRequest");
                json3.put("Username", name);
                json3.put("FriendName", friend_name);
                
                this.clientsockets.sendMassage(json3);
                
                JSONObject json8 = this.clientsockets.getMassage();
                
                if (!json8.isEmpty()){ 
                if (json8.getString("methode").equals("FriendRequest")) {
                
                    if (json8.getString("Answer").equals("True")) {
                        System.out.println("Request Sended");
                    }
                    else {
                        System.out.println("here is some problem with the name of your friend");
                        break;
                    }
                }else{
                    System.out.println("Not Sended");
                }
               }else {
                        System.out.println("Not Sended");
               
                    }
                break;
            case 3:
                page.personal_Chat(name);
                break;
            case 4:
                server();
                break;
            case 5:
                try{
                JSONObject json = new JSONObject();
                json.put("methode", "ShowRequest");
                json.put("Username", name);
                this.clientsockets.sendMassage(json);
                JSONObject json4 = this.clientsockets.getMassage();
                int g = 0;
                while (g == 0) {
                System.out.println("cloro1");
                    if (json4.getString("methode").equals("ShowRequest")) {
                        int l =0;
                        int j = 1;
                        while  (l == 0) {
                        if (!json4.getString(("Username")+j).isBlank()){
                            System.out.println(json4.getString("Username"+j));
                        j++;
                        }else{
                            l+=1;
                        }
                     }
                    }
                    else {
                        System.out.println("cloro3");
                        g+=1;
                        break;
                    }
                }
                }catch(Exception e) {
                    System.out.println("End...");
                }
                System.out.println(" Do you want to answer <YES / NO>");
                String answer = scan.nextLine();
                if (answer.equals("YES")) {
                    System.out.println("Enter the name of the one you want to accept");
                    String answer_name = scan.nextLine();
                    JSONObject json5 = new JSONObject();
                    json5.put("methode", "AnswerRequest");
                    json5.put("Username", name);
                    json5.put("FriendName", answer_name);
                    json5.put("Answer", "Accept");
                    this.clientsockets.sendMassage(json5);
                    JSONObject json6 = this.clientsockets.getMassage();
                    if (json6.getString("methode").equals("AnswerRequest")) {
                        if (json6.getString("Answer").equals("True")) {
                            System.out.println("Done");
                            break;
                        }
                        break;
                    }
                    break;
                }
                break;
            case 6:
            k+=1;
            }
        
    }
    }

    /**
     * show friend list
     */
    private void friendList() {
        Scanner scan = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        try {
                JSONObject json1 = new JSONObject();
                 json1.put("methode", "FriendList");
                 json1.put("Username", name);
                 this.clientsockets.sendMassage(json1);
                 JSONObject json2 = this.clientsockets.getMassage();
                 int i = 0;
                 int p = 0;
                    if (json2.getString("methode").equals("FriendList")) {
                 while(i == 0){ 
                    p++;
                     if (!json2.getString(("FriendName")+p).isBlank()) {
                     System.out.println(json2.getString("FriendName")+p);
                    
                     }else{
                         i+=1;
                     }
                   }
                }
                }catch(Exception e) {
                    System.out.println("End...");
                }
        System.out.println("you want to chose a friend");
        System.out.println("YES\nNO");
        String chose = scan.nextLine();
        if (chose.equals("YES")) {
            System.out.println("Enter the Name of your Friend ");
            System.out.println("<Exit> -> to get out");
            String friend = scan.nextLine();
            int f = 0;
            while (f == 0) {
                System.out.println("1->chat\n2->see profile\n3->Exite");
                int m = scanner.nextInt();
                switch(m) {
                    case 1:
                        page.chat(friend, name);
                        break;
                    case 2:
                        server.profile(friend, name);
                        break;
                    case 3:
                        f+=1;
                        break;
                }
            }
        }
        }

    /**
     * Server site
     */
    private void server() {
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        System.out.println("1->Creat Server\n2->Open Server");
        int n = scanner.nextInt();
        switch(n) {
            case 1:
                creatServer();
                break;
            case 2:
                System.out.println("Enter Server Name");
                String server_name = scan.nextLine();
                System.out.println("Enter your name");
                String user_name = scan.nextLine();
                JSONObject json1 = new JSONObject();
                json1.put("methode", "CheckServerUser");
                json1.put("ServerName", server_name);
                json1.put("ServerUser", user_name);
                clientsockets.sendMassage(json1);
                JSONObject json = clientsockets.getMassage();
                if (json.getString("methode").equals("CheckServerUser")) {
                    if (json.getString("Answer").equals("True")) {
                        openserver(server_name);
                    }
                    else{
                        System.out.println("You are not a member of this Server");
                    }
                    
                }
        }
        
    }

    /**
     * create server for client
     */
    private void creatServer() {
       Scanner scan = new Scanner(System.in);
       System.out.print("Server Name : ");
       String server_name = scan.nextLine();
       JSONObject json = new JSONObject();
       json.put("methode", "CreatServer");
       json.put("ServerCreater", this.name);
       json.put("ServerName", server_name);
       this.clientsockets.sendMassage(json);
       System.out.println("alis1");
       JSONObject json1 = this.clientsockets.getMassage();
       System.out.println("alis2");
       if (json1.getString("Answer").equals("True")) {
           System.out.println("Server Created");
       }
       else {
           System.out.println("There is some problem with creating server");
       }
       
    }

    /**
     * Open server for client
     * @param server_name
     */
    private void openserver(String server_name) {
        JSONObject json = new JSONObject();
        json.put("methode", "UserSatuation");
        json.put("ServerName", server_name);
        json.put("Usernmae", name);
        this.clientsockets.sendMassage(json);
        JSONObject json1 = this.clientsockets.getMassage();
        if (json1.getString("methode").equals("UserSatuation")) {
            switch (json1.getString("ability")) {
                case "UserDeleter":
                    server.userDeleter(server_name, name);
                    break;
                case "groupcreater":
                    server.groupcreater(server_name, name);
                    break;
                case "massagepiner":
                    server.massagepiner(server_name, name);
                    break;
                case "groupdeleter":
                    server.groupdeleter(server_name, name);
                    break;
                case "Admin":
                    server.admin(server_name, name);
                    break;
                case "OnlyMember":
                    server.onlymember(server_name, name);
                    break;
            }
        }
    }
}
