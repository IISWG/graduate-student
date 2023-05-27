package com.example.graduatestudent.entity.param;

import lombok.Data;

@Data
public class MailVerifyParam {
    private String newname;
    private String nickname;
    private String oldpassword;
    private String email;
    private String password;
    private String password2;
    private String code;
}
