package com.example.ChatBotService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="novels")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Novel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String pdfFileName;

    @Lob
    private String pdfBase64; // PDF as Base64

    private String coverImageFileName;

    @Lob
    private String coverImageBase64; // Image as Base64

    // Getters and Setters
}
