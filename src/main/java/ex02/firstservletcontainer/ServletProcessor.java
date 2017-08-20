package ex02.firstservletcontainer;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by robin on 2017/8/20.
 */
public class ServletProcessor {
    private static final String repository="file:"+System.getProperty("user.dir") + File.separator  + "webroot/";
    private Servlet servlet=null;
    private String servletName=null;
    private URLClassLoader loader = null;
    private static ServletProcessor servletProcessor=new ServletProcessor();
    private ServletProcessor(){}
    public static ServletProcessor getInstance(){
        return servletProcessor;
    }

    public void process(Request req,Response resp) {
        try {
            String uri = req.getHttpUri();
            servletName = uri.substring(uri.lastIndexOf("/") + 1);
            System.out.println("servlet名字为:" + servletName);
            loader = new URLClassLoader(new URL[]{new URL(repository)}, Thread.currentThread().getContextClassLoader());
            Class c = loader.loadClass(servletName);
            servlet = (Servlet) c.newInstance();
            System.out.println("repository:==="+repository);
            System.out.println(c==null);

            servlet.service(req, resp);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
