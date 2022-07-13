package projectclient;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import org.json.JSONObject;
public class TheServer {
    private ClientSockets clientsockets;
    public TheServer(Socket socket)  {
        this.clientsockets = new ClientSockets(socket);
    }

    /**
     * delete a user
     * @param server_name
     * @param username
     */
    public void userDeleter(String server_name, String username) {
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        int i = 0;
        while (i == 0){
        System.out.println("1->Groups\n2->Delete User3->Exite");
        int a = scanner.nextInt();
        switch(a) {
            case 1:
                try{
                JSONObject json4 = new JSONObject();
                json4.put("methode", "Server");
                json4.put("Job", "ShowGroups");
                json4.put("ServerName", server_name);
                json4.put("Username", username);
                this.clientsockets.sendMassage(json4);
                JSONObject json5 = this.clientsockets.getMassage();
                int s = 0;
                int p = 1;
                if (json5.getString("methode").equals("Server")) {
                while (s == 0) {
                  if (!json5.getString(("GroupName")+p).isBlank()){
                    System.out.println(json5.getString("GroupName"));
                    }else {
                        s+=1;
                       }
                }
                }
                System.out.println("<<Enter the name of the group>>");
                System.out.println("<<Enter <Exite> to get out>>");
                String group_name = scan.nextLine();
                if (!group_name.equals("Exite")) {
                    group(server_name, username, group_name);
                }
                }catch(Exception e) {
                    System.out.println("End....");
                }
                break;
                
            case 2:
                JSONObject json = new JSONObject();
                json.put("methode", "ShowServerMember");
                json.put("ServerName", server_name);
                this.clientsockets.sendMassage(json);
                int n = 0;
                 while (n == 0) {
                    JSONObject json1 = this.clientsockets.getMassage();
                    if (json1.getString("methode").equals("ShowServerMember")) {
                        System.out.println(json1.getString("MemberName"));
                    } 
                    else {
                        n+=1;
                    }
                }
                System.out.println("<<Enter the name of User you want to delete>>");
                String user_name = scan.nextLine();
                JSONObject json2 = new JSONObject(); 
                json2.put("methode", "Server");
                json2.put("Job", "UserDeleter");
                json2.put("DeletedName", user_name);
                this.clientsockets.sendMassage(json2);
                JSONObject json3 = this.clientsockets.getMassage();
                if (json3.getString("Answer").equals("True")) {
                    System.out.println(user_name+": Deleted");
                }
                break;
            case 3:
                i+=1;
                break;
        }
    }
        
    }

    /**
     * make group
     * @param server_name
     * @param username
     */
    public void groupcreater (String server_name, String username) {
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        int i = 0;
        while (i == 0) {
        System.out.println("1->Groups\n2->Creat Group\n3->Exite");
        int n = scanner.nextInt();
        switch(n){
            case 1:
                try {
                JSONObject json4 = new JSONObject();
                json4.put("methode", "Server");
                json4.put("Job", "ShowGroups");
                json4.put("ServerName", server_name);
                json4.put("Username", username);
                this.clientsockets.sendMassage(json4);
                JSONObject json5 = this.clientsockets.getMassage();
                int s = 0;
                int p = 1;
                if (json5.getString("methode").equals("Server")) {
                while (s == 0) {
                  if (!json5.getString(("GroupName")+p).isBlank()){
                    System.out.println(json5.getString("GroupName"));
                    }else {
                        s+=1;
                       }
                }
                }
                System.out.println("<<Enter the name of the group>>");
                System.out.println("<<Enter <Exite> to get out>>");
                String group_name = scan.nextLine();
                if (!group_name.equals("Exite")) {
                    group(server_name, username, group_name);
                }
                }catch(Exception e) {
                    System.out.println("End....");
                }
                break;
            case 2:
                System.out.println("<<Enter the Name of the group >>");
                System.out.println("<<Enter <Exite> to get out");
                String group_name1= scan.nextLine();
                JSONObject json2 = new JSONObject();
                json2.put("methode", "CreatGroup");
                json2.put("ServerName", server_name);
                json2.put("GroupName", group_name1);
                json2.put("GroupCreater", username);
                this.clientsockets.sendMassage(json2);
                JSONObject json3 = this.clientsockets.getMassage();
                if (json3.getString("methode").equals("CreatGroup")) {
                    if (json3.getString("Answer").equals("True")) {
                        System.out.println("Group Created seccusfully");
                        group(server_name,username,group_name1);/////////-???????
                    }else {
                        System.out.println("Group does not created");
                    }
                }
                break;
            case 3:
                i+=1;
                break;
        } 
       
        
        }
        
    }

