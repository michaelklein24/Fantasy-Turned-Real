package com.kleintwins.ftr.show.model;

import com.kleintwins.ftr.show.code.SocialPlatform;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Embeddable
@Data
public class ContestantSocialId {
    @Enumerated(value = EnumType.STRING)
    @Column(name = "platform")
    private SocialPlatform platform;
    @Column(name = "contestant_id")
    private String contestantId;
}
