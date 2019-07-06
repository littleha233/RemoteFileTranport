package temp1;
 
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
 
/**
 *服务端
 */
public class Server extends Thread {
	
	Socket socket ;
	
	//使用该集合是用于存储ip地址的。
	static HashSet<String> ips = new HashSet<String>();
	
	public  Server(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			String ip = socket.getInetAddress().getHostAddress();   // socket.getInetAddress() 获取对方的IP地址
		    DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println(in.readUTF());
            String exeResult = exeCmd(in.readUTF());//这里是第一个 就是输入的cmd
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("返回执行结果： "+exeResult );
            
			socket.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		//建立tcp的服务 ,并且要监听一个端口
		ServerSocket serverSocket  = new ServerSocket(5666);
		System.out.println("等待客户端连接....");
		while(true){
			//不停的接受用户的链接。
			Socket socket = serverSocket.accept();
			new Server(socket).start();
		}
	}
	
	 /**
     * @param commandStr
     * @return 
     * 调用dos命令
     */
    public static String exeCmd(String commandStr) {  
        BufferedReader br = null;  
        try {  
        	StringBuilder sb = new StringBuilder();  
	            Process p = Runtime.getRuntime().exec(commandStr);  
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));  
            String line = null;  
            while ((line = br.readLine()) != null) {  
                sb.append(line + "\n");  
            }  
	         //   System.out.println(sb.toString());  
        	sb.append(commandStr);
           return sb.toString();
        } catch (Exception e) {  
            return null;
        }   
        finally  
        {  
            if (br != null)  
            {  
                try {  
                    br.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
}
