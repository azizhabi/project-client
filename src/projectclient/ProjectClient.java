package projectclient;

import java.io.IOException;
import java.net.*;
import org.json.JSONObject;
// Project client for server
public class ProjectClient {
    /**
     * this program start the program
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
            
            Socket socket = new Socket("127.0.0.1",5030);
            MainSystem mainsystem = new MainSystem(socket);
            mainsystem.client();
            
            }
}