    /**
     * make a group
     * @param server_name
     * @param username
     * @param group_name1
     */
    private void group(String server_name, String username, String group_name1) {
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        JSONObject json6 = new JSONObject();
                    json6.put("methode", "getGroupMassage");
                    json6.put("GroupName", group_name1);
                    int d = 0;
                    while (d == 0) {
                        JSONObject json7 = this.clientsockets.getMassage();
                        if (json7.getString("methode").equals("getGroupMassage")) {
                            System.out.println(json7.getString("Sended")+" : "+json7.getString("massage"));
                            
                        }
                    }
        int i =0;
        while (i == 0) {
        System.out.println("1->massage\n2->Show Members\n3->Exite");
        int n = scanner.nextInt();
        switch(n) {
            case 1:
                int m = 0;
                while (m == 0) {
                System.out.println("<<Enter your massage>>");
                System.out.println("<<Enter <Exite> to get out>>");
                String massage = scan.nextLine();
                JSONObject json = new JSONObject();
                json.put("methode ", "GroupMassage");
                json.put("Username", username);
                json.put("massage", massage);
                this.clientsockets.sendMassage(json);
                }
                break;
            case 2:
                JSONObject json1 = new JSONObject();
                json1.put("methode", "ShowGroupMember");
                json1.put("GroupName", group_name1);
                this.clientsockets.sendMassage(json1);
                int s = 0;
                while (s == 0) {
                    JSONObject json2 = this.clientsockets.getMassage();
                    if (json2.getString("methode").equals("ShowGroupMassage")) {
                        System.out.println(json2.getString("member"));
                        
                }
                }
                        System.out.println("<<Enter the name to see profile>>");
                        System.out.println("<<Enter <Exite> to get out>>");
                        String name = scan.nextLine();
                        if (!name.equals("Exite")) {
                            profile(name,username);
                        }
                break;
            case 3:
                i+=1;
                break;
        }
    }
    }

    /**
     * profile of the user
     * @param name
     * @param username
     */
    public void profile(String name, String username) {
        JSONObject json = new JSONObject();
        json.put("methode", "Showprofile");
        json.put("profileName", name);
        this.clientsockets.sendMassage(json);
        JSONObject json1 = this.clientsockets.getMassage();
        if (json1.getString("methode").equals("Showprifile")) {
            System.out.println("Name : "+ json1.getString("Name"));
            System.out.println("Phone Number : "+ json1.getString("PhoneNumber"));
            System.out.println("Email Address : "+ json1.getString("EmailAddress"));
            System.out.println("State : " +json1.getString("State"));
            
        }
        
    }

