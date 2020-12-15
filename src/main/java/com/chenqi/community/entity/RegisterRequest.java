package com.chenqi.community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String gitAddress;
    private String sign;
}
