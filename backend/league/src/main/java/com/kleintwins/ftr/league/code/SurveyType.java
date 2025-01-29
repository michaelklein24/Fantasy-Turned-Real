package com.kleintwins.ftr.league.code;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Describes the type of survey", enumAsRef = true)
public enum SurveyType {
    EPISODE,
    SPECIAL
}