    /**
     * pin massage
     * @param server_name
     * @param username
     */
    public void massagepiner(String server_name, String username){
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        
        int i = 0;
        while (i == 0) {
            System.out.println("1->Groups\n2->Pin Massage\n3->Exite");
            int n  = scanner.nextInt();
            switch(n) {
               case 1:
                   try {
                JSONObject json4 = new JSONObject();
                json4.put("methode", "Server");
                json4.put("Job", "ShowGroups");
                json4.put("ServerName", server_name);
                json4.put("Username", username);
                this.clientsockets.sendMassage(json4);
                JSONObject json5 = this.clientsockets.getMassage();
                int s = 0;
                int p = 1;
                if (json5.getString("methode").equals("Server")) {
                while (s == 0) {
                  if (!json5.getString(("GroupName")+p).isBlank()){
                    System.out.println(json5.getString("GroupName"));
                    }else {
                        s+=1;
                       }
                }
                }
                System.out.println("<<Enter the name of the group>>");
                System.out.println("<<Enter <Exite> to get out>>");
                String group_name = scan.nextLine();
                if (!group_name.equals("Exite")) {
                    group(server_name, username, group_name);
                } 
                   }catch(Exception e) {
                       System.out.println("End....");
                   }
                break;
                
                case 2:
                    System.out.println("<<Enter the GroupName");
                    System.out.println("<<Enter <Exite> to get out>>");
                    String group_name1 = scan.nextLine();
                    if (!group_name1.equals("Exite")) {
                    System.out.println("<<Enter your massage>>");
                    String massage = scan.nextLine();
                    if (!massage.equals("Exite")) {
                    JSONObject json = new JSONObject();
                    json.put("methode", "Server");
                    json.put("Job", "Massagepiner");
                    json.put("ServerName",server_name);
                    json.put("GroupName", group_name1);
                    json.put("pinMassage", massage);
                    this.clientsockets.sendMassage(json);
                    JSONObject json1 = this.clientsockets.getMassage();
                    if (json1.getString("methode").equals("Server")) {
                        if (json1.getString("Answer").equals("True")) {
                            System.out.println("Massage pined");
                        }
                        else {
                            System.out.println("here is some problem with pining massages");
                        }
                    }
                    
                    }
                    }
                    break;
                case 3:
                    i+=1;
                    break;
        }
       
        
    }
}

    /**
     * delete a group
     * @param server_name
     * @param username
     */
    public void groupdeleter(String server_name, String username) {
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        int i = 0;
        while (i == 0) {
            System.out.println("1->Groups\n2->Delete Group\n3->Exite");
            int n = scanner.nextInt();
            switch(n) {
               case 1:
                   try {
                JSONObject json4 = new JSONObject();
                json4.put("methode", "Server");
                json4.put("Job", "ShowGroups");
                json4.put("ServerName", server_name);
                json4.put("Username", username);
                this.clientsockets.sendMassage(json4);
                JSONObject json5 = this.clientsockets.getMassage();
                int s = 0;
                int p = 1;
                if (json5.getString("methode").equals("Server")) {
                while (s == 0) {
                  if (!json5.getString(("GroupName")+p).isBlank()){
                    System.out.println(json5.getString("GroupName"));
                    }else {
                        s+=1;
                       }
                }
                }
                System.out.println("<<Enter the name of the group>>");
                System.out.println("<<Enter <Exite> to get out>>");
                String group_name = scan.nextLine();
                if (!group_name.equals("Exite")) {
                    group(server_name, username, group_name);
                } 
                    }catch(Exception e) {
                        System.out.println("End....");
                    }
                break;
                case 2:
                    
                JSONObject json6 = new JSONObject();
                json6.put("methode", "Server");
                json6.put("Job", "ShowGroups");
                json6.put("ServerName", server_name);
                json6.put("Username", username);
                this.clientsockets.sendMassage(json6);
                int f = 0;
                while (f == 0) {
                    JSONObject json7 = this.clientsockets.getMassage();
                    if (json7.getString("Job").equals("ShowGroups")) {
                        System.out.println(json7.getString("GroupName"));
                    }
                    else {
                        f+=1;
                    }
                }
                System.out.println("<<Enter the name of the group>>");
                System.out.println("<<Enter <Exite> to get out");
                String groupname = scan.nextLine();
                JSONObject json = new JSONObject();
                json.put("methode", "Server");
                json.put("Job", "GroupDeleter");
                json.put("GroupName", groupname);
                this.clientsockets.sendMassage(json);
                JSONObject json1 = this.clientsockets.getMassage();
                if (json1.getString("methode").equals("Server")) {
                    if (json1.getString("Answer").equals("True")) {
                        System.out.println("Group Deleted!!");
                    }else {
                        System.out.println("here is some problem with deleting the group");
                    }
                    
                }
                    break;
                case 3: 
                    i+=1;
                    break;
            }
        }
    }

