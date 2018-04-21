package com.sndj.recipe.ognl;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import ognl.Ognl;
import ognl.OgnlException;

public class TestOngl extends TestCase {
//     类中的第一方法为，testOgnlSimpleProperty,演示了如何用Ognl从一个简单对象中取属性值。代码如下：

    public void testOgnlSimpleProperty() {
        User user = new User();
        user.setName("abc");
        try {
            Object result = Ognl.getValue("name", user);//********(1)
            System.out.println("***简单属性**********result::" + result);
            //输出：***简单属性**********result::abc
            Company com = new Company();
            com.setCompanyName("某某科技有限公司");
            user.setCompany(com);

            result = Ognl.getValue("company.companyName", user);//*******(2)
            System.out.println("***嵌套属性**********result::" + result);
            //输出：***嵌套属性**********result::某某科技有限公司
        } catch (OgnlException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void testFromMap() throws OgnlException {
        Map distObject = new HashMap();
        distObject.put("abc", "10000");
        Object result = Ognl.getValue("abc", distObject);//********(1)

        System.out.println("***从Map中取简单属性**********result::" + result);
        //输出：***从Map中取简单属性**********result::10000
        User user = new User();
        user.setName("myName");
        distObject.put("user", user);
        result = Ognl.getValue("user.name", distObject);//********(2)
        System.out.println("***从Map中取嵌套属性**********result::" + result);
        //输出***从Map中取嵌套属性**********result::myName
    }

    // Ognl.getValue(expression, context, reqData[0])
    public void testSayHello() throws OgnlException {
        User user = new User();
        user.setUsername("java");
        String aa = (String) Ognl.getValue("@com.sndj.recipe.ognl.User@sayHello(#root.username)", new HashMap(), user);
        System.out.println(aa);
    }
}