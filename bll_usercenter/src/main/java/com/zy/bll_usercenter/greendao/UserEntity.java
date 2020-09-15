package com.zy.bll_usercenter.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author:zhangyue
 * @date:2020/9/15
 */
@Entity
public class UserEntity {
    @Id(autoincrement = true)
    private long id;

    private String username;
    private String sex;
    private int age;
    @Generated(hash = 976152957)
    public UserEntity(long id, String username, String sex, int age) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.age = age;
    }

    public UserEntity(String username, String sex, int age) {
        this.username = username;
        this.sex = sex;
        this.age = age;
    }

    @Generated(hash = 1433178141)
    public UserEntity() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
