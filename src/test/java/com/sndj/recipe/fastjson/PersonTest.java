package com.sndj.recipe.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextValueFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class PersonTest {

    private List<Person> listOfPersons = new ArrayList<Person>();

    @Before
    public void setUp() {
        listOfPersons.add(new Person(15, "John Doe", new Date()));
        listOfPersons.add(new Person(20, "Janette Doe", new Date()));

    }

    @Test
    public void whenJavaList_thanConvertToJsonCorrect() {
//        String jsonOutput= JSON.toJSONString(listOfPersons);
//        System.out.println(jsonOutput);
        // [{"AGE":15,"DATE OF BIRTH":1522465120438,"FULL NAME":"John Doe"},
        // {"AGE":20,"DATE OF BIRTH":1522465120438,"FULL NAME":"Janette Doe"}]


        //[{"DATE OF BIRTH":"31/03/18","FULL NAME":"John Doe"},{"DATE OF BIRTH":"31/03/18","FULL NAME":"Janette Doe"}]

        String jsonOutput = JSON.toJSONString(listOfPersons, SerializerFeature.BeanToArray);
        System.out.println(jsonOutput);
        //[[15,1522468084237,"John Doe"],[20,1522468084237,"Janette Doe"]]
    }

    @Test
    public void whenGenerateJson_thanGenerationCorrect() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 2; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("AGE", 10);
            jsonObject.put("FULL NAME", "Doe " + i);
            jsonObject.put("DATE OF BIRTH", "2016/12/12 12:12:12");
            jsonArray.add(jsonObject);
        }
        String jsonOutput = jsonArray.toJSONString();
        System.out.println(jsonOutput);
    }

    @Test
    public void whenJson_thanConvertToObjectCorrect() {
        Person person = new Person(20, "John Doe", new Date());
        String jsonObject = JSON.toJSONString(person);
        Person newPerson = JSON.parseObject(jsonObject, Person.class);

        assertEquals(newPerson.getAge(), 0); // if we set serialize to false
        assertEquals(newPerson.getFullName(), listOfPersons.get(0).getFullName());
    }


    @Test
    public void givenContextFilter_whenJavaObject_thanJsonCorrect() {
        ContextValueFilter valueFilter = new ContextValueFilter() {
            public Object process(BeanContext context, Object object, String name, Object value) {
                if (name.equals("DATE OF BIRTH")) {
                    return "NOT TO DISCLOSE";
                }
                if (value.equals("John Doe")) {
                    return ((String) value).toUpperCase();
                } else {
                    return null;
                }
            }
        };
        String jsonOutput = JSON.toJSONString(listOfPersons, valueFilter);
        System.out.println(jsonOutput);
    }

}
