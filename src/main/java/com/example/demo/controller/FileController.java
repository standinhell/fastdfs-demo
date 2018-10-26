package com.example.demo.controller;

import com.example.demo.dao.ArticleMapper;
import com.example.demo.domian.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FileController {

    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @GetMapping("/filelist")
    public String filelist(){
        return "filelist";
    }

    @GetMapping("/platformlist")
    @ResponseBody
    public List<Article> findPlatform(){
        return articleMapper.findPlatform();
    }

    @GetMapping("/articlelist")
    @ResponseBody
    public List<Article> findArticle(@RequestParam("platform") String platform){
        return articleMapper.findArticle(platform);
    }
}
