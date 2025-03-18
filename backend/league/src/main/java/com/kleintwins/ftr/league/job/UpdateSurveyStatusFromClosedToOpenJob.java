package com.kleintwins.ftr.league.job;

import com.kleintwins.ftr.job.IJob;
import com.kleintwins.ftr.league.code.SurveyStatus;
import com.kleintwins.ftr.league.model.SurveyModel;
import com.kleintwins.ftr.league.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateSurveyStatusFromClosedToOpenJob implements IJob {

    private final SurveyService surveyService;

    @Override
    @Scheduled(fixedRate = 60000)
    public void doWork() {
        log.info("UpdateSurveyStatusFromClosedToOpenJob started at {}", LocalDateTime.now());
        int updateCount = 0;

        List<SurveyModel> surveyModels = surveyService.getClosedSurveysReadyToOpen();
        for (SurveyModel survey : surveyModels) {
            try {
                surveyService.updateSurveyStatus(survey, SurveyStatus.OPEN);
                updateCount++;
                log.debug("Updated survey ID {} to OPEN", survey.getSurveyId());
            } catch (Exception ex) {
                log.error("Failed to update survey ID {}: {}", survey.getSurveyId(), ex.getMessage(), ex);
            }
        }

        log.info("UpdateSurveyStatusFromClosedToOpenJob completed. {} surveys updated.", updateCount);
    }
}
