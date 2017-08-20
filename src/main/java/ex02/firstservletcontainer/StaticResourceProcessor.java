package ex02.firstservletcontainer;

import java.io.IOException;

/**
 * Created by robin on 2017/8/20.
 */
public class StaticResourceProcessor {
    //单例模式，饿加载
    private static StaticResourceProcessor instance=new StaticResourceProcessor();
    public static StaticResourceProcessor getInstance(){
        return instance;
    }
    private StaticResourceProcessor(){};
    public void process(Request req,Response resp) throws IOException {
        resp.setRequest(req);
        resp.sendStaticHttpResponse();
    }
}
