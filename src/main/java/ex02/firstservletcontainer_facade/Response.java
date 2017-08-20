package ex02.firstservletcontainer_facade;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * Created by robin on 2017/8/20.
 */

public class Response implements ServletResponse {
    private String ROOT_PATH=System.getProperty("user.dir") + File.separator  + "webroot";
    private OutputStream outputStream;
    private Request request;
    public Response(OutputStream outputStream){
        this.outputStream=outputStream;
    }
    public void setRequest(Request request){
        this.request=request;
    }


    public void sendStaticHttpResponse() throws IOException {
        File file=new File(ROOT_PATH,request.getHttpUri());
        if(!file.exists()){
            System.out.println("请求资源不存在："+file.getAbsolutePath());
            return;
        }
        FileInputStream fis=new FileInputStream(file);
        DataOutputStream dos = new DataOutputStream(outputStream);
        int length=0;
        byte[] sendBytes = new byte[1024];
        while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
            dos.write(sendBytes, 0, length);
            dos.flush();
        }
    }


    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return (ServletOutputStream)outputStream;
    }

    public PrintWriter getWriter() throws IOException {
        PrintWriter writer = new PrintWriter(outputStream, true);
        return writer;
    }

    public void setCharacterEncoding(String s) {

    }

    public void setContentLength(int i) {

    }

    public void setContentLengthLong(long l) {

    }

    public void setContentType(String s) {

    }

    public void setBufferSize(int i) {

    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale locale) {

    }

    public Locale getLocale() {
        return null;
    }
}
