package com.cevier.test;

import com.alibaba.fastjson.JSONObject;
import com.cevier.test.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Test;

public class JsonTest {

    @Test
    public void test1() {
        User u = new User("jack", "male", 12);

        System.out.println("JackSON");
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jackSonStr = mapper.writeValueAsString(u);
            System.out.println(jackSonStr);
            User u1 = mapper.readValue(jackSonStr, User.class);
            System.out.println(u1);
        } catch (Exception e) {
            System.out.println("oops");
        }

        System.out.println("\nFastJSON1");
        try {
            String fastJsonStr = JSONObject.toJSONString(u);
            System.out.println(fastJsonStr);
            User u2 = JSONObject.parseObject(fastJsonStr, User.class);
            System.out.println(u2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nGSON");
        try {
            Gson gson = new Gson();
            String gsonStr = gson.toJson(u);
            System.out.println(gsonStr);
            User u3 = gson.fromJson(gsonStr, User.class);
            System.out.println(u3);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
