package pojo;

import java.util.Date;

public class User {

    private Integer id;
    private String username;
    private String password;
    private String phoneNumber;
    
    public Date getRegistrationTime() {
        return registrationTime;
    }
    
    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }
    
    private Date registrationTime;
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
