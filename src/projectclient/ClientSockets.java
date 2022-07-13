package projectclient;
import java.net.*;
import java.io.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.json.JSONObject;
public class ClientSockets {
    // socket for client
    private Socket socket;
    // reader for client
    private DataInputStream reader;
    // writer for client
    private DataOutputStream writer;
   // json
    JSONObject json;
    public ClientSockets(Socket socket) {
        this.json = new JSONObject();     
        try {
             
             this.socket=socket;
             this.reader=new DataInputStream(socket.getInputStream());
             this.writer=new DataOutputStream(socket.getOutputStream());
             
         }catch(IOException e) {
             e.printStackTrace();
         }
    }

    /**
     * send massages
      * @param json for information
     */
    public void sendMassage (JSONObject json) {
        try{
            writer.writeUTF(json.toString());
            writer.flush();
        }catch(IOException e) { 
            e.printStackTrace();
        } 
    }

    /**
     * get massages
     * @return jason
     */
    public JSONObject getMassage() { 
        try {
        String  massage = (String) reader.readUTF();
        JSONObject json1 = new JSONObject(massage);
        return json1;
        }catch(IOException e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        return json;
    }
}
