package zdc.cc.test;

import zdc.cc.domain.Person;
import zdc.cc.domain.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProxyTest {

    public static void main(String[] args) {


        Map<String,Integer> map = new HashMap();

        int postRelType = map.get("post_rel_type")==null?0:map.get("post_rel_type");
        int postRelId = map.get("post_red_id")==null?0:map.get("post_red_id");

        if(2111==postRelType&&postRelId!=0){

        }

        Object o = map.get("test") == null ? 0 : map.get("test");
        Person zhuzhu = new Student("zhuzhu");

        Person niuniu = new Student("niuniu");

        InvocationHandler ih = new StuInvocationHandler<Person>(zhuzhu);

        InvocationHandler ih1 = new StuInvocationHandler<Person>(niuniu);

        Person stuProxy = (Person)Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, ih);

        Person stuProxy1 = (Person)Proxy.newProxyInstance(Person.class.getClassLoader(), niuniu.getClass().getInterfaces(), ih1);
        niuniu.giveMoney();
        stuProxy.giveMoney();
        stuProxy1.giveMoney();
    }
}
