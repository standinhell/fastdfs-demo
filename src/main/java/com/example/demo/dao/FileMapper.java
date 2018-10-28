package com.example.demo.dao;

import com.example.demo.domian.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    @Insert("insert into file(file_path,pid,ptable) values(#{filePath},#{pid},#{ptable})")
    Integer saveFile(File file1);
}
