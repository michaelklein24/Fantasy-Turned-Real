package com.ftr.api.security.dto;

import com.ftr.api.security.code.CheckUsernameResponseTypeCode;
import lombok.Data;

@Data
public class CheckUsernameResponse {
    private CheckUsernameResponseTypeCode checkUsernameResponseTypeCode;
}
