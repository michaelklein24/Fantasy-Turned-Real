package com.ftr.api.security.dto;

import com.ftr.api.user.code.CheckUsernameResponseTypeCode;
import lombok.Data;

@Data
public class CheckUsernameResponse {
    private CheckUsernameResponseTypeCode checkUsernameResponseTypeCode;
}
