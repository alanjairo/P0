package com.revature.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private int userId;
    private String username;
    private String password;

    public User(){}

    public User(int userId, String username, String password)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public int getUserId(){return userId;}
    public void setUserId(int userId){this.userId = userId;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return username;
    }


}
