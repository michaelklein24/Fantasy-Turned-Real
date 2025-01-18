package com.kleintwins.ftr.notification.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class NotificationPayloadJsonConverter implements AttributeConverter<NotificationPayload, String> {

    private final ObjectMapper objectMapper;

    public NotificationPayloadJsonConverter() {
        objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
    }

    @Override
    public String convertToDatabaseColumn(NotificationPayload payload) {
        try {
            // Ensure that the payload is correctly serialized to a JSON string
            return objectMapper.writeValueAsString(payload);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error serializing NotificationPayload", e);
        }
    }

    @Override
    public NotificationPayload convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, NotificationPayload.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deserializing NotificationPayload", e);
        }
    }
}
