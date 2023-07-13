package com.dormitoryms.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StudentForm {
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

    @JsonProperty("dId")
    private Integer dId;
    private Integer oldDId;
}
