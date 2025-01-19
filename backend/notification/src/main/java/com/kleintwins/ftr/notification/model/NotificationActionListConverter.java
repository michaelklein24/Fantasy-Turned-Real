package com.kleintwins.ftr.notification.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class NotificationActionListConverter implements AttributeConverter<List<NotificationAction>, String> {

    private final ObjectMapper objectMapper;

    public NotificationActionListConverter() {
        objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
    }

    @Override
    public String convertToDatabaseColumn(List<NotificationAction> actions) {
        try {
            return objectMapper.writeValueAsString(actions);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error serializing NotificationPayload", e);
        }
    }

    @Override
    public List<NotificationAction> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<NotificationAction>>() {});
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deserializing NotificationPayload", e);
        }
    }
}
