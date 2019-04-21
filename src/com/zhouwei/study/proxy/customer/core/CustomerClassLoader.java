package com.zhouwei.study.proxy.customer.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 自定义的ClassLoader
 * 代码生成、编译重新动态load到JVM中
 * @author zhouwei
 * @create 2019-04-20
 */
public class CustomerClassLoader extends ClassLoader{

    private File baseDir;

    public CustomerClassLoader(){
        String filePath = CustomerClassLoader.class.getResource("").getPath();
        this.baseDir = new File(filePath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = CustomerClassLoader.class.getPackage().getName() + "." + name;
        if(baseDir != null){
            File classFile = new File(baseDir,name.replaceAll("\\.","/")+".class");
            if(classFile.exists()){
                try(FileInputStream in = new FileInputStream(classFile);
                    ByteArrayOutputStream out = new ByteArrayOutputStream()){
                    byte[] buff = new byte[1024];
                    int len = in.read(buff);
                    while(len != -1){
                        out.write(buff,0,len);
                        len = in.read(buff);
                    }

                    return defineClass(className,out.toByteArray(),0,out.size());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    classFile.delete();
                }
            }
        }
        return null;
    }
}
