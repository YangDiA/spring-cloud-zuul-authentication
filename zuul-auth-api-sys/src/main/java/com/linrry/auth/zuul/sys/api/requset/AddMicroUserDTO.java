package com.linrry.auth.zuul.sys.api.requset;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 *
 * @author Linrry
 * @date 2018-09-30 下午 15:19
 */
public class AddMicroUserDTO implements Serializable {


    @NotBlank
    private String phone;

    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private  String roleName;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
