package com.blogmanger.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blogmanger.dao.ArticleDao;
import com.blogmanger.entity.Article;
import com.blogmanger.service.ArticleService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Article)表服务实现类
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {

    @Override
    public List<Article> selectArticleByauthorid(Integer authorid) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("authorid", authorid);
        return getBaseMapper().selectList(wrapper);
    }

    @Override
    public Map<String, Object> selectArticleByPage(Integer page, Integer limit) {
        Page page1 = new Page(page, limit);
        IPage ipage = getBaseMapper().selectPage(page1, null);
        Map<String, Object> map = new HashMap<>();
        map.put("count", ipage.getTotal());
        map.put("current", ipage.getCurrent());
        map.put("size", ipage.getSize());
        map.put("data", ipage.getRecords());
        return map;
    }

    @Override
    public boolean addArticle(Article article) {
        File file = new File("D:/blogmanger/src/main/webpage/authorid" + article.getAuthorid() + "/" + article.getAtitle() + ".md");
        String acontent = article.getAcontent();
        try {
            FileOutputStream fos = new FileOutputStream(file, false);
            fos.write(acontent.getBytes("UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        article.setAcontent("D:/blogmanger/src/main/webpage/authorid" + article.getAuthorid() + "/" + article.getAtitle() + ".md");
        return getBaseMapper().insert(article) > 0;
    }


}