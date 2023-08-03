package com.dico.test.util;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
public class ApiErrorResponse {
    private int statusCode;
    private Serializable detail;
    @JsonSerialize(using = InstantSerializer.class)
    @JsonDeserialize(using = InstantDeserializer.class)
    private Instant timestamp;

    public ApiErrorResponse(int statusCode, Serializable detail,
                            Instant timestamp) {
        this.statusCode = statusCode;
        this.detail = detail;
        this.timestamp = timestamp;
    }

    public static ApiErrorResponse getInstance(int statusCode,
                                               Serializable detail) {
        return new ApiErrorResponse(statusCode, detail,
                Instant.now().truncatedTo(ChronoUnit.SECONDS));
    }
}
