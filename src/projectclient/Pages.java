package projectclient;

import java.net.Socket;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Scanner;
public class Pages {
ClientSockets sockets;
    public Pages(Socket socket) {
       sockets = new ClientSockets(socket); 
    }

    /**
     * seng massage to another account
     * @param name personal chat
     */
    public void personal_Chat(String name) {
        try {
                 JSONObject json1 = new JSONObject();
                 json1.put("methode", "FriendList");
                 json1.put("Username", name);
                 this.sockets.sendMassage(json1);
                 JSONObject json2 = this.sockets.getMassage();
                 int i = 0;
                 int p = 1;
                    if (json2.getString("methode").equals("FriendList")) {
                 while(i == 0){
                     if (!json2.getString(("FriendName")+p).isBlank()) {
                            System.out.println(json2.getString("FriendName")+p);
                    p++;
                     }else{
                         System.out.println("NO Friend");
                         i+=1;
                     }
                   }
                }
        }catch(Exception e) {
            System.out.println("End...");
        }
        
////////////////----------------------------------------------------------->>>>>
        try {
        Scanner scanner = new Scanner(System.in);
        System.out.println("<<Enter the name of a friend or one who you want to chat >>");
        System.out.println("Enter <Exit> to get out ");    
        String name1 = scanner.nextLine();
        if (!name1.equals("Exite")) {
        JSONObject json5 = new JSONObject();
        json5.put("methode", "getmassage");
        json5.put("Username", name);
        json5.put("FriendName", name1);
        sockets.sendMassage(json5);
        chat(name,name1);
        }
    }catch(Exception e){
        System.out.println("cannot send massage");
    }
    }

    /**
     * send massages
     * @param name sender
     * @param name1 reciever
     */
    public void chat(String name,String name1){
        Scanner scanner = new Scanner(System.in);
            int i = 0;
            while (i == 0) {
                JSONObject json = new JSONObject();
                System.out.println("Enter your massage");
                System.out.println("Enter Exite to get out");
                String massage = scanner.nextLine();
                if (!name.equals("Exit") && !massage.equals("Exit")) {
                    json.put("methode", "SendMassage");
                    json.put("Username", name1);
                    json.put("FriendName", name);
                    json.put("massage", massage);
                
                }   
                if (!json.isEmpty()){
                         sockets.sendMassage(json);
                         JSONObject json1 = this.sockets.getMassage();
                         if (json1.getString("Answer").equals("True")) {
                             System.out.println("sended");
                         }
                    }else{
                         i++;
                     }
                }
    }

    /**
     * send massage to group
     * @param name sender
     */
    public void GroupChat(String name) {
        Scanner scan = new Scanner(System.in);
        JSONObject json = new JSONObject();
        json.put("methode", "MyGroups");
        json.put("Username", name);
        sockets.sendMassage(json);
        int n = 0;
        while (n == 0) {
            JSONObject json1 = sockets.getMassage();
            if (json1.getString("methode").equals("MyGroups")) {
                System.out.println(json1.getString("GroupName"));
            }
            else{
                n+=1;
            }
        }
        //------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        System.out.println("<<Enter the name of the Group you want to see>>");
        System.out.println("<< To get out Enter Exite>>");
        String groupname = scan.nextLine();
        if (!groupname.equals("Exite")) {
            JSONObject json2 = new JSONObject();
            json2.put("methode", "Showgroupmassage");
            json2.put("GroupName", groupname);
            sockets.sendMassage(json2);
            int i = 0;
            while (i == 0) {
                
                JSONObject json3 = sockets.getMassage();
                if (json3.getString("methode").equals("Showgroupmassage")) {
                System.out.println(json3.getString("Sended")+":"+json3.getString("massage"));
                }
            }
          }
          
        System.out.println("<<Enter your massage>>");
        System.out.println("<Enter 'Exite' to get out>");
        String massage = "";
        while (massage.equals("Exite")) {
        massage = scan.nextLine();
        JSONObject json4 = new JSONObject();
        json4.put("methode", "GroupMassage");
        json4.put("GroupName", groupname);
        json4.put("Username", name);
        json4.put("massage", massage);
        sockets.sendMassage(json4);
        }
    }
    
//    public void view_profile() {
//
//    }
    
}
