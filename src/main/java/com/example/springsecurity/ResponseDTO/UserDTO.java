package com.example.springsecurity.ResponseDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotBlank(message = "Username is required!")
    @Size(min = 3, message = "Username must have atleast 3 characters!")
    @Size(max = 10, message = "Username can have almost 10 characters!")
    private String userName;

    @NotBlank(message = "Password is required!")
    private String password;

    @Email(message = "Email is not valid format!")
    @NotBlank(message = "Email is required!")
    private String emailId;

    @NotBlank(message = "Mobile number is required!")
    @Size(min = 10, max = 10, message = "Mobile number must have 10 characters!")
    @Pattern(regexp = "^[0-9]*$", message = "Mobile number must contains only digits!")
    private String mobileNo;

    public UserDTO() {
    }

    public UserDTO(String userName, String password, String emailId, String mobileNo) {
        this.userName = userName;
        this.password = password;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", emailId='" + emailId + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}
