package com.chenqi.community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author Ardai
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post {
    private Integer id;
    private String title;
    private String content;
    private Date createTime;
    private Integer view;
    private Integer comments;
    private String postImg;
    private Integer userId;
}
