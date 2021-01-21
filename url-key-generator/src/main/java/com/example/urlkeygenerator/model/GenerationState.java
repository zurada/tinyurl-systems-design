package com.example.urlkeygenerator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        property = "@class"
)
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum GenerationState {
    @JsonProperty("BUSY") BUSY,
    @JsonProperty("FREE") FREE;

    public String getName() {
        return this.name();
    }
}
