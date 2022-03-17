package com.wdw.maj.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;    //账户名
    private String accountId;   //账户id
    private String token;   //账户随机编码
    private Long gmtCreate; //账户创建时间
    private Long gmtModified;   //账户读入时间
    private String avatarUrl;
}
