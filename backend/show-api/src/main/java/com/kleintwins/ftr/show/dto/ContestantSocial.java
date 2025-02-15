package com.kleintwins.ftr.show.dto;

import com.kleintwins.ftr.show.code.SocialPlatform;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContestantSocial {
    private String handle;
    private SocialPlatform platform;
}
