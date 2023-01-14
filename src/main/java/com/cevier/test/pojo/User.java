package com.cevier.test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private String gender;

    private Integer age;

    public String getLivingState() {
        if (age == null) {
            return "unknown";
        }
        return "alive";
    }

//    public void setLivingState(String state) {
//
//    }
}
