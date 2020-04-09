package com.blogmanger.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blogmanger.dao.ArticleMuchDao;
import com.blogmanger.entity.re.ArticleMuch;
import com.blogmanger.service.ArticleMuchService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * (Article)表服务实现类
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
@Service("ArticleMuchService")
public class ArticleMuchServiceImpl extends ServiceImpl<ArticleMuchDao, ArticleMuch> implements ArticleMuchService {

    @Override
    public ArticleMuch selectArticleMuchByAid(Integer aid) {
        ArticleMuch articleMuch=getBaseMapper().selectArticleMuchByAid(aid);
        try {
            FileReader reader = new FileReader(articleMuch.getAcontent());
            StringBuffer sb=new StringBuffer();
            char[] world=new char[8];
            int a = reader.read(world);
            while (a!=-1){
                sb.append(world);
                a=reader.read(world);
            }
            articleMuch.setAcontent(new String(sb));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articleMuch;
    }

    @Override
    public List<ArticleMuch> selectArticleMuchByauthorid(Integer authorid) {
        List<ArticleMuch> list=getBaseMapper().selectArticleMuchByauthorid(authorid);
        return list;
    }

    @Override
    public ArticleMuch selectArticleMuchByAtitle(String atitle) {
        ArticleMuch articleMuch=getBaseMapper().selectArticleMuchByAtitle(atitle);
        try {
            FileReader reader = new FileReader(articleMuch.getAcontent());
            StringBuffer sb=new StringBuffer();
            char[] world=new char[8];
            int a = reader.read(world);
            while (a!=-1){
                sb.append(world);
                a=reader.read(world);
            }
            articleMuch.setAcontent(new String(sb));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articleMuch;
    }
}