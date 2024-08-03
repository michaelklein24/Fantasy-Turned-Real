package com.ftr.api.core.dto;

import lombok.Data;

@Data
public abstract class AbstractResponseDto {
    protected boolean success;
    protected String errorMsg;
}
