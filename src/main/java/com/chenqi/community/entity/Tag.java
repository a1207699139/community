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
public class Tag {
    private Integer id;
    private String name;
    private Date createTime;
}
