package zdc.cc.test;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {

    public static void main(String[] args) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try {
            Class clazz = loader.loadClass("zdc.cc.test.PrivateCar");

            PrivateCar pcar = (PrivateCar) clazz.newInstance();

            Field colorFld = clazz.getDeclaredField("color");
            colorFld.setAccessible(true);
            colorFld.set(pcar,"黄色");


            Method driveMtd = clazz.getDeclaredMethod("drive",null);
            driveMtd.setAccessible(true);
            driveMtd.invoke(pcar, null);

            String filePath = "D:/workSpace/attackDouyu/src/main/resources/application.properties";
            WritableResource writableResource = new PathResource(filePath);

            Resource resource = new ClassPathResource("application.properties");
            InputStream inputStream1 = writableResource.getInputStream();
            InputStream inputStream = resource.getInputStream();
            OutputStream outputStream = writableResource.getOutputStream();
            int i ;
            while ((i=inputStream.read())!=-1){
                outputStream.write(i);
            }
            System.out.println(outputStream.toString());
//            outputStream.write("welcome".getBytes());

            outputStream.close();

            ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
