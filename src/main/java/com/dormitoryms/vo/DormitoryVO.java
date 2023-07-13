package com.dormitoryms.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DormitoryVO {
    private Integer id;
    @JsonProperty("bName")
    private String bName;
    private String name;
    private Integer type;
    private Integer available;
}
