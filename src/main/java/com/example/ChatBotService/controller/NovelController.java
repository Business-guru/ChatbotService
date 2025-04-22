package com.example.ChatBotService.controller;

import com.example.ChatBotService.dto.NovelDTO;
import com.example.ChatBotService.entity.Novel;
import com.example.ChatBotService.service.NovelService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/novels")
public class NovelController {
    private final NovelService novelService;

    public NovelController(NovelService novelService) {
        this.novelService = novelService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadNovel(
            @RequestParam String title,
            @RequestParam("pdf") MultipartFile pdf,
            @RequestParam("cover") MultipartFile coverImage) throws Exception {

        Novel novel = novelService.saveNovel(title, pdf, coverImage);
        return ResponseEntity.ok("Novel saved with ID: " + novel.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NovelDTO> getNovel(@PathVariable Long id) {
        Novel novel = novelService.getNovel(id);
        ModelMapper modelMapper=new ModelMapper();
        return ResponseEntity.ok(modelMapper.map(novel, NovelDTO.class));
    }
    @GetMapping("/covers")
    public ResponseEntity<List<String>> getCoverImages() {
        List<String> novel = novelService.getAllCoverImages();
        return ResponseEntity.ok(novel);
    }
}
