package com.sndj.recipe.ognl;

import java.util.HashMap;
import java.util.Map;

import ognl.Ognl;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * http://blog.csdn.net/mmm123lmj/article/details/4527898
 *
 * @author Administrator
 */
public class OgnlTest extends TestCase {

    /*获得类属性，以及使用root getValue*/
    public void testOgnl_01() throws Exception {
        User user = new User();
        user.setUsername("dirk");

        String value = (String) Ognl.getValue("username", user);
        System.out.println(value);
    }

    public void testOgnl_02() throws Exception {
        User user = new User();
        Person p = new Person();
        p.setUsername("dallas");
        user.setPerson(p);

        String value = (String) Ognl.getValue("person.username", user);
        System.out.println(value);
    }


    public void testOgnl_03() throws Exception {
        User user = new User();
        Person p = new Person();
        p.setUsername("dallas");
        user.setPerson(p);

        String value = (String) Ognl.getValue("#root.person.username", user);
        System.out.println(value);
    }

    public static void main(String[] args) throws Exception {
        new OgnlTest().testOgnl_01();
    }

    //----------------------------------------------------  
    //OGNL CONTEXT使用·  
    public void testOgnl_04() throws Exception {
        Person p1 = new Person();
        Person p2 = new Person();
        p1.setUsername("dirk");
        p2.setUsername("terri");

        Map<String, Person> context = new HashMap<String, Person>();
        context.put("p1", p1);
        context.put("p2", p2);

        String value = (String) Ognl.getValue("#p1.username + ',' +#p2.username", context, new Object());
        System.out.println(value);
    }

    public void testOgnl_05() throws Exception {
        Person p1 = new Person();
        Person p2 = new Person();
        p1.setUsername("风");
        p2.setUsername("云");

        Map<String, Person> context = new HashMap<String, Person>();
        context.put("p1", p1);
        context.put("p2", p2);

        User root = new User();
        root.setUsername("雨");

        String value = (String) Ognl.getValue("#p1.username + ',' + #p2.username + ',' + username", context, root);
        System.out.println(value);
    }

    //-------------------------------------
    //not important : OGNL赋值操作   java的method反射 get set方法
    public void testOgnl_06() throws Exception {
        User user = new User();

        //给root对象的属性赋值，相当于调用user.setUsername()
        Ognl.setValue("username", user, "暗之幻影");

        System.out.println(user.getUsername());
    }


    public void testOgnl_07() throws Exception {
        User user = new User();

        Map<String, User> context = new HashMap<String, User>();
        context.put("u", user);

        //给context中的对象属性赋值，相当于调用user.setUsername()
        Ognl.setValue("#u.username", context, new Object(), "暗之幻影");

        System.out.println(user.getUsername());
    }

    public void testOgnl_08() throws Exception {
        User user = new User();

        Map<String, User> context = new HashMap<String, User>();
        context.put("u", user);

        //给context中的对象属性赋值，相当于调用user.setUsername()
        //在表达式中使用=赋值操作符来赋值
        Ognl.getValue("#u.username = '暗之幻影'", context, new Object());

        System.out.println(user.getUsername());
    }

    public void testOgnl_09() throws Exception {
        User user = new User();
        Person p = new Person();

        Map<String, Object> context = new HashMap<String, Object>();
        context.put("u", user);
        context.put("p", p);

        //给context中的对象属性赋值，相当于调用user.setUsername()
        //在表达式中使用=赋值操作符来赋值
        Ognl.getValue("#u.username = '风',#p.username = '云'", context, new Object());

        System.out.println(user.getUsername() + "," + p.getUsername());
    }

    //--------------------------------------
    //not important 使用OGNL调用对象的方法
    public void testOgnl_10() throws Exception {
        User user = new User();
        user.setUsername("暗之幻影");

        String value = (String) Ognl.getValue("getUsername()", user);
        System.out.println(value);
    }

    public void testOgnl_11() throws Exception {
        User user = new User();

        Ognl.getValue("setUsername('暗之幻影')", user);
        System.out.println(user.getUsername());
    }

    @Test
    public void testaa() {
        String str = "中";
        // 4e2d
        String asciiResult = stringToAscii(str);
        System.out.println(asciiResult);
        String stringResult = asciiToString(asciiResult);
        System.out.println(stringResult);
        System.out.println(Integer.toHexString(20013));
    }
    public static String stringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append((int)chars[i]).append(",");
            }
            else {
                sbu.append((int)chars[i]);
            }
        }
        return sbu.toString();
    }

    public static String asciiToString(String value)
    {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }
}  