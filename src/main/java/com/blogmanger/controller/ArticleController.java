package com.blogmanger.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.blogmanger.entity.Article;
import com.blogmanger.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Article)表控制层
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
@RestController
@RequestMapping("article")
public class ArticleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;

    @RequestMapping("selectArticleByauthorid")
    public List<Article> selectArticleByauthorid(Integer authorid) {
        System.out.println("authorid:" + authorid);
        List<Article> list = articleService.selectArticleByauthorid(authorid);
        return list;
    }

    @RequestMapping("getArticleByPage")
    public Map<String, Object> getArticleByPage(Integer page, Integer limit) {
        return articleService.selectArticleByPage(page, limit);
    }

    @PostMapping("addArticle")
    @ResponseBody
    public boolean addArticle(@RequestBody Article article) {
//        System.out.printf(article.getAtitle()+"\n"+article.getAuthorid()+"\n"+article.getAcontent()+"\n"+article.getAclass()+"\n"+article.getImgpath());
        return articleService.addArticle(article);
    }
}