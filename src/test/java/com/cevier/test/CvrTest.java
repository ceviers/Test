package com.cevier.test;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Map;

public class CvrTest {

    @Test
    public void test() {
        String s = "{\n" +
                "    \"data\": {\n" +
                "        \"RASS_0307_327611e86a556e68fabb32c33adf2ad1\": {\n" +
                "            \"0160bddc0e8f51732500608cbbcbfa90\": \"successed_online\",\n" +
                "            \"0160bddc0e8f51732500608cbbcbfa90\": \"successed_offline\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"errCode\": 0,\n" +
                "    \"errMsg\": \"success\"\n" +
                "}";

        Complex c = JSONObject.parseObject(s, Complex.class);
        System.out.println(c.getHidden());
    }
}

class Complex {
    private Map<String, Map<String, String>> data;
    private Integer errCode;
    private String errMsg;

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getHidden() {
        for (Map<String, String> value : this.data.values()) {
            for (String s : value.values()) {
                return s;
            }
        }
        return "";
    }
}
