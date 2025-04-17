package com.example.ChatBotService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NovelDTO {

    private String title;
    private String pdfFileName;
    private String pdfBase64;
    private String coverImageFileName;
    private String coverImageBase64;
}
