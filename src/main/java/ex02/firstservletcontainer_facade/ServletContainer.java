package ex02.firstservletcontainer_facade;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by robin on 2017/8/20.
 */
public class ServletContainer {
    private static Executor executor= Executors.newFixedThreadPool(5);
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(8080);
        System.out.println("服务器启动，等待连接");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("获得连接");
            executor.execute(new SocketHandler(socket));
        }
    }
}

class SocketHandler implements Runnable{
    private Socket socket;
    SocketHandler(Socket socket){
        this.socket=socket;
    }
    public void run() {
        try {
            //请求响应直接对字节流进行处理的，不是字符流
            //来了请求创建request对象和response对象
            System.out.println("SOCKET处理线程正在运行");
            Request request=new Request(socket.getInputStream());
            Response response=new Response(socket.getOutputStream());
            request.parseHttpRequest();
            String uri=request.getHttpUri();
            if(!uri.startsWith("/servlet")){
                //静态资源
                System.out.println("----处理静态请求----");
                StaticResourceProcessor.getInstance().process(request,response);
            }else{
                //动态资源
                System.out.println("---动态资源---");
                ServletProcessor.getInstance().process(request,response);
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
