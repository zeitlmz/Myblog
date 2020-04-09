package com.blogmanger.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blogmanger.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * (Article)表服务接口
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
public interface ArticleService extends IService<Article> {
    List<Article> selectArticleByauthorid(Integer authorid);

    Map<String, Object> selectArticleByPage(Integer page, Integer limit);

    boolean addArticle(Article article);
}