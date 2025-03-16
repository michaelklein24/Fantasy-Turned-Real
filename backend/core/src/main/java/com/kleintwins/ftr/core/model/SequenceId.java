package com.kleintwins.ftr.core.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class SequenceId {
    private String entity;
    private String entityId;

    public SequenceId(String entity, String entityId) {
        this.entity = entity;
        this.entityId = entityId;
    }
}
