package com.example.ChatBotService.repository;

import com.example.ChatBotService.entity.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NovelRepository extends JpaRepository<Novel, Long> {

    @Query("SELECT n.coverImageBase64 FROM Novel n")
    public List<String> findAllImages();

}
