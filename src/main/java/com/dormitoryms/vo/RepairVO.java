package com.dormitoryms.vo;

import lombok.Data;

@Data
public class RepairVO {
    private Integer id;
    private String buildingName;
    private String dormitoryName;
    private String studentName;
    private String time;
    private String content;
    private Integer status;
}
