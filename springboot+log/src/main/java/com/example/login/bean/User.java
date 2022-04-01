package com.example.login.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String id;
    private String name;
    private String password;

}
