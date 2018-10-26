package com.example.demo.dao;

import com.example.demo.domian.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select("select distinct platform from article")
    List<Article> findPlatform();

    @Select("select id,name from article where platform=#{platform}")
    List<Article> findArticle(String platform);

}
