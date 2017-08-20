package ex02.classloadertest;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by robin on 2017/8/20.
 */
public class ClassLoaderTest {
    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //匿名内部类
        ClassLoader myLoader = new ClassLoader() {
            @Override
            protected Class loadClass(String class_name, boolean resolve)
                    throws ClassNotFoundException {
                try {
                    String fileName = class_name.substring(class_name.lastIndexOf(".") + 1) + ".class";
                    System.out.println("----filename---:" + fileName);
                    InputStream is = this.getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        System.out.println("---is == null -----");
                        return super.loadClass(class_name, resolve);
                    }
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(class_name,bytes,0,bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        Object obj=myLoader.loadClass("ex02.classloadertest.Student").newInstance(); //自定义类加载器
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ex02.classloadertest.Student); //系统类加载器
//        class java.lang.Object
//        true
    }
}
