package com.many.reader.task;

import com.many.reader.service.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 完成自动计算任务
 */
@Component
public class ComputeTask {
    @Resource
    private BookService bookService;
    //在每分钟0秒执行 定时调度（任务调度）
    @Scheduled(cron = "0 0 * * * ?")
    public void updateEvaluation(){
        bookService.updateEvaluation();
    }
}
