package com.blogmanger.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blogmanger.entity.re.ArticleMuch;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Article)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
public interface ArticleMuchDao extends BaseMapper<ArticleMuch> {

    /**
     * 通过作者id获取该作者的所有文章
     *
     * @param authorid
     * @return
     */
    @Select("select ar.atitle,au.author,ar.auptime,ar.aclass,ar.acontent " +
            "from article ar,autor au where ar.authorid=au.authorid " +
            "and ar.authorid=#{authorid}")
    List<ArticleMuch> selectArticleMuchByauthorid(Integer authorid);

    /**
     * 通过文字id获取文章
     * @param aid 文章id
     * @return 返回ArticleMuch对象
     */
    @Select("select ar.atitle,au.author,ar.auptime,ar.aclass,ar.acontent " +
            "from article ar,autor au where ar.authorid=au.authorid " +
            "and ar.aid=#{aid}")
    ArticleMuch selectArticleMuchByAid(Integer aid);

    /**
     * 通过文章标题获取整篇
     * @param atitle 文章标题
     * @return 返回ArticleMuch对象
     */
    @Select("select ar.atitle,au.author,ar.auptime,ar.aclass,ar.acontent " +
            "from article ar,autor au where ar.authorid=au.authorid " +
            "and ar.atitle=#{atitle}")
    ArticleMuch selectArticleMuchByAtitle(String atitle);
}