package com.lyyco.rays.service.crawler;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * com.lyyco.rays.service.crawler
 *
 * @Author liyangyang
 * 2018/3/26
 */
@Component("JobInfoDaoPipeline")
public class JobInfoDaoPipeline implements PageModelPipeline<LieTouJobInfo>{

    @Resource
    private JobInfoDAO jobInfoDAO;

    public void process(LieTouJobInfo lieTouJobInfo, Task task){
        jobInfoDAO.add(lieTouJobInfo);
    }

}
