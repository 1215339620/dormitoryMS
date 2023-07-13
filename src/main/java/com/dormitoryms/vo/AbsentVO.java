package com.dormitoryms.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AbsentVO {
    private Integer id;
    private String buildingName;
    private String dormitoryName;
    private String studentName;
    private String time;
    private String remarks;
}
