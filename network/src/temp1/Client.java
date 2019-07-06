package temp1;
 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
 
/**
 *客户端
 */
public class Client {
	  public static void main(String [] args) throws UnknownHostException  
      {  
         InetAddress serverName = InetAddress.getLocalHost();//这是ip,the ipaddr of server
         //there is my localhost addr
         int port = 5666;//这是端口  
         try  
         {  
           Socket client = null;
           while (true) {  
            client = new Socket(serverName, port); 
            System.out.println("请输入命令:");  
            Scanner sc=new Scanner(System.in);
            String cmdStr = sc.nextLine();  
            OutputStream outToServer = client.getOutputStream();  
            DataOutputStream out = new DataOutputStream(outToServer);  
            out.writeUTF("\n");  
            out.writeUTF(cmdStr);  
            client.shutdownOutput();  
            InputStream inFromServer = client.getInputStream();  
            DataInputStream in = new DataInputStream(inFromServer);  
            System.out.println(" 读取服务器返回" + in.readUTF());  
            client.close();  
           }  
         }catch(IOException e)  
         {  
            e.printStackTrace();  
         }  
      } 
}
