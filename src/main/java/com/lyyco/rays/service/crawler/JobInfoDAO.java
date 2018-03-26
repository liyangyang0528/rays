package com.lyyco.rays.service.crawler;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Created by lyy
 * @Date: Created in 11:22 2018/3/26
 */
public interface JobInfoDAO {

    @Insert("insert into JobInfo (`title`,`salary`,`company`,`description`,`source`,`url`,`urlMd5`) values (#{title},#{salary},#{company},#{description},#{source},#{url},#{urlMd5})")
    public int add(LieTouJobInfo jobInfo);
}
