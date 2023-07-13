package com.dormitoryms.form;

import lombok.Data;

@Data
public class ChangeForm {
    private Integer id;
    private String oldPass;
    private String newPass;
    private String checkPass;
}
