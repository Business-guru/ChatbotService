package com.example.ChatBotService.utils;

import com.example.ChatBotService.enums.ChatType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

@ControllerAdvice
public class EnumConverterConfig {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ChatType.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(ChatType.valueOf(text.toUpperCase()));
            }
        });
    }
}
