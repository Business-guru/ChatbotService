package com.example.ChatBotService.service;

import com.example.ChatBotService.entity.Novel;
import com.example.ChatBotService.repository.NovelRepository;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class NovelService {

    private final NovelRepository novelRepository;

    public NovelService(NovelRepository novelRepository) {
        this.novelRepository = novelRepository;
    }

    public Novel saveNovel(String title, MultipartFile pdf, MultipartFile image) throws Exception {
        Novel novel = new Novel();
        novel.setTitle(title);

        // PDF
        byte[] pdfBytes = pdf.getBytes();
        novel.setPdfFileName(pdf.getOriginalFilename());
        novel.setPdfBase64(Base64.getEncoder().encodeToString(pdfBytes));

        // Cover Image
        byte[] imageBytes = image.getBytes();
        novel.setCoverImageFileName(image.getOriginalFilename());
        novel.setCoverImageBase64(Base64.getEncoder().encodeToString(imageBytes));

        return novelRepository.save(novel);
    }

    public Novel getNovel(Long id) {
        return novelRepository.findById(id).orElseThrow(() -> new RuntimeException("Novel not found"));
    }

    public List<String> getAllCoverImages() {

        List<String>  list=novelRepository.findAllImages();
        if(list==null)return new ArrayList<>();
        return list;
    }
}
