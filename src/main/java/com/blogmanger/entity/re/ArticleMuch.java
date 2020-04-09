package com.blogmanger.entity.re;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleMuch extends Model<ArticleMuch> {
    //文章标题
    private String atitle;
    //作者id
    private String author;
    //发布时间
    private Timestamp auptime;
    //分类
    private String aclass;
    //文章内容
    private String acontent;

    @Override
    public String toString() {
        return "ArticleMuch{" +
                "atitle='" + atitle + '\'' +
                ", author=" + author +
                ", auptime=" + auptime +
                ", aclass='" + aclass + '\'' +
                ", acontent='" + acontent + '\'' +
                '}';
    }

}