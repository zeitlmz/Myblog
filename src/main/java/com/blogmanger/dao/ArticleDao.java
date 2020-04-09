package com.blogmanger.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blogmanger.entity.Article;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Article)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
public interface ArticleDao extends BaseMapper<Article> {
}