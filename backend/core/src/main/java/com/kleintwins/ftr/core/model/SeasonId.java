package com.kleintwins.ftr.core.model;

import com.kleintwins.ftr.core.code.Show;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Embeddable
@Data
public class SeasonId {
    private int sequence;
    @Enumerated(value = EnumType.STRING)
    private Show show;

    public SeasonId(Show show, int sequence) {
        this.sequence = sequence;
        this.show = show;
    }

    public SeasonId() {}
}
