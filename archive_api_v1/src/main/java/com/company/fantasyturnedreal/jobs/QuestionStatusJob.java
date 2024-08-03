package com.company.fantasyturnedreal.jobs;

import com.company.fantasyturnedreal.service.league.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QuestionStatusJob {

    @Autowired
    QuestionService questionService;

    @Scheduled(fixedRate = 60000)
    public void updateQuestionStatus() {
        try {
            long methodStartTime = System.currentTimeMillis();
            questionService.validateQuestionStatus();
            long methodEndTime = System.currentTimeMillis();
            log.info("Running question status validation job.  Execution time: {}ms", methodEndTime - methodStartTime);
        } catch (Exception e) {
            log.error("Question validation job failed. Error: ", e.getMessage());
        }
    }

}
