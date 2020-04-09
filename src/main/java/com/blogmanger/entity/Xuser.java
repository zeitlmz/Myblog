package com.blogmanger.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (Xuser)表实体类
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
@SuppressWarnings("serial")
public class Xuser extends Model<Xuser> {
    //作者id
    private Long authorid;
    //账号
    private String username;
    //密码
    private String password;

    @Override
    public String toString() {
        return "Xuser{" +
                "authorid=" + authorid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}