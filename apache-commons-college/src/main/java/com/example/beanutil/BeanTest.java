package com.example.beanutil;

import org.apache.commons.beanutils.BeanUtils;
import java.util.HashMap;
import java.util.Map;

public class BeanTest {

    public static void main(String[] args) throws Exception{
        Person person = new Person();
        person.setAge(1);
        person.setName("2");
        person.setEmail("3");
        System.out.println(person);

        //1.克隆一个bean
        Person person1 = (Person) BeanUtils.cloneBean(person);
        System.out.println(person1.getEmail());


        //2.将Map转换为一个Bean
        Map map = new HashMap();
        map.put("name","tom");
        map.put("email","tom");
        map.put("age",22);
        BeanUtils.populate(person,map);
        System.out.println(person.toString());


        //3.将Bean转换为一个Map
        Map<String, String> describe = BeanUtils.describe(person);
        System.out.println(describe);
    }




    public static class Person {
        private String name;
        private String email;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
