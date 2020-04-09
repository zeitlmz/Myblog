package com.blogmanger.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.blogmanger.entity.re.ArticleMuch;
import com.blogmanger.service.ArticleMuchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Article)表控制层
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
@RestController
@RequestMapping("articleMuch")
public class ArticleMuchController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleMuchService articleMuchService;



    @RequestMapping("selectArticleMuchByauthorid")
    public R selectArticleByauthorid(Integer authorid) {
        List<ArticleMuch> list = articleMuchService.selectArticleMuchByauthorid(authorid);
        return success(list);
    }

    @RequestMapping("selectArticleMuchByAtitle")
    public ArticleMuch selectArticleByAtitle(String atitle) {
        System.out.println("atitle:" + atitle);
        ArticleMuch articleMuch = articleMuchService.selectArticleMuchByAtitle(atitle);
        return articleMuch;
    }

    @RequestMapping("selectArticleByAid")
    public ArticleMuch selectArticleByAid(Integer aid) {
        System.out.println("aid:" + aid);
        ArticleMuch articleMuch = articleMuchService.selectArticleMuchByAid(aid);
        return articleMuch;
    }
}