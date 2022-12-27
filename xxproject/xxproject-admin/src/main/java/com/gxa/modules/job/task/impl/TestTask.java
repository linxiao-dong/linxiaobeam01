package com.gxa.modules.job.task.impl;

import com.gxa.modules.job.task.ITask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 示例事务
 */
@Component
@Slf4j
public class TestTask implements ITask {

    @Scheduled(cron = "*/3 * * * * *")
    @Override
    public void run() {
        log.info("TestTask定时任务正在执行");
    }
}
