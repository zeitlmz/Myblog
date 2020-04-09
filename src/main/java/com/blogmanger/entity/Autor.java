package com.blogmanger.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Autor)表实体类
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Autor extends Model<Autor> {
    //作者id
    private Long authorid;
    //作者名字
    private String author;
    //年龄
    private Integer age;
    //头像图片路径
    private String tximg;
    //住址
    private String address;
    //积分
    private Long score;

    @Override
    public String toString() {
        return "Autor{" +
                "authorid=" + authorid +
                ", author='" + author + '\'' +
                ", age=" + age +
                ", tximg='" + tximg + '\'' +
                ", address='" + address + '\'' +
                ", score=" + score +
                '}';
    }
}