package com.example.demo.dao;

import com.example.demo.domian.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select("select distinct platform from article")
    List<Article> findPlatform();

    @Select("select id,name from article where platform=#{platform}")
    List<Article> findArticle(String platform);

    @Select("select * from article where platform=#{platform} and name=#{name}")
    Article getArticle(@Param("name") String name, @Param("platform") String platform);

    @Insert("insert into article(name,platform) values(#{name},#{platform})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    Integer saveArticle(Article article);
}
