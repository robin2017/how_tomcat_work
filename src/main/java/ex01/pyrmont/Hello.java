package ex01.pyrmont;

import java.io.*;
import java.net.Socket;

/**
 * Created by robin on 2017/8/17.
 */
public class Hello {
    public void test() throws IOException, InterruptedException {


        Socket socket=new Socket("127.0.0.1",80);
        OutputStream os=socket.getOutputStream();
        boolean autoflush=true;
        PrintWriter out=new PrintWriter(socket.getOutputStream(),autoflush);
        BufferedReader in=new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
        out.println("GET /index.jsp HTTP/1.1");
        out.println("Host:localhost:8080");
        out.println("Connection:Close");
        out.println();
        boolean loop=true;
        StringBuffer sb=new StringBuffer(8096);
        while(loop){
            if(in.ready()){
                int i=0;
                while (i!=-1){
                    i=in.read();
                    sb.append((char)i);
                }
                loop=false;
            }
            Thread.currentThread().sleep(50);
        }
        socket.close();


    }
}