    /**
     *  find group member
     * @param server_name
     * @param username
     */
    public void onlymember(String server_name, String username) {
        Scanner scan = new Scanner (System.in);
        Scanner scanner = new Scanner (System.in);
        int i = 0;
        while (i == 0) {
            System.out.println("1->Groups\n2->Exite");
            int n = scanner.nextInt();
            switch(n) {
               case 1:
                   try {
                JSONObject json4 = new JSONObject();
                json4.put("methode", "Server");
                json4.put("Job", "ShowGroups");
                json4.put("ServerName", server_name);
                json4.put("Username", username);
                this.clientsockets.sendMassage(json4);
                JSONObject json5 = this.clientsockets.getMassage();
                int s = 0;
                int p = 1;
                if (json5.getString("methode").equals("Server")) {
                while (s == 0) {
                  if (!json5.getString(("GroupName")+p).isBlank()){
                    System.out.println(json5.getString("GroupName"));
                    }else {
                        s+=1;
                       }
                }
                }
                System.out.println("<<Enter the name of the group>>");
                System.out.println("<<Enter <Exite> to get out>>");
                String group_name = scan.nextLine();
                if (!group_name.equals("Exite")) {
                    group(server_name, username, group_name);
                }
                   }catch(Exception e) {
                       System.out.println("End....");
                   }
                break;
                case 2:
                    i+=1;
                    break;
            }
        }
    }

    /**
     * exit
     * @param server_name
     * @param username
     */
    public void admin (String server_name, String username) {
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner (System.in);
       int  i =0;
        while (i == 0) {
        System.out.println("1->Groups\n2->Add Role\n3->Exite");
        int n = scanner.nextInt();
        switch(n) {
            case 1:
                try {
                JSONObject json4 = new JSONObject();
                json4.put("methode", "Server");
                json4.put("Job", "ShowGroups");
                json4.put("ServerName", server_name);
                json4.put("Username", username);
                this.clientsockets.sendMassage(json4);
                JSONObject json5 = this.clientsockets.getMassage();
                int s = 0;
                int p = 1;
                if (json5.getString("methode").equals("Server")) {
                while (s == 0) {
                  if (!json5.getString(("GroupName")+p).isBlank()){
                    System.out.println(json5.getString("GroupName"));
                    }else {
                        s+=1;
                       }
                }
                }
                System.out.println("<<Enter the name of the group>>");
                System.out.println("<<Enter <Exite> to get out>>");
                String group_name = scan.nextLine();
                if (!group_name.equals("Exite")) {
                    group(server_name, username, group_name);
                }
                }catch (Exception e) {
                    System.out.println("End....");
                }
                break;        
            case 2:
                addrole(server_name,username);
                break;
            case 3:
                i +=1;
                break;
        }
        
    }
 }
    private void addrole(String server_name, String username) {
        Scanner scan = new Scanner (System.in);
        JSONObject json = new JSONObject();
        json.put("methode", "FriendList");
        json.put("Username", username);
        this.clientsockets.sendMassage(json);
        int i = 0;
        while (i == 0) {
            JSONObject json1 = this.clientsockets.getMassage();
            if (json1.getString("methode").equals("FriendList")) {
                System.out.println(json1.getString("FriendName"));
            }
            else {
                i+=1;
            }
        }
        System.out.print("FriendName :");
        String friend_name = scan.nextLine();
        System.out.println("Role :");
        String role = scan.nextLine();
        JSONObject json1 = new JSONObject();
        json1.put("methode", "AddRole");
        json1.put("Username", friend_name);
        json1.put("ServerName", server_name);
        
    }
    
}
