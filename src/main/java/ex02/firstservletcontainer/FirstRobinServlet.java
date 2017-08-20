package ex02.firstservletcontainer;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by robin on 2017/8/20.
 */
public class FirstRobinServlet implements Servlet {
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("robinservlet   init");
    }
    public ServletConfig getServletConfig() {
        return null;
    }
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {
        String reply="hello ,robin,i am servlet";
        PrintWriter printWriter=servletResponse.getWriter();
        printWriter.write(reply);
        printWriter.flush();
    }
    public String getServletInfo() {
        return null;
    }
    public void destroy() {
        System.out.println("robinservlet destory");
    }
}


