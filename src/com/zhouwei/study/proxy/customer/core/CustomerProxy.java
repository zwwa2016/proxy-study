package com.zhouwei.study.proxy.customer.core;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 自定义的代理类
 * 生成代理对象的代码
 * @author zhouwei
 * @create 2019-04-20
 */
public class CustomerProxy {

    private static String ln = "\r\n";

    public static Object newProxyInstance(CustomerClassLoader loader,
                                          Class<?>[] interfaces,
                                          CustomerInvocationHandler h){
        //1.生成源代码
        String proxySrc = generateSrc(interfaces[0]);
        //2.输出到磁盘
        String filePath = CustomerProxy.class.getResource("").getPath();
        File f = new File(filePath + "$Proxy0.java");
        try(FileWriter fw = new FileWriter(f)){
            fw.write(proxySrc);
            fw.flush();
            fw.close();

            //3.编译
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manager.getJavaFileObjects(f);
            JavaCompiler.CompilationTask task = compiler.getTask(null,manager,null,null,null,iterable);
            task.call();
            manager.close();

            //4.加载到JVM，返回代理对象
            Class  classProxy = loader.findClass("$Proxy0");
            Constructor c  = classProxy.getConstructor(CustomerInvocationHandler.class);
            f.delete();
            return c.newInstance(h);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 生成java源代码
     * @param interfaces
     * @return
     */
    public static String generateSrc(Class<?> interfaces){
        StringBuffer src = new StringBuffer();
        src.append("package com.zhouwei.study.proxy.customer.core;" + ln);
        src.append("import java.lang.reflect.Method;" + ln);
        src.append("public class $Proxy0 implements " + interfaces.getName() + "{" +ln);
        src.append("CustomerInvocationHandler h;" + ln);
        src.append("public $Proxy0(CustomerInvocationHandler h){" +ln);
        src.append("this.h = h;"+ln);
        src.append("}"+ln);
        for(Method m : interfaces.getMethods()){
            src.append("public " + m.getReturnType().getName() + " " + m.getName() +"(){" +ln);
            src.append("try{" +ln);
            src.append("Method m = " +interfaces.getName() + ".class.getMethod(\""+ m.getName()+"\",new Class[]{});" +ln);
            src.append("this.h.invoke(this,m,null);" + ln);
            src.append("}catch(Throwable e){ e.printStackTrace();}" + ln);
            src.append("}" + ln);
        }
        src.append("}");
        return src.toString();
    }
}
