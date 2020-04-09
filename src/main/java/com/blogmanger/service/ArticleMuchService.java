package com.blogmanger.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blogmanger.entity.Article;
import com.blogmanger.entity.re.ArticleMuch;

import java.util.List;

/**
 * (Article)表服务接口
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
public interface ArticleMuchService extends IService<ArticleMuch> {
    ArticleMuch selectArticleMuchByAid(Integer aid);

    List<ArticleMuch> selectArticleMuchByauthorid(Integer authorid);

    ArticleMuch selectArticleMuchByAtitle(String atitle);
}