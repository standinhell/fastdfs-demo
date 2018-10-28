package com.example.demo.controller;

import com.example.demo.dao.ArticleMapper;
import com.example.demo.dao.FileMapper;
import com.example.demo.domian.Article;
import com.example.demo.fdfs.FDSFUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class FileController {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private FileMapper fileMapper;

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

    @PostMapping("uploadfile")
    @ResponseBody
    public String uploadFile(String platform, String name, MultipartFile file) throws IOException {
        Article article = articleMapper.getArticle(name, platform);
        if (null==article){
            article.setName(name);
            article.setPlatform(platform);
            articleMapper.saveArticle(article);
        }
        String fileName = UUID.randomUUID().toString();
        String suffix = file.getOriginalFilename().split("\\.")[1];
        File upFile = File.createTempFile(fileName,suffix);
        file.transferTo(upFile);
        String filePath = FDSFUtil.uploadFile(upFile, fileName + "." + suffix);
        com.example.demo.domian.File file1 = new com.example.demo.domian.File();
        file1.setFilePath(filePath);
        file1.setPid(article.getId());
        file1.setPtable("article");
        Integer i = fileMapper.saveFile(file1);
        if(i==1){
            return filePath;
        }else {
            return "error";
        }

    }
}
