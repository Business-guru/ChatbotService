package com.example.ChatBotService.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChatType {
    @JsonProperty("General")
    GENERAL,
    @JsonProperty("Law")
    LAW
}
