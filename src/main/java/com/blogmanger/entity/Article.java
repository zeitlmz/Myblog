package com.blogmanger.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Article)表实体类
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article extends Model<Article> {
    //文章id
    private Long aid;
    //文章标题
    private String atitle;
    //作者id
    private Long authorid;
    //文章内容
    private String acontent;
    //发布时间
    private Date auptime;
    //分类
    private String aclass;
    //图片路径
    private String imgpath;

    @Override
    public String toString() {
        return "Article{" +
                "aid=" + aid +
                ", atitle='" + atitle + '\'' +
                ", authorid=" + authorid +
                ", acontent='" + acontent + '\'' +
                ", auptime=" + auptime +
                ", aclass='" + aclass + '\'' +
                ", imgpath='" + imgpath + '\'' +
                '}';
    }
}