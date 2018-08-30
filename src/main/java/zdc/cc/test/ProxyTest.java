package zdc.cc.test;

import zdc.cc.domain.Person;
import zdc.cc.domain.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {

        Person zhuzhu = new Student("zhuzhu");

        Person niuniu = new Student("niuniu");

        InvocationHandler ih = new StuInvocationHandler<Person>(zhuzhu);

        InvocationHandler ih1 = new StuInvocationHandler<Person>(niuniu);

        Person stuProxy = (Person)Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, ih);

        Person stuProxy1 = (Person)Proxy.newProxyInstance(Person.class.getClassLoader(), niuniu.getClass().getInterfaces(), ih1);
        stuProxy.giveMoney();
        stuProxy1.giveMoney();
    }
}
