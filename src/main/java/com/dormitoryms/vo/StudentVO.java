package com.dormitoryms.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StudentVO {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String nation;
    private String phone;
    private String department;
    private String major;
    private String classes;
    private String dName;
}
